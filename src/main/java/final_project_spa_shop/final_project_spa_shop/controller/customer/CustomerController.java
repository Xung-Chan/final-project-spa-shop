package final_project_spa_shop.final_project_spa_shop.controller.customer;

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

import final_project_spa_shop.final_project_spa_shop.dto.request.CustomerRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.CustomerResponse;
import final_project_spa_shop.final_project_spa_shop.service.ICustomerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	private ICustomerService customerService;

	@GetMapping("/customers")
	public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAll() {
		return new ResponseEntity<>(new ApiResponse<>(customerService.getAll()), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CustomerResponse>> getById(@PathVariable long id) {
		return new ResponseEntity<>(new ApiResponse<>(customerService.getById(id)), HttpStatus.OK);
	}

//	@DeleteMapping("")
//	public ResponseEntity<CustomerRessponse> delete(@RequestParam long id){
//		return new ResponseEntity<>(customerService.delete(id),HttpStatus.NO_CONTENT);
//	}
	@PostMapping("")
	public ResponseEntity<ApiResponse<CustomerResponse>> save(@Valid @RequestBody CustomerRequest request) {
		return new ResponseEntity<>(new ApiResponse<>(customerService.save(request)), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<CustomerResponse>> save(@PathVariable long id,
			@Valid @RequestBody CustomerRequest request) {
		request.setId(id);
		return new ResponseEntity<>(new ApiResponse<>(customerService.save(request)), HttpStatus.CREATED);
	}
}
