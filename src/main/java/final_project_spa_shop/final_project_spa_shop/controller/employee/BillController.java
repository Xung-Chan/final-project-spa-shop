package final_project_spa_shop.final_project_spa_shop.controller.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import final_project_spa_shop.final_project_spa_shop.dto.request.BillRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.BillResponse;
import final_project_spa_shop.final_project_spa_shop.service.IBillService;

@RestController
@RequestMapping("/bill")
public class BillController {
	@Autowired
	@Qualifier("billService")
	private IBillService  billIService;
	@GetMapping("/bills")
	public ResponseEntity<List<BillResponse>> getAll(){
		return new ResponseEntity<>(billIService.getAll(),HttpStatus.OK);
	}
	@GetMapping("/bills/{customerID}")
	public ResponseEntity<List<BillResponse>> getAllByCustomerID(@PathVariable long customerID){
		return new ResponseEntity<>(billIService.getAllByCustomerID(customerID),HttpStatus.OK);
	}
	
	@DeleteMapping("")
	public ResponseEntity<BillResponse> delete(@RequestParam long id){
		return new ResponseEntity<>(billIService.delete(id),HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("")
	public ResponseEntity<BillResponse> save(@RequestBody BillRequest bill){
		return new ResponseEntity<>(billIService.save(bill),HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<BillResponse> update(@PathVariable long id, @RequestBody BillRequest bill){
		bill.setId(id);
		return new ResponseEntity<>(billIService.save(bill),HttpStatus.CREATED);
	}
}
