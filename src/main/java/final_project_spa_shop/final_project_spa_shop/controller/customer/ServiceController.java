package final_project_spa_shop.final_project_spa_shop.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import final_project_spa_shop.final_project_spa_shop.entity.ServiceEntity;
import final_project_spa_shop.final_project_spa_shop.service.IService;

@RestController
@RequestMapping("/service")
public class ServiceController {
	@Autowired
	private IService<ServiceEntity,ServiceEntity> serviceSer;
	@GetMapping("/services")
	public ResponseEntity<List<ServiceEntity>> getAll(){
		return new ResponseEntity<>(serviceSer.getAll(),HttpStatus.OK);
	}
	
//	@PostMapping("/services")
//	public ResponseEntity<ServiceEntity> delete(@RequestParam long id){
//		return new ResponseEntity<>(serviceSer.delete(id),HttpStatus.NO_CONTENT);
//	}
	
}
