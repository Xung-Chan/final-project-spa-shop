package final_project_spa_shop.final_project_spa_shop.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class HomeController {
	@GetMapping("/home")
	public String home() {
		return "customer/index";
	}

	@GetMapping("/service")
	public String service() {
		return "customer/service";
	}

	@GetMapping("/about")
	public String about() {
		return "customer/about";
	}

	@GetMapping("/appointment")
	public String appointment() {
		return "customer/appointment";
	}

	@GetMapping("/contact")
	public String contact() {
		return "customer/contact";
	}

	@GetMapping("/forgot")
	public String forgot() {
		return "customer/forgot";
	}

	@GetMapping("/forumC")
	public String forum() {
		return "customer/forumC";
	}

	@GetMapping("/gallery")
	public String gallery() {
		return "customer/gallery";
	}

	@GetMapping("/price")
	public String price() {
		return "customer/price";
	}

	@GetMapping("/registry")
	public String registry() {
		return "customer/registry";
	}

	@GetMapping("/team")
	public String team() {
		return "customer/team";
	}

	@GetMapping("/testimonial")
	public String testimonial() {
		return "customer/testimonial";
	}
	@GetMapping("/login")
	public String login() {
		return "customer/login";
	}
	@GetMapping("/profile")
	public String profile() {
		return "customer/profile";
	}
	@GetMapping("/editprofile")
	public String editprofile() {
		return "customer/editprofile";
	}
	@GetMapping("/changepassword")
	public String changepassword() {
		return "customer/changepassword";
	}
	@GetMapping("/history")
	public String history() {
		return "customer/history";
	}
	@GetMapping("/cancelappointment")
	public String cancelappointment() {
		return "customer/cancelappointment";
	}
	@GetMapping("/payment")
	public String payment() {
		return "customer/payment";
	}
}
