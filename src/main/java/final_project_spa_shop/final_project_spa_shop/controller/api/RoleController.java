package final_project_spa_shop.final_project_spa_shop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;
import final_project_spa_shop.final_project_spa_shop.entity.RoleEntity;
import final_project_spa_shop.final_project_spa_shop.service.implementation.RoleService;


@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	RoleService roleServ;
	@GetMapping("roles")
	public ResponseEntity<ApiResponse<List<RoleEntity>>> getAll() {
		return new ResponseEntity<>(new ApiResponse<>( roleServ.getAll()),HttpStatus.OK);
	}
	
}
