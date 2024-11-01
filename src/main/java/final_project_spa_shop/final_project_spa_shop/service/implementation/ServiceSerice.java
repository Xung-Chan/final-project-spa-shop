package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.entity.ServiceEntity;
import final_project_spa_shop.final_project_spa_shop.repository.ServiceRepository;
import final_project_spa_shop.final_project_spa_shop.service.IService;
@Service
public class ServiceSerice implements IService<ServiceEntity,ServiceEntity>{
	@Autowired
	private ServiceRepository serviceRepo;

	@Override
	public List<ServiceEntity> getAll() {
		return serviceRepo.findAll();
	}

	@Override
	public ServiceEntity delete(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceEntity save(ServiceEntity object) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
