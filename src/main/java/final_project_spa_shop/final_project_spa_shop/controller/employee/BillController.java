package final_project_spa_shop.final_project_spa_shop.controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import final_project_spa_shop.final_project_spa_shop.dto.request.AppointmentRequest;
import final_project_spa_shop.final_project_spa_shop.dto.request.BillRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AppointmentResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.BillResponse;
import final_project_spa_shop.final_project_spa_shop.service.IService;

@RestController
@RequestMapping("/bill")
public class BillController {
	@Autowired
	private IService<BillResponse,BillRequest>  billIService;
	@PostMapping("")
	public ResponseEntity<BillResponse> save(@RequestBody BillRequest appoint){
		return new ResponseEntity<>(billIService.save(appoint),HttpStatus.CREATED);
	}
}
