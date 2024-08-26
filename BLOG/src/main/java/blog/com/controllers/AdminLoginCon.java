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
	
	//ログイン処理
	@PostMapping("/admin/login/process")
	public String adminLoginProcess(@RequestParam String adminEmail,
									@RequestParam String password) {
		//logincheckメソッドを呼び出して、adminニ結果をあげる。
		Admin admin = adminService.loginCheck(adminEmail, password);
		//もしadmin==null，login画面ニとどまる
		//そうでない場合、ログインのデータをsessionに保存，ブログ一覧画面にアクセスする。
		if(admin ==null) {
			return "login.html";
		}else {
			session.setAttribute("loginAdminInfo", admin);
			return "redirect:/blog/list";
		}
		
	}
}
