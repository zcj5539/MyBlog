package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blog.com.models.entity.Admin;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogDeleteCon {
	@Autowired
	private BlogService blogService;

	@Autowired
	private HttpSession session;

	@PostMapping("/blog/delete")
	public String blogDelete(Long blogId) {
		// sessionからもらった登録者情報をadminで格纳。
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		// もし、admin==nullだったら、ログイン画面にリダイレクトする
		if (admin == null) {
			return "redirect:/admin/login";
		} else {
			// もし、deleteBlogの結果はtrueだったら
			// 商品一覧画面ニリダイレクトする
			// そうでない場合、編集画面にリダイレクトする
			if (blogService.deleteBlog(blogId)) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit" + blogId;
			}
		}
	}

}
