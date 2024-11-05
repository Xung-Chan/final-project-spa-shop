package final_project_spa_shop.final_project_spa_shop.controller.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class CustomerErrorController implements ErrorController{
	@GetMapping("/error")
	public String handleError() {
		return "customer/404";
	}
}
