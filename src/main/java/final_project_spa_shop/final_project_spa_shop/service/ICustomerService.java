package final_project_spa_shop.final_project_spa_shop.service;

import java.util.List;

import final_project_spa_shop.final_project_spa_shop.dto.request.AccountRequest;
import final_project_spa_shop.final_project_spa_shop.dto.request.CustomerRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.CustomerResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.PaginationResponse;

public interface ICustomerService {
	public List<CustomerResponse> getAll();

	public CustomerResponse loadUserByUsername(String username);

	public CustomerResponse loadMyInformation();

	public CustomerResponse getById(long id);

	public CustomerResponse delete(long id);

	public CustomerResponse create(AccountRequest accountRequest, CustomerRequest customerRequest);

	public CustomerResponse update(CustomerRequest customerRequest);

	public List<CustomerResponse> getAllByPage(int page);

	public PaginationResponse getTotalPage() ;


}
