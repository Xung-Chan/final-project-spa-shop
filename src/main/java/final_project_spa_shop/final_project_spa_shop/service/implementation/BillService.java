package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.BillRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.BillResponse;
import final_project_spa_shop.final_project_spa_shop.entity.BillEntity;
import final_project_spa_shop.final_project_spa_shop.entity.CustomerEntity;
import final_project_spa_shop.final_project_spa_shop.entity.EmployeeEntity;
import final_project_spa_shop.final_project_spa_shop.entity.VoucherEntity;
import final_project_spa_shop.final_project_spa_shop.mapper.BillMapper;
import final_project_spa_shop.final_project_spa_shop.repository.BillRepository;
import final_project_spa_shop.final_project_spa_shop.repository.CustomerRepository;
import final_project_spa_shop.final_project_spa_shop.repository.EmployeeRepository;
import final_project_spa_shop.final_project_spa_shop.repository.ServiceRepository;
import final_project_spa_shop.final_project_spa_shop.repository.VoucherRepository;
import final_project_spa_shop.final_project_spa_shop.service.IBillService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Service(value = "billService")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillService implements IBillService {
	@Autowired
	BillRepository billRepo;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	VoucherRepository voucherRepository;
	@Autowired
	ServiceRepository serviceRepository;
	@Autowired
	BillMapper billMapper;

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

	@Override
	public BillResponse save(BillRequest object) {
		BillEntity entity = billMapper.toBillEntity(object);
		long id = entity.getId();
		if (id != 0)
			billRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("INVALID_BILL"));
		Optional<CustomerEntity> customerEntity = customerRepository.findById(object.getCustomerID());
		Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(object.getEmployeeID());
		Optional<VoucherEntity> voucherOptional = voucherRepository.findById(object.getVoucherID());
		if (!customerEntity.isPresent())
			throw new EntityNotFoundException("INVALID_CUSTOMER");
		if (!employeeEntity.isPresent())
			throw new EntityNotFoundException("INVALID_EMPLOYEE");
		if (voucherOptional.isPresent()) {
			VoucherEntity voucherEntity = voucherOptional.get();
			if (voucherEntity.getExpired_at().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
					.isBefore(LocalDate.now())) {
				throw new EntityNotFoundException("INVALID_VOUCHER");
			}
			if (voucherEntity.getBill().getId() != entity.getId())
				throw new EntityExistsException("DUPLICATED_VOUCHER");
			entity.setVoucher(voucherOptional.get());
		}
		entity.setCustomer(customerEntity.get());
		entity.setEmployee(employeeEntity.get());
		entity.setServices(new HashSet<>(object.getServices().stream().map(
				x -> serviceRepository.findById(x).orElseThrow(() -> new EntityNotFoundException("INVALID_SERVICE")))
				.toList()));
		return billMapper.toBillResponse(billRepo.save(entity));
	}

}
