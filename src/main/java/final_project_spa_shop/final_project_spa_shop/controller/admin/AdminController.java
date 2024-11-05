package final_project_spa_shop.final_project_spa_shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@GetMapping("/dashboard")
	public String getIndex() {
		return "admin/index";
	}
	@GetMapping("/payment")
	public String payment() {
		return "customer/payment";
	}
}
