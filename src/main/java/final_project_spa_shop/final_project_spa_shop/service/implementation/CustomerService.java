package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.AccountRequest;
import final_project_spa_shop.final_project_spa_shop.dto.request.CustomerRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AccountResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.CustomerResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.PaginationResponse;
import final_project_spa_shop.final_project_spa_shop.entity.CustomerEntity;
import final_project_spa_shop.final_project_spa_shop.exception.ErrorCode;
import final_project_spa_shop.final_project_spa_shop.mapper.CustomerMapper;
import final_project_spa_shop.final_project_spa_shop.repository.AccountRepository;
import final_project_spa_shop.final_project_spa_shop.repository.CustomerRepository;
import final_project_spa_shop.final_project_spa_shop.repository.RoleRepository;
import final_project_spa_shop.final_project_spa_shop.service.ICustomerService;
import final_project_spa_shop.final_project_spa_shop.service.IImageSerive;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
	CustomerRepository customerRepository;
	AccountService accountService;
	RoleRepository roleRepository;
	AccountRepository accountRepository;
	CustomerMapper customerMapper;
	IImageSerive imageSerive;
	PasswordEncoder encoder;

	@Override
	public List<CustomerResponse> getAll() {
		return customerRepository.findAll().stream().map(customerMapper::toCustomerRessponse).toList();
	}

	@Override
	public CustomerResponse getById(long id) {
		Optional<CustomerEntity> result = customerRepository.findById(id);
		if (!result.isPresent())
			throw new EntityNotFoundException("INVALID_CUSTOMER");
		return customerMapper.toCustomerRessponse(result.get());
	}

	@Override
	public CustomerResponse delete(long id) {
		CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(()->new RuntimeException(ErrorCode.INVALID_CUSTOMER.name()));
		
		customerRepository.deleteById(id);
		return null;
	}

	@Override
	public CustomerResponse create(AccountRequest accountRequest, CustomerRequest customerRequest) {
		accountRequest.setPassword(encoder.encode(accountRequest.getPassword()));
		accountRequest.setRoleID(roleRepository.findByName("CUSTOMER").orElseThrow(()->new RuntimeException(ErrorCode.INVALID_ROLE.getMessage())).getId());
		accountService.create(accountRequest);
		CustomerEntity entity = customerMapper.toCustomerEntity(customerRequest);
		entity.setAccount(accountRepository.findByUsername(accountRequest.getUsername()).orElseThrow(()->new RuntimeException(ErrorCode.INVALID_USERNAME.getMessage())));
		entity = customerRepository.save(entity);
		accountRequest.setProfileID(entity.getId());
		AccountResponse accountResponse = accountService.update(accountRequest);
		String imagePath = imageSerive.saveImage(customerRequest.getImage(), "/customer/img/avt-" + entity.getId());
		entity.setImagePath(imagePath);
		CustomerResponse customerResponse = customerMapper.toCustomerRessponse(customerRepository.save(entity));
		customerResponse.setUsername(accountResponse.getUsername());
		customerResponse.setPassword(accountResponse.getPassword());
		return customerResponse;
	}
	
	@Override
	public CustomerResponse update(CustomerRequest customerRequest) {
		long id = customerRequest.getId();
		CustomerEntity oldCustomerEntity = customerRepository.findById(id).orElseThrow(()-> new RuntimeException(ErrorCode.INVALID_CUSTOMER.getMessage()));
		CustomerEntity customerEntity = customerMapper.toCustomerEntity(customerRequest);
		if(customerRequest.getImage() != null) {
			String imagePath = imageSerive.saveImage(customerRequest.getImage(), "/customer/img/avt-" + customerEntity.getId());
			customerEntity.setImagePath(imagePath);
		}
		else {
			customerEntity.setImagePath(oldCustomerEntity.getImagePath());
		}
		customerEntity.setAccount(oldCustomerEntity.getAccount());
		String username = oldCustomerEntity.getAccount().getUsername();
		String password = oldCustomerEntity.getAccount().getPassword();
		CustomerResponse customerResponse = customerMapper.toCustomerRessponse(customerRepository.save(customerEntity));
		customerResponse.setUsername(username);
		customerResponse.setPassword(password);
		return customerResponse;
	}
	@Override
	public CustomerResponse loadUserByUsername(String username) {
		Optional<CustomerEntity> result = customerRepository.findByAccountUsername(username);
		if (!result.isPresent())
			throw new EntityNotFoundException("INVALID_CUSTOMER");
		return customerMapper.toCustomerRessponse(result.get());
	}

	@Override
	public CustomerResponse loadMyInformation() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return loadUserByUsername(username);
	}
	@Override
	public List<CustomerResponse> getAllByPage(int page) {
		Pageable pages = PageRequest.of(page - 1, 5);
		return customerRepository.findAll(pages).getContent().stream().map(customerMapper::toCustomerRessponse).toList();
	}

	@Override
	public PaginationResponse getTotalPage() {
		return new PaginationResponse((int) Math.ceil(1.0 * customerRepository.count() / 5));
	}
	

}
