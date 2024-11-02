package final_project_spa_shop.final_project_spa_shop.controller.employee;

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

import final_project_spa_shop.final_project_spa_shop.dto.request.ScheduleRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ScheduleResponse;
import final_project_spa_shop.final_project_spa_shop.service.IScheduleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	@Autowired
	private IScheduleService scheduleSer;

	@GetMapping("/schedules")
	public ResponseEntity<ApiResponse<List<ScheduleResponse>>> getAll() {
		return new ResponseEntity<>(new ApiResponse<>(scheduleSer.getAll()), HttpStatus.OK);
	}

	@DeleteMapping("")
	public ResponseEntity<ScheduleResponse> delete(@RequestParam long id) {
		return new ResponseEntity<>(scheduleSer.delete(id), HttpStatus.NO_CONTENT);
	}

	@PostMapping("")
	public ResponseEntity<ApiResponse<ScheduleResponse>> save(@Valid @RequestBody ScheduleRequest request) {
		return new ResponseEntity<>(new ApiResponse<>(scheduleSer.save(request)), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<ScheduleResponse>> update(@PathVariable long id,
			@RequestBody ScheduleRequest bill) {
		bill.setId(id);
		return new ResponseEntity<>(new ApiResponse<>(scheduleSer.save(bill)), HttpStatus.CREATED);
	}
}
