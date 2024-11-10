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
import org.springframework.web.bind.annotation.RestController;

import final_project_spa_shop.final_project_spa_shop.dto.request.AccountRequest;
import final_project_spa_shop.final_project_spa_shop.dto.request.CustomerRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.CustomerResponse;
import final_project_spa_shop.final_project_spa_shop.service.ICustomerService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerController {
	ICustomerService customerService;

	@GetMapping("/customers")
	public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAll() {
		return new ResponseEntity<>(new ApiResponse<>(customerService.getAll()), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CustomerResponse>> getById(@PathVariable long id) {
		return new ResponseEntity<>(new ApiResponse<>(customerService.getById(id)), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<ApiResponse<CustomerResponse>> save(@Valid @ModelAttribute AccountRequest accountRequest,
			@Valid @ModelAttribute CustomerRequest customerRequest) {
		return new ResponseEntity<>(new ApiResponse<>(customerService.create(accountRequest, customerRequest)),
				HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<CustomerResponse>> save(@PathVariable long id,
			@Valid @ModelAttribute CustomerRequest request) {
		request.setId(id);
		return new ResponseEntity<>(new ApiResponse<>(customerService.update(request)), HttpStatus.CREATED);
	}

	@GetMapping("/myInformation")
	public ResponseEntity<ApiResponse<CustomerResponse>> getMyInformation() {
		return new ResponseEntity<>(new ApiResponse<>(customerService.loadMyInformation()), HttpStatus.OK);
	}
}
