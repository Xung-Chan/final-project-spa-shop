package final_project_spa_shop.final_project_spa_shop.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import final_project_spa_shop.final_project_spa_shop.entity.RoleEntity;
import final_project_spa_shop.final_project_spa_shop.service.implementation.RoleService;


@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	RoleService roleServ;
	@GetMapping("roles")
	public List<RoleEntity> getAll() {
		return roleServ.getAll();
	}
	
}
