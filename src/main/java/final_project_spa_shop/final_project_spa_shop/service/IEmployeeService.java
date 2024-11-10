package final_project_spa_shop.final_project_spa_shop.service;

import java.util.List;

import final_project_spa_shop.final_project_spa_shop.dto.request.AccountRequest;
import final_project_spa_shop.final_project_spa_shop.dto.request.EmployeeRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.EmployeeResponse;

public interface IEmployeeService{
	public EmployeeResponse getById(long id);
	public List<EmployeeResponse> getAllLimit(int limit) ;
	public List<EmployeeResponse> getAll();
	public EmployeeResponse loadUserByUsername(String username);
	public EmployeeResponse loadMyInformation();
	public EmployeeResponse create(AccountRequest accountRequest,EmployeeRequest employeeRequest);
	public EmployeeResponse update(EmployeeRequest employeeRequest);
}
