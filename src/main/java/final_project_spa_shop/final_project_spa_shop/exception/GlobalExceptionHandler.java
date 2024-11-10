package final_project_spa_shop.final_project_spa_shop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import final_project_spa_shop.final_project_spa_shop.dto.respone.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler{
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
	@ExceptionHandler(value = HandlerMethodValidationException.class)
	ResponseEntity<ApiResponse> handleethodValidationException(HandlerMethodValidationException ex) {
		String errorKey = ex.getMessage();
		System.out.println(errorKey);
		ErrorCode errorCode;
		try {
			errorCode = ErrorCode.valueOf(errorKey);
		} catch (Exception e) {
			errorCode = ErrorCode.INVALID_EXCEPTION;
		}
		return ResponseEntity.badRequest().body(new ApiResponse<>(errorCode.getCode(), errorCode.getMessage()));
	}

	
	
	@ExceptionHandler(value = NoResourceFoundException.class)
	public ModelAndView handleException(NoResourceFoundException ex) {
	    String errorKey = ex.getMessage();
	    System.out.println(ex.getMessage());
	    ErrorCode errorCode;

	    try {
	        errorCode = ErrorCode.valueOf(errorKey);
	    } catch (Exception e) {
	        errorCode = ErrorCode.INVALID_EXCEPTION;
	    }

	    ModelAndView modelAndView = new ModelAndView("error");
	    modelAndView.addObject("errorCode", errorCode.getCode());
	    modelAndView.addObject("errorMessage", errorCode.getMessage());
	    return modelAndView;
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
