package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.AppointmentRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AppointmentResponse;
import final_project_spa_shop.final_project_spa_shop.entity.AppointmentEntity;
import final_project_spa_shop.final_project_spa_shop.entity.CustomerEntity;
import final_project_spa_shop.final_project_spa_shop.exception.ErrorCode;
import final_project_spa_shop.final_project_spa_shop.mapper.AppointmentMapper;
import final_project_spa_shop.final_project_spa_shop.repository.AppointmentRepository;
import final_project_spa_shop.final_project_spa_shop.repository.CustomerRepository;
import final_project_spa_shop.final_project_spa_shop.service.IAppointmentSevice;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AppointmentSevice implements IAppointmentSevice {
	AppointmentRepository appointmentRepo;
	CustomerRepository customerRepo;
	AppointmentMapper appointmentMapper;
	
	@Override
	public List<AppointmentResponse> getAll() {
		return appointmentRepo.findAll().stream().map(appointmentMapper::toAppointmentResponse).toList();
	}

	@Override
	public AppointmentResponse delete(long id) {
		Optional<AppointmentEntity> result = appointmentRepo.findById(id);
		if (!result.isPresent())
			throw new EntityNotFoundException("INVALID_APPOINTMENT");
		appointmentRepo.deleteById(id);
		return appointmentMapper.toAppointmentResponse(result.get());
	}

	@Override
	public List<AppointmentResponse> getAllByCustomerID(long id) {
		return appointmentRepo.findByCustomerId(id).stream().map(appointmentMapper::toAppointmentResponse).toList();
	}

	@Override
	public AppointmentResponse save(AppointmentRequest object) {
		AppointmentEntity entity = appointmentMapper.toAppointmentEntity(object);
		long id = entity.getId();
		if (id != 0)
			appointmentRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("INVALID_APPOINTMENT"));
		Optional<CustomerEntity> customerOptional = customerRepo.findById(object.getCustomerID());
		if (!customerOptional.isPresent())
			throw new EntityNotFoundException("INVALID_CUSTOMER");
		entity.setCustomer(customerOptional.get());
		return appointmentMapper.toAppointmentResponse(appointmentRepo.save(entity));
	}

	@Override
	public AppointmentResponse save(Date date) {
		String username =SecurityContextHolder.getContext().getAuthentication().getName();
		CustomerEntity customerEntity= customerRepo.findByUsername(username).orElseThrow(()->new RuntimeException(ErrorCode.INVALID_CUSTOMER.name()));
		AppointmentRequest request= new AppointmentRequest();
		request.setDate(date);
		request.setCustomerID(customerEntity.getId());
		return save(request);
	}

}
