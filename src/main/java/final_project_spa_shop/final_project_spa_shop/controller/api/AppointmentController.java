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

import final_project_spa_shop.final_project_spa_shop.dto.request.AppointmentRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AppointmentResponse;
import final_project_spa_shop.final_project_spa_shop.service.IAppointmentSevice;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	@Autowired
	private IAppointmentSevice appointmentSer;

	@GetMapping("/appointments")
	public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getAll() {
		return new ResponseEntity<>(new ApiResponse<>(appointmentSer.getAll()), HttpStatus.OK);
	}

	@GetMapping("/appointments/{customerID}")
	public ResponseEntity<ApiResponse<List<AppointmentResponse>>> getByCustomerId(@PathVariable long customerID) {
		return new ResponseEntity<>(new ApiResponse<>( appointmentSer.getAllByCustomerID(customerID)), HttpStatus.OK);
	}

	@DeleteMapping("")
	public ResponseEntity<Object> delete(@RequestParam long id) {
		return new ResponseEntity<>(appointmentSer.delete(id),HttpStatus.NO_CONTENT);
	}

	@PostMapping("")
	public ResponseEntity<ApiResponse<AppointmentResponse>> save(@Valid @RequestBody AppointmentRequest appoint) {
		return new ResponseEntity<>(new ApiResponse<>( appointmentSer.save(appoint)), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<AppointmentResponse>> save(@PathVariable long id,@Valid @RequestBody AppointmentRequest appoint) {
		appoint.setId(id);
		return new ResponseEntity<>(new ApiResponse<>( appointmentSer.save(appoint)), HttpStatus.CREATED);
	}

}
