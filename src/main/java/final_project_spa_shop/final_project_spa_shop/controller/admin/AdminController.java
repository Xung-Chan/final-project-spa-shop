package final_project_spa_shop.final_project_spa_shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@GetMapping("/home")
	public String getIndex() {
		return "admin/index";
	}
	@GetMapping("/booking")
	public String payment() {
		return "admin/booking";
	}
	@GetMapping("/clerk")
	public String clerk() {
		return "admin/clerk";
	}
	@GetMapping("/addEmployee")
	public String addEmployee() {
		return "admin/addEmployee";
	}
	@GetMapping("/profile")
	public String profile() {
		return "admin/profile";
	}
}
