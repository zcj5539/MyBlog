package blog.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.com.models.entity.Admin;
import blog.com.models.entity.Blog;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogListCon {
	@Autowired
	private HttpSession session;
	
	@Autowired
	private BlogService blogService;
	//blog一覧画面を表示
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		//セッションから登録者情報を取得
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
	//もし，admin==null，ログイン画面にリダイレクトする
		//そうでない場合、登録者の名前を画面ニ渡し、ブログ一覧画面を表示する
		 if(admin==null) {
			 return "redirect:/admin/login";
		 }else {
			 //取得博客情报
			 List<Blog> blogList = blogService.selectAllBlogList(admin.getAdminId());
			 model.addAttribute("adminName",admin.getAdminName());
			 model.addAttribute("blogList",blogList);
			 return "list.html";
		 }
	}
}
