package final_project_spa_shop.final_project_spa_shop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import final_project_spa_shop.final_project_spa_shop.dto.request.EmployeeRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.EmployeeResponse;
import final_project_spa_shop.final_project_spa_shop.service.IEmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	private IEmployeeService employeeService;

	@GetMapping("/employees")
	public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getAll() {
		return new ResponseEntity<>(new ApiResponse<>(employeeService.getAll()), HttpStatus.OK);
	}
	@GetMapping("/limit")
	public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getAllLimit(@RequestParam int limit) {
		return new ResponseEntity<>(new ApiResponse<>(employeeService.getAllLimit(limit)), HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeResponse>> getById(@PathVariable long id) {
		return new ResponseEntity<>(new ApiResponse<>(employeeService.getById(id)), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<ApiResponse<EmployeeResponse>> save(@Valid @ModelAttribute EmployeeRequest request) {
		return new ResponseEntity<>(new ApiResponse<>(employeeService.save(request)), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeResponse>> save(@PathVariable long id,
			@Valid @ModelAttribute EmployeeRequest request) {
		request.setId(id);
		return new ResponseEntity<>(new ApiResponse<>(employeeService.save(request)), HttpStatus.CREATED);
	}
}
