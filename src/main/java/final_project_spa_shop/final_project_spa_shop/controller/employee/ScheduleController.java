package final_project_spa_shop.final_project_spa_shop.controller.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import final_project_spa_shop.final_project_spa_shop.dto.request.ScheduleRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ScheduleResponse;
import final_project_spa_shop.final_project_spa_shop.service.IService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	@Autowired
	private IService<ScheduleResponse,ScheduleRequest> scheduleSer;
	@GetMapping("/schedules")
	public ResponseEntity<List<ScheduleResponse>> getAll(){
		return new ResponseEntity<>(scheduleSer.getAll(),HttpStatus.OK);
	}
}
