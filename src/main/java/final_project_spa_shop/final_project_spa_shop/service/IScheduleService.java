package final_project_spa_shop.final_project_spa_shop.service;

import java.util.List;

import final_project_spa_shop.final_project_spa_shop.dto.request.ScheduleRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ScheduleResponse;

public interface IScheduleService extends IService<ScheduleResponse, ScheduleRequest> {
	public ScheduleResponse check(long id);

	public List<ScheduleResponse> getTomorow();

	public List<ScheduleResponse> getToday();

	public List<ScheduleResponse> mySchedule();

}
