package final_project_spa_shop.final_project_spa_shop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	ResponseEntity<ApiResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		String errorKey = ex.getFieldError().getDefaultMessage();
		ErrorCode errorCode;
		try {
			errorCode = ErrorCode.valueOf(errorKey);
		} catch (Exception e) {
			errorCode = ErrorCode.INVALID_EXCEPTION;
		}
		return ResponseEntity.badRequest().body(new ApiResponse<>(errorCode.getCode(), errorCode.getMessage()));
	}

	@ExceptionHandler(value = Exception.class)
	ResponseEntity<ApiResponse> handleException(Exception ex) {
		String errorKey = ex.getMessage();
		System.out.println(ex.getMessage());
		ErrorCode errorCode;
		try {
			errorCode = ErrorCode.valueOf(errorKey);
		} catch (Exception e) {
			errorCode = ErrorCode.INVALID_EXCEPTION;
		}
		return ResponseEntity.badRequest().body(new ApiResponse<>(errorCode.getCode(), errorCode.getMessage()));
	}

}
