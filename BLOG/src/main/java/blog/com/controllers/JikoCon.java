package blog.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JikoCon {
	@GetMapping("/jikosyokai")
	public String getjikoPage() {
		return "jikosyokai.html";
	}
}
