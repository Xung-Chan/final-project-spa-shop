package final_project_spa_shop.final_project_spa_shop.service;

import java.util.List;

import final_project_spa_shop.final_project_spa_shop.dto.request.AppointmentRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AppointmentResponse;

public interface IAppointmentSevice extends IService<AppointmentResponse, AppointmentRequest> {
	public List<AppointmentResponse> getAllByCustomerID(long id) ;
}