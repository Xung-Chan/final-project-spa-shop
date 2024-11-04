package final_project_spa_shop.final_project_spa_shop.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import final_project_spa_shop.final_project_spa_shop.dto.request.PostRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.PostResponse;
import final_project_spa_shop.final_project_spa_shop.service.IPostService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
	IPostService postService;

	@GetMapping("/posts")
	public ResponseEntity<ApiResponse<List<PostResponse>>> getAll() {
		return new ResponseEntity<>(new ApiResponse<>(postService.getAll()), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<ApiResponse<PostResponse>> save(@Valid @RequestBody PostRequest request) {
		return new ResponseEntity<>(new ApiResponse<>(postService.save(request)), HttpStatus.OK);
	}

}
