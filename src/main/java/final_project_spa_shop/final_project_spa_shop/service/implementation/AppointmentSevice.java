package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.AppointmentRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AppointmentResponse;
import final_project_spa_shop.final_project_spa_shop.entity.AppointmentEntity;
import final_project_spa_shop.final_project_spa_shop.mapper.AppointmentMapper;
import final_project_spa_shop.final_project_spa_shop.repository.AppointmentRepository;
import final_project_spa_shop.final_project_spa_shop.repository.CustomerRepository;
import final_project_spa_shop.final_project_spa_shop.service.IService;

@Service
public class AppointmentSevice implements IService<AppointmentResponse, AppointmentRequest> {
	@Autowired
	private AppointmentRepository appointmentRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private AppointmentMapper appointmentMapper;

	@Override
	public List<AppointmentResponse> getAll() {
		return appointmentRepo.findAll().stream().map(appointmentMapper::toAppointmentResponse).toList();
	}

	@Override
	public AppointmentResponse delete(long id) {
		Optional<AppointmentEntity> result = appointmentRepo.findById(id);
		if (!result.isPresent())
			throw new RuntimeException("Appointment is not exist");
		appointmentRepo.deleteById(id);
		return appointmentMapper.toAppointmentResponse(result.get());
	}

	@Override
	public AppointmentResponse save(AppointmentRequest object) {
		AppointmentEntity entity = appointmentMapper.toAppointmentEntity(object);
		entity.setCustomer(customerRepo.findById(object.getCustomerID()).get());
		return appointmentMapper.toAppointmentResponse(appointmentRepo.save(entity));
	}

}
