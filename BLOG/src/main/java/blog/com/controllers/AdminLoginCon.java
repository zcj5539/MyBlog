package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.com.models.entity.Admin;
import blog.com.services.AdminService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminLoginCon {
	@Autowired
	private AdminService adminService;
	
	//sessionの宣言
	@Autowired
	private HttpSession session;
	
	@GetMapping("/admin/login")
	public String getAdminLoginPage() {
		return "login.html";
	}
	
	//登陆处理
	@PostMapping("/admin/login/process")
	public String adminLoginProcess(@RequestParam String adminEmail,
									@RequestParam String password) {
		//呼出logincheck方法。把结果给admin
		Admin admin = adminService.loginCheck(adminEmail, password);
		//如果admin==null，停留在login
		//否则，把登陆数据保存在session，访问博客一览画面。
		if(admin ==null) {
			return "login.html";
		}else {
			session.setAttribute("loginAdminInfo", admin);
			//这里不确定是blog/list还是list
			return "redirect:/blog/list";
		}
		
	}
}
