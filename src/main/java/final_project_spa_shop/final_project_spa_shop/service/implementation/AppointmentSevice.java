package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.AppointmentRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AppointmentResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.PaginationResponse;
import final_project_spa_shop.final_project_spa_shop.entity.AppointmentEntity;
import final_project_spa_shop.final_project_spa_shop.entity.ServiceEntity;
import final_project_spa_shop.final_project_spa_shop.exception.ErrorCode;
import final_project_spa_shop.final_project_spa_shop.mapper.AppointmentMapper;
import final_project_spa_shop.final_project_spa_shop.repository.AppointmentRepository;
import final_project_spa_shop.final_project_spa_shop.repository.CustomerRepository;
import final_project_spa_shop.final_project_spa_shop.repository.ServiceRepository;
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
	ServiceRepository serviceRepository;
	AppointmentMapper appointmentMapper;

	@Override
	public List<AppointmentResponse> getAll() {
		return appointmentRepo.findAll().stream().map((x) -> {
			Set<String> services = new HashSet<>(x.getServices().stream().map(ServiceEntity::getName).toList());
			AppointmentResponse appointmentResponse = appointmentMapper.toAppointmentResponse(x);
			appointmentResponse.setServices(services);
			return appointmentResponse;
		}).toList();
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
	public List<AppointmentResponse> myAppointment() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return appointmentRepo.findByCustomerAccountUsername(username).stream().map((x) -> {
			Set<String> services = new HashSet<>(x.getServices().stream().map(ServiceEntity::getName).toList());
			AppointmentResponse appointmentResponse = appointmentMapper.toAppointmentResponse(x);
			appointmentResponse.setServices(services);
			return appointmentResponse;
		}).toList();
	}

	@Override
	public AppointmentResponse save(AppointmentRequest request) {
		AppointmentEntity entity = appointmentMapper.toAppointmentEntity(request);
		long id = entity.getId();
		if (id != 0)
			appointmentRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("INVALID_APPOINTMENT"));
		entity.setCustomer(customerRepo.findById(request.getCustomerID())
				.orElseThrow(() -> new RuntimeException(ErrorCode.INVALID_CUSTOMER.getMessage())));
		entity.setServices(new HashSet<ServiceEntity>(request.getServiceIDs().stream().map((x) -> {
			return serviceRepository.findById(x)
					.orElseThrow(() -> new RuntimeException(ErrorCode.INVALID_SERVICE.name()));
		}).toList()));
		double initalCost = calculateTotalCost(entity.getServices());
		double discount = 0.05*initalCost;
		entity.setCost(initalCost-discount);
		entity.setDiscount(discount);
		entity.setInitalCost(initalCost);
		AppointmentResponse appointmentResponse = appointmentMapper.toAppointmentResponse(appointmentRepo.save(entity));
		appointmentResponse
				.setServices(new HashSet<String>(entity.getServices().stream().map(ServiceEntity::getName).toList()));
		return appointmentResponse;
	}

	private double calculateTotalCost(Set<ServiceEntity> services) {
		return services.stream().mapToDouble(ServiceEntity::getPrice).sum();
	}

	@Override
	public AppointmentResponse pay(long id) {
		AppointmentEntity appointmentEntity = appointmentRepo.findById(id)
				.orElseThrow(() -> new RuntimeException(ErrorCode.INVALID_APPOINTMENT.name()));
		appointmentEntity.setStatus(true);
		appointmentEntity = appointmentRepo.save(appointmentEntity);
		return appointmentMapper.toAppointmentResponse(appointmentEntity);
	}

	@Override
	public AppointmentResponse findById(long id) {
		AppointmentEntity appointmentEntity = appointmentRepo.findById(id)
				.orElseThrow(() -> new RuntimeException(ErrorCode.INVALID_APPOINTMENT.name()));
		AppointmentResponse appointmentResponse = appointmentMapper.toAppointmentResponse(appointmentEntity);
		appointmentResponse.setServices(
				new HashSet<>(appointmentEntity.getServices().stream().map(ServiceEntity::getName).toList()));
		return appointmentResponse;
	}

	@Override
	public List<AppointmentResponse> getToday() {
		List<AppointmentEntity> responses = appointmentRepo.findByDate(LocalDate.now());
		return responses.stream().map((x) -> {
			AppointmentResponse response = appointmentMapper.toAppointmentResponse(x);
			response.setServices(new HashSet<String>(x.getServices().stream().map(ServiceEntity::getName).toList()));
			return response;
		}).toList();
	}

	@Override
	public List<AppointmentResponse> getTomorow() {
		List<AppointmentEntity> responses = appointmentRepo.findByDate(LocalDate.now().plusDays(1));
		return responses.stream().map((x) -> {
			AppointmentResponse response = appointmentMapper.toAppointmentResponse(x);
			response.setServices(new HashSet<String>(x.getServices().stream().map(ServiceEntity::getName).toList()));
			return response;
		}).toList();
	}

	@Override
	public PaginationResponse getTotalPage() {
		return new PaginationResponse((int)Math.ceil(1.0*appointmentRepo.count()/5));
	}
	@Override
	public PaginationResponse getTotalPageToday() {
		return new PaginationResponse((int)Math.ceil(1.0*appointmentRepo.countByDate(LocalDate.now())/5));
	}
	@Override
	public PaginationResponse getTotalPageTomorow() {
		return new PaginationResponse((int)Math.ceil(1.0*appointmentRepo.countByDate(LocalDate.now().plusDays(1))/5));
	}
	
	@Override
	public List<AppointmentResponse> searchByFullName(String fullName) {
		return appointmentRepo.findByCustomerFullNameIgnoreCaseContaining(fullName).stream().map((x) -> {
			AppointmentResponse response = appointmentMapper.toAppointmentResponse(x);
			response.setServices(new HashSet<String>(x.getServices().stream().map(ServiceEntity::getName).toList()));
			return response;
		}).toList();
	}
	@Override
	public List<AppointmentResponse> getAll(int page) {
		Pageable pages = PageRequest.of(page-1, 5);
		return appointmentRepo.findAll(pages).getContent().stream().map((x) -> {
			AppointmentResponse response = appointmentMapper.toAppointmentResponse(x);
			response.setServices(new HashSet<String>(x.getServices().stream().map(ServiceEntity::getName).toList()));
			return response;
		}).toList();
	}

}

//	@Override
//	public AppointmentResponse save(Date date) {
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		CustomerEntity customerEntity = customerRepo.findByAccountUsername(username)
//				.orElseThrow(() -> new RuntimeException(ErrorCode.INVALID_CUSTOMER.name()));
//		AppointmentRequest request = new AppointmentRequest();
//		request.setDate(date);
//		request.setCustomerID(customerEntity.getId());
//		return save(request);
//	}
