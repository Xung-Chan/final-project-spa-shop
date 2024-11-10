package final_project_spa_shop.final_project_spa_shop.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import final_project_spa_shop.final_project_spa_shop.dto.request.AccountRequest;
import final_project_spa_shop.final_project_spa_shop.dto.respone.AccountResponse;
import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;
import final_project_spa_shop.final_project_spa_shop.service.IAccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
	IAccountService accountService;
	@PostMapping("/changePassword")
	public ResponseEntity<ApiResponse<AccountResponse>> changePassword(@RequestBody AccountRequest  request) {
		return new ResponseEntity<>(new ApiResponse<>(accountService.changePassword(request)), HttpStatus.OK);
	}
	@PostMapping("/checkPassword")
	public ResponseEntity<ApiResponse<AccountResponse>> checkPassword(@RequestBody AccountRequest  request) {
		return new ResponseEntity<>(new ApiResponse<>(accountService.checkPassword(request)), HttpStatus.OK);
	}
}
