package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.ScheduleRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ScheduleResponse;
import final_project_spa_shop.final_project_spa_shop.entity.EmployeeEntity;
import final_project_spa_shop.final_project_spa_shop.entity.ScheduleEntity;
import final_project_spa_shop.final_project_spa_shop.mapper.ScheduleMapper;
import final_project_spa_shop.final_project_spa_shop.repository.EmployeeRepository;
import final_project_spa_shop.final_project_spa_shop.repository.ScheduleRepository;
import final_project_spa_shop.final_project_spa_shop.service.IScheduleService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ScheduleService implements IScheduleService {
	@Autowired
	private ScheduleMapper scheduleMapper;
	@Autowired
	private ScheduleRepository scheduleRepo;
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<ScheduleResponse> getAll() {
		return scheduleRepo.findAll().stream().map(scheduleMapper::toScheduleResponse).toList();
	}

	@Override
	public ScheduleResponse delete(long id) {
		Optional<ScheduleEntity> result = scheduleRepo.findById(id);
		if (!result.isPresent())
			throw new EntityNotFoundException("INVALID_SCHEDULE");
		scheduleRepo.deleteById(id);
		return scheduleMapper.toScheduleResponse(result.get());
	}

	@Override
	public ScheduleResponse save(ScheduleRequest object) {
		ScheduleEntity entity = scheduleMapper.toScheduleEntity(object);
		long id=entity.getId();
		if(id!=0)
			scheduleRepo.findById(id).orElseThrow(()->new EntityNotFoundException("INVALID_SCHEDULE"));
		Optional<EmployeeEntity> emOptional = employeeRepository.findById(object.getEmployeeID());
		if (!emOptional.isPresent())
			throw new EntityNotFoundException("INVALID_EMPLOYEE");
		entity.setEmployee(emOptional.get());
		return scheduleMapper.toScheduleResponse(scheduleRepo.save(entity));
	}

}
