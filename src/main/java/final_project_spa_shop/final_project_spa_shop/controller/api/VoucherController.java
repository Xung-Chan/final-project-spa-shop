package final_project_spa_shop.final_project_spa_shop.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;
import final_project_spa_shop.final_project_spa_shop.entity.VoucherEntity;
import final_project_spa_shop.final_project_spa_shop.service.IVoucherSerice;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
	@Autowired
	private IVoucherSerice voucherSerice;

	@GetMapping("/vouchers")
	public ResponseEntity<ApiResponse<List<VoucherEntity>>> getAll() {
		return new ResponseEntity<>(new ApiResponse<>(voucherSerice.getAll()), HttpStatus.OK);
	}

	@DeleteMapping("")
	public ResponseEntity<VoucherEntity> delete(@RequestParam long id) {
		return new ResponseEntity<>(voucherSerice.delete(id), HttpStatus.NO_CONTENT);
	}

	@PostMapping("")
	public ResponseEntity<ApiResponse<VoucherEntity>> save(@Valid @RequestBody VoucherEntity request) {
		return new ResponseEntity<>(new ApiResponse<>(voucherSerice.save(request)), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<VoucherEntity>> update(@PathVariable long id,
			@Valid @RequestBody VoucherEntity request) {
		request.setId(id);
		return new ResponseEntity<>(new ApiResponse<>(voucherSerice.save(request)), HttpStatus.CREATED);
	}
}
