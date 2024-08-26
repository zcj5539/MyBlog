package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminLogoutCon {
	@Autowired
	private HttpSession session;
	
	//ログアウト処理
	@GetMapping("/logout")
	public String adminLogout() {
		//session無効化
		session.invalidate();
		return "redirect:/admin/login";
	}
}
