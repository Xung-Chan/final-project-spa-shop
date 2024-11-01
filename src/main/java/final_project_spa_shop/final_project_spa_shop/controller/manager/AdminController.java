package final_project_spa_shop.final_project_spa_shop.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@GetMapping("/admin")
	public String getIndex() {
		return "admin/index";
	}
}
