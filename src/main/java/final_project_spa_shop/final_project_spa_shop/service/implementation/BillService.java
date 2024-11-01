package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.BillRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.BillResponse;
import final_project_spa_shop.final_project_spa_shop.entity.BillEntity;
import final_project_spa_shop.final_project_spa_shop.mapper.BillMapper;
import final_project_spa_shop.final_project_spa_shop.repository.BillRepository;
import final_project_spa_shop.final_project_spa_shop.repository.CustomerRepository;
import final_project_spa_shop.final_project_spa_shop.repository.EmployeeRepository;
import final_project_spa_shop.final_project_spa_shop.repository.ServiceRepository;
import final_project_spa_shop.final_project_spa_shop.repository.VoucherRepository;
import final_project_spa_shop.final_project_spa_shop.service.IBillService;
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
	public BillResponse delete(long id) {
		Optional<BillEntity> result = billRepo.findById(id);
		if (!result.isPresent())
			throw new RuntimeException("Appointment is not exist");
		billRepo.deleteById(id);
		return billMapper.toBillResponse(result.get());
	}

	@Override
	public BillResponse save(BillRequest object) {
		BillEntity entity = billMapper.toBillEntity(object);
		entity.setCustomer(customerRepository.findById(object.getCustomerID()).get());
		entity.setEmployee(employeeRepository.findById(object.getEmployeeID()).get());
		entity.setVoucher(voucherRepository.findById(object.getVoucherID()).get());
		entity.setServices(
				new HashSet<>(object.getServices().stream().map(x -> serviceRepository.findById(x).get()).toList()));
		return billMapper.toBillResponse(billRepo.save(entity));
	}

}
