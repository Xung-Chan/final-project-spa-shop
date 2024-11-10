package final_project_spa_shop.final_project_spa_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import final_project_spa_shop.final_project_spa_shop.dto.request.BillRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.BillResponse;
import final_project_spa_shop.final_project_spa_shop.entity.BillEntity;

@Mapper(componentModel = "spring")
public interface BillMapper {
	@Mapping(target = "customerName",source = "customer.fullName")
	@Mapping(target = "services",ignore = true)
	public BillResponse toBillResponse(BillEntity entity);
	@Mapping(target = "customer",ignore = true)
	@Mapping(target = "services",ignore = true)
	@Mapping(target = "cost",ignore = true)
	public BillEntity toBillEntity(BillRequest request);
}
