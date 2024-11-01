package final_project_spa_shop.final_project_spa_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import final_project_spa_shop.final_project_spa_shop.dto.request.BillRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.BillResponse;
import final_project_spa_shop.final_project_spa_shop.entity.BillEntity;

@Mapper(componentModel = "spring")
public interface BillMapper {
	@Mapping(target = "employeeName",source = "employee.fullName")
	@Mapping(target = "customerName",source = "customer.fullName")
	@Mapping(target = "discount",source = "voucher.percent")
	public BillResponse toBillResponse(BillEntity entity);
	@Mapping(target = "employee",ignore = true)
	@Mapping(target = "customer",ignore = true)
	@Mapping(target = "voucher",ignore = true)
	@Mapping(target = "services",ignore = true)
	public BillEntity toBillEntity(BillRequest request);
}
