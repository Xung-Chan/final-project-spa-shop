package final_project_spa_shop.final_project_spa_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import final_project_spa_shop.final_project_spa_shop.entity.RoleEntity;
import final_project_spa_shop.final_project_spa_shop.service.RoleService;


@RestController
@RequestMapping("/user")
public class RoleController {
	@Autowired
	RoleService roleServ;
	@GetMapping("")
	public List<RoleEntity> getAll() {
		return roleServ.getAll();
	}
	
}
