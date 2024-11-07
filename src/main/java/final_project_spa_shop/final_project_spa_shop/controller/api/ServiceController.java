package final_project_spa_shop.final_project_spa_shop.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import final_project_spa_shop.final_project_spa_shop.dto.request.ServiceRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;
import final_project_spa_shop.final_project_spa_shop.entity.ServiceEntity;
import final_project_spa_shop.final_project_spa_shop.service.IServiceSerice;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/service")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ServiceController {
	IServiceSerice serviceSer;

	@GetMapping("/services")
	public ResponseEntity<ApiResponse<List<ServiceEntity>>> getAll() {
		return new ResponseEntity<>(new ApiResponse<>(serviceSer.getAll()), HttpStatus.OK);
	}
	@GetMapping("/limit")
	public ResponseEntity<ApiResponse<List<ServiceEntity>>> getAllLimit(@RequestParam int limit) {
		return new ResponseEntity<>(new ApiResponse<>(serviceSer.getAllLimit(limit)), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<ApiResponse<ServiceEntity>> save(@ModelAttribute ServiceRequest request) {
		return new ResponseEntity<>(new ApiResponse<>(serviceSer.save(request)), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<ServiceEntity>> save(@PathVariable long id,@ModelAttribute ServiceRequest request) {
		request.setId(id);
		return new ResponseEntity<>(new ApiResponse<>(serviceSer.save(request)), HttpStatus.CREATED);
	}

}
