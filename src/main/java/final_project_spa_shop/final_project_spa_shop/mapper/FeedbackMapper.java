package final_project_spa_shop.final_project_spa_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import final_project_spa_shop.final_project_spa_shop.dto.request.FeedbackRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.FeedbackResponse;
import final_project_spa_shop.final_project_spa_shop.entity.FeedbackEntity;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
	@Mapping(target = "customerID",source = "customer.id")
	@Mapping(target = "name",source = "customer.fullName")
	@Mapping(target = "imagePath",source = "customer.imagePath")
	FeedbackResponse toFeedbackResponse(FeedbackEntity entity);
	@Mapping (target = "customer",ignore = true)
	FeedbackEntity toFeedbackEntity(FeedbackRequest request);
}
