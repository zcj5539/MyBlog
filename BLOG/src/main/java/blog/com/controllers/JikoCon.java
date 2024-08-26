package blog.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JikoCon {
	//自己紹介画面を表示する
	@GetMapping("/jikosyokai")
	public String getjikoPage() {
		return "jikosyokai.html";
	}
}
