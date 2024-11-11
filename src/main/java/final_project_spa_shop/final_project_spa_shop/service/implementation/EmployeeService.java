package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.AccountRequest;
import final_project_spa_shop.final_project_spa_shop.dto.request.EmployeeRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AccountResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.EmployeeResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.PaginationResponse;
import final_project_spa_shop.final_project_spa_shop.entity.EmployeeEntity;
import final_project_spa_shop.final_project_spa_shop.exception.ErrorCode;
import final_project_spa_shop.final_project_spa_shop.mapper.EmployeeMapper;
import final_project_spa_shop.final_project_spa_shop.repository.AccountRepository;
import final_project_spa_shop.final_project_spa_shop.repository.EmployeeRepository;
import final_project_spa_shop.final_project_spa_shop.repository.RoleRepository;
import final_project_spa_shop.final_project_spa_shop.service.IEmployeeService;
import final_project_spa_shop.final_project_spa_shop.service.IImageSerive;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {
	EmployeeRepository employeeRepository;
	RoleRepository roleRepository;
	AccountRepository accountRepository;
	AccountService accountService;
	EmployeeMapper employeeMapper;
	PasswordEncoder encoder;
	IImageSerive imageSerive;

	@Override
	public List<EmployeeResponse> getAll() {
		return employeeRepository.findAll().stream().map(employeeMapper::toEmployeeResponse).toList();
	}
	@Override
	public EmployeeResponse myInformation() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return employeeMapper.toEmployeeResponse(employeeRepository.findByAccountUsername(username).orElseThrow(()->new RuntimeException(ErrorCode.INVALID_EMPLOYEE.name())));
	}
	@Override	
	public List<EmployeeResponse> getAllByPage(int page) {
		Pageable pages = PageRequest.of(page-1, 5);
		return employeeRepository.findAll(pages).getContent().stream().map(employeeMapper::toEmployeeResponse).toList();
	}
	
	@Override
	public PaginationResponse getTotalPage() {
		return new PaginationResponse((int)Math.ceil(1.0*employeeRepository.count()/5));
	}
	
	@Override
	public List<EmployeeResponse> getAllLimit(int limit) {
		Pageable pages = PageRequest.of(0, limit);
		return employeeRepository.findAll(pages).getContent().stream().map(employeeMapper::toEmployeeResponse).toList();
	}

	@Override
	public EmployeeResponse getById(long id) {
		Optional<EmployeeEntity> result = employeeRepository.findById(id);
		if (!result.isPresent())
			throw new EntityNotFoundException("INVALID_EMPLOYEE");
		return employeeMapper.toEmployeeResponse(result.get());
	}

	@Override
	public EmployeeResponse create(AccountRequest accountRequest, EmployeeRequest employeeRequest) {
		accountRequest.setPassword(encoder.encode(accountRequest.getPassword()));
		accountRequest.setRoleID(roleRepository.findById(accountRequest.getRoleID())
				.orElseThrow(() -> new RuntimeException(ErrorCode.INVALID_ROLE.getMessage())).getId());
		accountService.create(accountRequest);
		EmployeeEntity entity = employeeMapper.toEmployeeEntity(employeeRequest);
		entity.setAccount(accountRepository.findByUsername(accountRequest.getUsername())
				.orElseThrow(() -> new RuntimeException(ErrorCode.INVALID_USERNAME.getMessage())));
		entity = employeeRepository.save(entity);
		accountRequest.setProfileID(entity.getId());
		AccountResponse accountResponse = accountService.update(accountRequest);
		String imagePath = imageSerive.saveImage(employeeRequest.getImage(), "/customer/img/avt-" + entity.getId());
		entity.setImagePath(imagePath);
		entity.setAccount(accountRepository.findByUsername(accountRequest.getUsername())
				.orElseThrow(() -> new RuntimeException(ErrorCode.INVALID_USERNAME.getMessage())));
		EmployeeResponse employeeResponse= employeeMapper.toEmployeeResponse(employeeRepository.save(entity));
		employeeResponse.setUsername(accountResponse.getUsername());
		employeeResponse.setPassword(accountResponse.getPassword());
		return employeeResponse;
	}

	@Override
	public EmployeeResponse update(EmployeeRequest employeeRequest) {
		long id = employeeRequest.getId();
		EmployeeEntity oldEmployeeEntity = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(ErrorCode.INVALID_EMPLOYEE.getMessage()));
		EmployeeEntity customerEntity = employeeMapper.toEmployeeEntity(employeeRequest);
		String imagePath = imageSerive.saveImage(employeeRequest.getImage(),
				"/customer/img/avt-" + customerEntity.getId());
		customerEntity.setImagePath(imagePath);
		customerEntity.setAccount(oldEmployeeEntity.getAccount());
		String username = oldEmployeeEntity.getAccount().getUsername();
		String password = oldEmployeeEntity.getAccount().getPassword();
		EmployeeResponse employeeResponse = employeeMapper.toEmployeeResponse(employeeRepository.save(customerEntity));
		employeeResponse.setUsername(username);
		employeeResponse.setPassword(password);
		return employeeResponse;
	}

	@Override
	public EmployeeResponse loadUserByUsername(String username) {
		return employeeMapper.toEmployeeResponse(employeeRepository.findByAccountUsername(username).orElseThrow(()->new RuntimeException(ErrorCode.INVALID_EMPLOYEE.getMessage())));
	}

	@Override
	public EmployeeResponse loadMyInformation() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return loadUserByUsername(username);
	}
	
}
