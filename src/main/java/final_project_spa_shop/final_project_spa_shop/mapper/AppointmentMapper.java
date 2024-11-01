package final_project_spa_shop.final_project_spa_shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import final_project_spa_shop.final_project_spa_shop.dto.request.AppointmentRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AppointmentResponse;
import final_project_spa_shop.final_project_spa_shop.entity.AppointmentEntity;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
	@Mapping(target = "customerID",source = "customer.id")
	@Mapping(target = "customerName",source = "customer.fullName")
	AppointmentResponse toAppointmentResponse(AppointmentEntity entity);
	@Mapping (target = "customer",ignore = true)
	AppointmentEntity toAppointmentEntity(AppointmentRequest request);
}
