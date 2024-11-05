package final_project_spa_shop.final_project_spa_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import final_project_spa_shop.final_project_spa_shop.dto.request.ServiceRequest;
import final_project_spa_shop.final_project_spa_shop.entity.ServiceEntity;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
	@Mapping(target = "imagePath",ignore = true)
	ServiceEntity toServiceEntity(ServiceRequest request);
}
