package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.services.AdminService;

@Controller
public class AdminRegister{
	
	@Autowired
	private AdminService adminService;
	
	//表示注册画面
	@GetMapping("/admin/register")
	public String getAdminRegisterPage() {
		return "register.html";
	}
	//注册处理
	@PostMapping("/admin/register/process")
	public String adminRegitserProcess(@RequestParam String adminName,
										@RequestParam String adminEmail,
										@RequestParam String password) {
		if(adminService.createAdmin(adminEmail, adminName, password)) {
			return "login.html";
		}else {
			return "register.html";
		}
	}
	
}
