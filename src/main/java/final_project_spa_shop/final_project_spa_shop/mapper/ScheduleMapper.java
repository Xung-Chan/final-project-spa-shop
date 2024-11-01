package final_project_spa_shop.final_project_spa_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import final_project_spa_shop.final_project_spa_shop.dto.request.ScheduleRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ScheduleResponse;
import final_project_spa_shop.final_project_spa_shop.entity.ScheduleEntity;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

	@Mapping(target = "employeeID", source = "employee.id")
	@Mapping(target = "employeeName", source = "employee.fullName")
	public ScheduleResponse toScheduleResponse(ScheduleEntity schedule);

	@Mapping(target = "employee", ignore = true)
	public ScheduleEntity toScheduleEntity(ScheduleRequest schedule);
}