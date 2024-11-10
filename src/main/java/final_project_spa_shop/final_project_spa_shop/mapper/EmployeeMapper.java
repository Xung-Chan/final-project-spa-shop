package final_project_spa_shop.final_project_spa_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import final_project_spa_shop.final_project_spa_shop.dto.request.EmployeeRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.EmployeeResponse;
import final_project_spa_shop.final_project_spa_shop.entity.EmployeeEntity;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	@Mapping(target = "image",source = "imagePath")
	@Mapping(target = "username",source = "account.username")
	@Mapping(target = "password",source = "account.password")
	EmployeeResponse toEmployeeResponse(EmployeeEntity entity);
	@Mapping(target = "updated_at",ignore = true)
	@Mapping(target = "imagePath",ignore = true)
	@Mapping(target = "account",ignore = true)
	EmployeeEntity toEmployeeEntity(EmployeeRequest request);
}
