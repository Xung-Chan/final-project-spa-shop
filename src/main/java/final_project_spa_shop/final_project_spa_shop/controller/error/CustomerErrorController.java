package final_project_spa_shop.final_project_spa_shop.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;
@ControllerAdvice
public class CustomerErrorController {
	@ExceptionHandler(NoResourceFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String handleNotFound() {
		return "404";
	}
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public String handleNotPermiit() {
		return "404";
	}
}
