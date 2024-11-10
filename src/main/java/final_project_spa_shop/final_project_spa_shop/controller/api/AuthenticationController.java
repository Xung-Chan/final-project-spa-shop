package final_project_spa_shop.final_project_spa_shop.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import final_project_spa_shop.final_project_spa_shop.dto.request.AuthenticationRequest;
import final_project_spa_shop.final_project_spa_shop.dto.request.IntrospectRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AuthenticationResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.IntrospectResponse;
import final_project_spa_shop.final_project_spa_shop.service.IAuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
	IAuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(
			@Valid @RequestBody AuthenticationRequest request) {
		AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
//		String token = authenticationResponse.getToken();
//		var cookie = new Cookie("token", token);
//		cookie.setPath("/");
//		var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//		attributes.getResponse().addCookie(cookie);
		return new ResponseEntity<>(new ApiResponse<>(authenticationResponse), HttpStatus.ACCEPTED);
	}
	

	@PostMapping("/introspect")
	public ResponseEntity<ApiResponse<IntrospectResponse>> introspect(@Valid @RequestBody IntrospectRequest request) {
		return new ResponseEntity<>(new ApiResponse<>(authenticationService.introspect(request)), HttpStatus.OK);
	}
}
