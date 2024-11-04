package final_project_spa_shop.final_project_spa_shop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;
import final_project_spa_shop.final_project_spa_shop.entity.ServiceEntity;
import final_project_spa_shop.final_project_spa_shop.service.IService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/service")
public class ServiceController {
	@Autowired
	private IService<ServiceEntity, ServiceEntity> serviceSer;

	@GetMapping("/services")
	public ResponseEntity<ApiResponse<List<ServiceEntity>>> getAll() {
		return new ResponseEntity<>(new ApiResponse<>(serviceSer.getAll()), HttpStatus.OK);
	}

//	@DeleteMapping("")
//	public ResponseEntity<ServiceEntity> delete(@RequestParam long id){
//		return new ResponseEntity<>(serviceSer.delete(id),HttpStatus.NO_CONTENT);
//	}
	@PostMapping("")
	public ResponseEntity<ApiResponse<ServiceEntity>> save(@Valid @RequestBody ServiceEntity appoint) {
		return new ResponseEntity<>(new ApiResponse<>(serviceSer.save(appoint)), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<ServiceEntity>> save(@PathVariable long id,
			@Valid @RequestBody ServiceEntity appoint) {
		appoint.setId(id);
		return new ResponseEntity<>(new ApiResponse<>(serviceSer.save(appoint)), HttpStatus.CREATED);
	}

}
