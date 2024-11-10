package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.BillRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.BillResponse;
import final_project_spa_shop.final_project_spa_shop.entity.BillEntity;
import final_project_spa_shop.final_project_spa_shop.entity.ServiceEntity;
import final_project_spa_shop.final_project_spa_shop.exception.ErrorCode;
import final_project_spa_shop.final_project_spa_shop.mapper.BillMapper;
import final_project_spa_shop.final_project_spa_shop.repository.BillRepository;
import final_project_spa_shop.final_project_spa_shop.repository.CustomerRepository;
import final_project_spa_shop.final_project_spa_shop.repository.ServiceRepository;
import final_project_spa_shop.final_project_spa_shop.service.IBillService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service(value = "billService")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal =  true)
@RequiredArgsConstructor
public class BillService implements IBillService {
	BillRepository billRepo;
	CustomerRepository customerRepository;
	ServiceRepository serviceRepository;
	BillMapper billMapper;
	//thống kê theo thời gian
	@Override
	public List<BillResponse> getAll() {
		return billRepo.findAll().stream().map(billMapper::toBillResponse).toList();
	}

	@Override
	public List<BillResponse> getAllByCustomerID(long id) {
		return billRepo.findByCustomerId(id).stream().map(billMapper::toBillResponse).toList();
	}

	@Override
	public BillResponse getById(long id) {
		Optional<BillEntity> entity = billRepo.findById(id);
		if (!entity.isPresent())
			throw new EntityNotFoundException("INVALID_BILL");
		return billMapper.toBillResponse(entity.get());
	}

	@Override
	public BillResponse delete(long id) {
		Optional<BillEntity> result = billRepo.findById(id);
		if (!result.isPresent())
			throw new EntityNotFoundException("INVALID_BILL");
		billRepo.deleteById(id);
		return billMapper.toBillResponse(result.get());
	}

//	@Override
//	public BillResponse save(BillRequest object) {
//		BillEntity entity = billMapper.toBillEntity(object);
//		long id = entity.getId();
//		if (id != 0)
//			billRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("INVALID_BILL"));
//		Optional<CustomerEntity> customerEntity = customerRepository.findById(object.getCustomerID());
//		Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(object.getEmployeeID());
//		Optional<VoucherEntity> voucherOptional = voucherRepository.findById(object.getVoucherID());
//		if (!customerEntity.isPresent())
//			throw new EntityNotFoundException("INVALID_CUSTOMER");
//		if (!employeeEntity.isPresent())
//			throw new EntityNotFoundException("INVALID_EMPLOYEE");
//		if (voucherOptional.isPresent()) {
//			VoucherEntity voucherEntity = voucherOptional.get();
//			if (voucherEntity.getExpired_at().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
//					.isBefore(LocalDate.now())) {
//				throw new EntityNotFoundException("INVALID_VOUCHER");
//			}
//			if (voucherEntity.getBill().getId() != entity.getId())
//				throw new EntityExistsException("DUPLICATED_VOUCHER");
//			entity.setVoucher(voucherOptional.get());
//		}
//		entity.setCustomer(customerEntity.get());
//		entity.setEmployee(employeeEntity.get());
//		entity.setServices(new HashSet<>(object.getServices().stream().map(
//				x -> serviceRepository.findById(x).orElseThrow(() -> new EntityNotFoundException("INVALID_SERVICE")))
//				.toList()));
//		return billMapper.toBillResponse(billRepo.save(entity));
//	}
	@Override
	public BillResponse create(BillRequest billRequest) {
		BillEntity  billEntity = billMapper.toBillEntity(billRequest);
		billEntity.setCustomer(customerRepository.findById(billRequest.getCustomerID()).orElseThrow(()->new RuntimeException(ErrorCode.INVALID_CUSTOMER.name())));
		Set<ServiceEntity> services = new HashSet<ServiceEntity>(billRequest.getServices()
				.stream()
				.map((x)->{
					return serviceRepository.findById(x).orElseThrow(()->new RuntimeException(ErrorCode.INVALID_SERVICE.name()));
				}).toList());
		billEntity.setServices(services);
		BillResponse billResponse = billMapper.toBillResponse(billRepo.save(billEntity));
		billResponse.setServices(new HashSet<String>( billEntity.getServices().stream().map(ServiceEntity::getName).toList()));
		return billResponse;
	}
	@Override
	public BillResponse pay(long id) {
		BillEntity billEntity = billRepo.findById(id).orElseThrow(()->new RuntimeException(ErrorCode.INVALID_BILL.name()));
		billEntity.setStatus(true);
		billEntity = billRepo.save(billEntity);
		BillResponse billResponse = billMapper.toBillResponse(billEntity);
		billResponse.setServices(new HashSet<String>( billEntity.getServices().stream().map(ServiceEntity::getName).toList()));
		return billResponse;
	}

}
