package final_project_spa_shop.final_project_spa_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import final_project_spa_shop.final_project_spa_shop.entity.RoleEntity;
import final_project_spa_shop.final_project_spa_shop.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepo;
	public List<RoleEntity> getAll(){
		return roleRepo.findAll(); 
	}
}
