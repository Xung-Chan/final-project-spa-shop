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

import final_project_spa_shop.final_project_spa_shop.dto.request.FeedbackRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.FeedbackResponse;
import final_project_spa_shop.final_project_spa_shop.service.IFeedbackSevice;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	@Autowired
	private IFeedbackSevice feedbackSevice;

	@GetMapping("/feedbacks")
	public ResponseEntity<ApiResponse<List<FeedbackResponse>>> getAll() {
		return new ResponseEntity<>(new ApiResponse<>(feedbackSevice.getAll()), HttpStatus.OK);
	}

	@DeleteMapping("")
	public ResponseEntity<Object> delete(@RequestParam long id) {
		return new ResponseEntity<>(feedbackSevice.delete(id), HttpStatus.NO_CONTENT);
	}

	@PostMapping("")
	public ResponseEntity<ApiResponse<FeedbackResponse>> save(@Valid @RequestBody FeedbackRequest request) {
		return new ResponseEntity<>(new ApiResponse<>(feedbackSevice.save(request)), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<FeedbackResponse>> save(@PathVariable long id,
			@Valid @RequestBody FeedbackRequest request) {
		request.setId(id);
		return new ResponseEntity<>(new ApiResponse<>(feedbackSevice.save(request)), HttpStatus.CREATED);
	}

}
