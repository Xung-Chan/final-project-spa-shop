package final_project_spa_shop.final_project_spa_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import final_project_spa_shop.final_project_spa_shop.dto.request.CustomerRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.CustomerResponse;
import final_project_spa_shop.final_project_spa_shop.entity.CustomerEntity;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	@Mapping(target = "image",source = "imagePath")
	CustomerResponse toCustomerRessponse(CustomerEntity entity);
	@Mapping(target = "updated_at",ignore = true)
	@Mapping(target = "role",ignore = true)
	@Mapping(target = "imagePath",ignore = true)
	CustomerEntity toCustomerEntity(CustomerRequest request);
	
}
