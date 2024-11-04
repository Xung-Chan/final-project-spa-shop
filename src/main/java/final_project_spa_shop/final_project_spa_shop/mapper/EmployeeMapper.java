package final_project_spa_shop.final_project_spa_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import final_project_spa_shop.final_project_spa_shop.dto.request.EmployeeRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.EmployeeResponse;
import final_project_spa_shop.final_project_spa_shop.entity.EmployeeEntity;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	EmployeeResponse toEmployeeResponse(EmployeeEntity entity);
	@Mapping(target = "updated_at",ignore = true)
	@Mapping(target = "role",ignore = true)
	@Mapping(target = "permissions",ignore = true)
	EmployeeEntity toEmployeeEntity(EmployeeRequest request);
	
}
