package final_project_spa_shop.final_project_spa_shop.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.entity.RoleEntity;
import final_project_spa_shop.final_project_spa_shop.repository.RoleRepository;
import final_project_spa_shop.final_project_spa_shop.service.IService;

@Service
public class RoleService implements IService<RoleEntity,RoleEntity> {
	@Autowired
	RoleRepository roleRepo;
	public List<RoleEntity> getAll(){
		return roleRepo.findAll(); 
	}
	public RoleEntity getSingle(long id) {
		return roleRepo.findById(id).get();
	}
	@Override
	public RoleEntity delete(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RoleEntity save(RoleEntity object) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
