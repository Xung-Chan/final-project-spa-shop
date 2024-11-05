package final_project_spa_shop.final_project_spa_shop.service;


import java.util.List;

import final_project_spa_shop.final_project_spa_shop.dto.request.ServiceRequest;
import final_project_spa_shop.final_project_spa_shop.entity.ServiceEntity;

public interface IServiceSerice extends IService<ServiceEntity, ServiceRequest>{
//	public List<ServiceEntity> getAll() ;
//	public ServiceEntity delete(long id) ;	
//	public ServiceEntity save(String name, String description, MultipartFile file);
//	public ServiceEntity save(long id,String name, String description, MultipartFile file);
	public List<ServiceEntity> getAllLimit(int limit) ;
}
