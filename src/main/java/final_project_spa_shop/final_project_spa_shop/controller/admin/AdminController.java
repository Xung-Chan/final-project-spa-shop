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
	@GetMapping("/changePassword")
	public String changePassword() {
		return "admin/changePassword";
	}
	@GetMapping("/editProfile")
	public String editProfile() {
		return "admin/editProfile";
	}
	@GetMapping("/schedule")
	public String schedule() {
		return "admin/schedule";
	}
	@GetMapping("/addSchedule")
	public String addSchedule() {
		return "admin/addSchedule";
	}
	@GetMapping("/customer")
	public String customer() {
		return "admin/customer";
	}
	@GetMapping("/service")
	public String service() {
		return "admin/service";
	}
	@GetMapping("/addService")
	public String addService() {
		return "admin/addService";
	}
	@GetMapping("/editService")
	public String editService() {
		return "admin/editService";
	}
	@GetMapping("/revenue")
	public String revenue() {
		return "admin/revenue";
	}
	@GetMapping("/bill")
	public String bill() {
		return "admin/bill";
	}
	@GetMapping("/addBill")
	public String addBill() {
		return "admin/addBill";
	}
	@GetMapping("/mySchedule")
	public String mySchedule() {
		return "admin/mySchedule";
	}
}
