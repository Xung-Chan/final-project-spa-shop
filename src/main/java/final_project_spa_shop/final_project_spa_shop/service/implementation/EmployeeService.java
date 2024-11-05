package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.EmployeeRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.EmployeeResponse;
import final_project_spa_shop.final_project_spa_shop.entity.EmployeeEntity;
import final_project_spa_shop.final_project_spa_shop.mapper.EmployeeMapper;
import final_project_spa_shop.final_project_spa_shop.repository.EmployeeRepository;
import final_project_spa_shop.final_project_spa_shop.repository.PermissionRepository;
import final_project_spa_shop.final_project_spa_shop.repository.RoleRepository;
import final_project_spa_shop.final_project_spa_shop.service.IEmployeeService;
import final_project_spa_shop.final_project_spa_shop.service.IImageSerive;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {
	EmployeeRepository employeeRepository;
	PermissionRepository permissionRepository;
	RoleRepository roleRepository;
	EmployeeMapper employeeMapper;
	IImageSerive imageSerive;

	@Override
	public List<EmployeeResponse> getAll() {
		return employeeRepository.findAll().stream().map(employeeMapper::toEmployeeResponse).toList();
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
		entity.setPermissions(
				new HashSet<>(
						object.getPermissions().stream()
								.map(x -> permissionRepository.findById(x)
										.orElseThrow(() -> new EntityNotFoundException("INVALID_PERMISSION")))
								.toList()));
		PasswordEncoder encoder = new BCryptPasswordEncoder(9);
		entity.setPassword(encoder.encode(entity.getPassword()));
		// mặc định employee id = 1
		entity.setRole(roleRepository.findById(1l).get());
		long id = entity.getId();
		if (id != 0)
			employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("INVALID_EMPLOYEE"));
		else
			entity = employeeRepository.save(entity);
		String imagePath = imageSerive.saveImage(object.getImage(), "/customer/img/avt-" + entity.getId());
		entity.setImagePath(imagePath);
		return employeeMapper.toEmployeeResponse(employeeRepository.save(entity));
	}

}
