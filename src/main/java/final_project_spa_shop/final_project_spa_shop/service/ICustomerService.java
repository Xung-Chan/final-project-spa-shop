package final_project_spa_shop.final_project_spa_shop.service;

import final_project_spa_shop.final_project_spa_shop.dto.request.CustomerRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.CustomerResponse;

public interface ICustomerService extends IService<CustomerResponse, CustomerRequest> {
	public CustomerResponse getById(long id);
}
