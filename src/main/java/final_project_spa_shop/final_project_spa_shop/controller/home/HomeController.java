package final_project_spa_shop.final_project_spa_shop.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/home")
	public String home() {
		return "index";
	}
	@GetMapping("/service")
	public String service() {
		return "service";
	}
}
