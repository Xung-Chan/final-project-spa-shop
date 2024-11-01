package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.dto.request.ScheduleRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ScheduleResponse;
import final_project_spa_shop.final_project_spa_shop.mapper.ScheduleMapper;
import final_project_spa_shop.final_project_spa_shop.repository.ScheduleRepository;
import final_project_spa_shop.final_project_spa_shop.service.IService;
@Service
public class ScheduleService implements IService<ScheduleResponse,ScheduleRequest> {
	@Autowired
	private ScheduleMapper scheduleMapper;
	@Autowired
	private ScheduleRepository scheduleRepo;
	@Override
	public List<ScheduleResponse> getAll() {
		return scheduleRepo.findAll().stream().map(scheduleMapper::toScheduleResponse).toList();
	}
	@Override
	public ScheduleResponse delete(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ScheduleResponse save(ScheduleRequest object) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
