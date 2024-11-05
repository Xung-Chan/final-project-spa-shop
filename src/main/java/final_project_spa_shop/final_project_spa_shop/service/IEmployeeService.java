package final_project_spa_shop.final_project_spa_shop.service;

import java.util.List;

import final_project_spa_shop.final_project_spa_shop.dto.request.EmployeeRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.EmployeeResponse;

public interface IEmployeeService extends IService<EmployeeResponse, EmployeeRequest> {
	public EmployeeResponse getById(long id);
	public List<EmployeeResponse> getAllLimit(int limit) ;
}
