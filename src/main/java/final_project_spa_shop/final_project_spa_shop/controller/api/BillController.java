package final_project_spa_shop.final_project_spa_shop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import final_project_spa_shop.final_project_spa_shop.dto.request.BillRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.BillResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.PaginationResponse;
import final_project_spa_shop.final_project_spa_shop.service.IBillService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/bill")
public class BillController {
	@Autowired
	@Qualifier("billService")
	private IBillService billIService;

	@GetMapping("/bills")
	public ResponseEntity<ApiResponse<List<BillResponse>>> getAll() {
		return new ResponseEntity<>(new ApiResponse<>(billIService.getAll()), HttpStatus.OK);
	}
	@GetMapping("/all/{page}")
	public ResponseEntity<ApiResponse<List<BillResponse>>> getAllByPage(@PathVariable int page) {
		return new ResponseEntity<>(new ApiResponse<>(billIService.getAll(page)), HttpStatus.OK);
	}
	
	@GetMapping("/totalPage")
	public ResponseEntity<ApiResponse<PaginationResponse>> getTotalPage() {
		return new ResponseEntity<>(new ApiResponse<>(billIService.getTotalPage()), HttpStatus.OK);
	}
	
	@GetMapping("/thisYear")
	public ResponseEntity<ApiResponse<List<BillResponse>>> getPaidThisYear() {
		return new ResponseEntity<>(new ApiResponse<>(billIService.getAllThisYear()), HttpStatus.OK);
	}
	@GetMapping("/thisMonth")
	public ResponseEntity<ApiResponse<List<BillResponse>>> getPaidThisMonth() {
		return new ResponseEntity<>(new ApiResponse<>(billIService.getAllThisMonth()), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<BillResponse>> getById(@PathVariable long id) {
		return new ResponseEntity<>(new ApiResponse<>(billIService.getById(id)), HttpStatus.OK);
	}


	@DeleteMapping("")
	public ResponseEntity<BillResponse> delete(@RequestParam long id) {
		return new ResponseEntity<>(billIService.delete(id), HttpStatus.NO_CONTENT);
	}

	@PostMapping("")
	public ResponseEntity<ApiResponse<BillResponse>> save(@Valid @ModelAttribute BillRequest bill) {
		return new ResponseEntity<>(new ApiResponse<>(billIService.create(bill)), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<BillResponse>> pay(@PathVariable long id) {
		return new ResponseEntity<>(new ApiResponse<>(billIService.pay(id)), HttpStatus.CREATED);
	}
}
