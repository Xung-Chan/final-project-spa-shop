package final_project_spa_shop.final_project_spa_shop.service;

import java.util.List;

import final_project_spa_shop.final_project_spa_shop.dto.request.ServiceRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.PaginationResponse;
import final_project_spa_shop.final_project_spa_shop.entity.ServiceEntity;

public interface IServiceSerice  {
	public List<ServiceEntity> getAll(int page);

	public ServiceEntity getById(long id);

	public List<ServiceEntity> getAllLimit(int limit);

	public PaginationResponse getTotalPage();

	public ServiceEntity update(ServiceRequest request);

	public List<ServiceEntity> getAll();

	public ServiceEntity save(ServiceRequest object) ;
	
	

}
