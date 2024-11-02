package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.EmployeeRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.EmployeeResponse;
import final_project_spa_shop.final_project_spa_shop.entity.EmployeeEntity;
import final_project_spa_shop.final_project_spa_shop.mapper.EmployeeMapper;
import final_project_spa_shop.final_project_spa_shop.repository.EmployeeRepository;
import final_project_spa_shop.final_project_spa_shop.repository.PermissionRepository;
import final_project_spa_shop.final_project_spa_shop.repository.RoleRepository;
import final_project_spa_shop.final_project_spa_shop.service.IEmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeService implements IEmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	PermissionRepository permissionRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	EmployeeMapper employeeMapper;

	@Override
	public List<EmployeeResponse> getAll() {
		return employeeRepository.findAll().stream().map(employeeMapper::toEmployeeResponse).toList();
	}

	@Override
	public EmployeeResponse getById(long id) {
		Optional<EmployeeEntity> result = employeeRepository.findById(id);
		if (!result.isPresent())
			throw new EntityNotFoundException("INVALID_EMPLOYEE");
		return employeeMapper.toEmployeeResponse(result.get());
	}

	@Override
	public EmployeeResponse delete(long id) {
		Optional<EmployeeEntity> result = employeeRepository.findById(id);
		if (!result.isPresent())
			throw new EntityNotFoundException("INVALID_EMPLOYEE");
		employeeRepository.deleteById(id);
		return employeeMapper.toEmployeeResponse(result.get());
	}

	@Override
	public EmployeeResponse save(EmployeeRequest object) {
		EmployeeEntity entity = employeeMapper.toEmployeeEntity(object);
		long id = entity.getId();
		if (id != 0)
			employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("INVALID_EMPLOYEE"));
		entity.setPermissions(
				new HashSet<>(
						object.getPermissions().stream()
								.map(x -> permissionRepository.findById(x)
										.orElseThrow(() -> new EntityNotFoundException("INVALID_PERMISSION")))
								.toList()));
		// mặc định employee id = 1
		entity.setRole(roleRepository.findById(1l).get());
		return employeeMapper.toEmployeeResponse(employeeRepository.save(entity));
	}

}
