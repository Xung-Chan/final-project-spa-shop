package final_project_spa_shop.final_project_spa_shop.controller.customer;

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
import final_project_spa_shop.final_project_spa_shop.dto.respone.AppointmentResponse;
import final_project_spa_shop.final_project_spa_shop.entity.ServiceEntity;
import final_project_spa_shop.final_project_spa_shop.service.IService;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	@Autowired
	private IService<AppointmentResponse,AppointmentRequest> appointmentSer;
	@GetMapping("/appointments")
	public ResponseEntity<List<AppointmentResponse>> getAll(){
		return new ResponseEntity<> (appointmentSer.getAll(),HttpStatus.OK);
	}
	@DeleteMapping("")
	public ResponseEntity<AppointmentResponse> delete(@RequestParam long id){
		return new ResponseEntity<>(appointmentSer.delete(id),HttpStatus.NO_CONTENT);
	}
	@PostMapping("")
	public ResponseEntity<AppointmentResponse> save(@RequestBody AppointmentRequest appoint){
		return new ResponseEntity<>(appointmentSer.save(appoint),HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<AppointmentResponse> save(@PathVariable long id, @RequestBody AppointmentRequest appoint){
		appoint.setId(id);	
		return new ResponseEntity<>(appointmentSer.save(appoint),HttpStatus.CREATED);
	}
	
}
