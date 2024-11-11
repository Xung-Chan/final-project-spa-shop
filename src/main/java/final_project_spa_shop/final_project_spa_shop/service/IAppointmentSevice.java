package final_project_spa_shop.final_project_spa_shop.service;

import java.util.List;

import final_project_spa_shop.final_project_spa_shop.dto.request.AppointmentRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AppointmentResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.PaginationResponse;

public interface IAppointmentSevice extends IService<AppointmentResponse, AppointmentRequest> {
	public List<AppointmentResponse> getAllByCustomerID(long id);

	public List<AppointmentResponse> searchByFullName(String fullName);

	public AppointmentResponse pay(long id);

	public AppointmentResponse findById(long id);

	public PaginationResponse getTotalPage();

	public PaginationResponse getTotalPageToday();

	public PaginationResponse getTotalPageTomorow();

	public List<AppointmentResponse> getAll(int page);

	public List<AppointmentResponse> getToday();

	public List<AppointmentResponse> getTomorow();

	public List<AppointmentResponse> myAppointment();

}
