package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.CustomerRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.CustomerResponse;
import final_project_spa_shop.final_project_spa_shop.entity.CustomerEntity;
import final_project_spa_shop.final_project_spa_shop.mapper.CustomerMapper;
import final_project_spa_shop.final_project_spa_shop.repository.CustomerRepository;
import final_project_spa_shop.final_project_spa_shop.repository.RoleRepository;
import final_project_spa_shop.final_project_spa_shop.service.ICustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerService implements ICustomerService {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	CustomerMapper customerMapper;

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
		Optional<CustomerEntity> result = customerRepository.findById(id);
		if (!result.isPresent())
			throw new EntityNotFoundException("INVALID_CUSTOMER");
		customerRepository.deleteById(id);
		return customerMapper.toCustomerRessponse(result.get());
	}

	@Override
	public CustomerResponse save(CustomerRequest object) {
		CustomerEntity entity = customerMapper.toCustomerEntity(object);
		long id = entity.getId();
		if (id != 0 )
			customerRepository.findById(id).orElseThrow(()->new EntityNotFoundException("INVALID_CUSTOMER"));
		// mặc định employee id = 2
		entity.setRole(roleRepository.findById(2l).get());
		return customerMapper.toCustomerRessponse(customerRepository.save(entity));
	}

}
