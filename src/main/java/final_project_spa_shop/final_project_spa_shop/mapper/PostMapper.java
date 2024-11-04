package final_project_spa_shop.final_project_spa_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import final_project_spa_shop.final_project_spa_shop.dto.request.PostRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.PostResponse;
import final_project_spa_shop.final_project_spa_shop.entity.PostEntity;

@Mapper(componentModel = "spring")
public interface PostMapper {
	@Mapping(target = "customer",ignore = true)
	@Mapping(target = "createdAt",ignore = true)
	PostEntity toPostEntity(PostRequest request);
	@Mapping(target = "customerID",source = "customer.id")
	@Mapping(target = "customerName",source = "customer.fullName")
	PostResponse toPostResponse(PostEntity entity);
}
