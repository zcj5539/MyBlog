package blog.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Admin;
import blog.com.models.entity.Blog;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogEditCon {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private HttpSession session;
	
	//編集画面を表示する
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId,Model model) {
		// 从session获得的登录者情报用admin格纳
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		//もしadmin==null　ログイン画面ニリダイレクトする
		if(admin == null) {
			return"redirect:/admin/login";
		}else {
			//編集画面に表示させる情報を変数ニ格納
			Blog blog = blogService.BlogEditCheck(blogId);
			//もしblog == nullだったら、ブログ一覧画面ニリダイレクトする
			//そうでない場合、編集画面に編集する内容を渡す
			//編集画面を表示
			if(blog == null) {
				return "redirect:/blog/list";
			}else {
				model.addAttribute("adminName",admin.getAdminName());
				model.addAttribute("blog",blog);
				return "blog-edit.html";
			}
		}
	}
		//更新処理をする
		@PostMapping("/blog/edit/process")
		public String blogUpdate(@RequestParam String title,   
				@RequestParam LocalDate date, 
				@RequestParam String cate,
				@RequestParam MultipartFile img,
				@RequestParam String content,
				@RequestParam Long blogId){
			// 从session获得的登录者情报用admin格纳
			Admin admin = (Admin) session.getAttribute("loginAdminInfo");
			//もし、admin ==nullだったら、ログイン画面ニリダイレクトする
			//そうでない場合、ファイルの保存
			//もし、blogUpdateの結果がtrueの場合は、ブログ一覧画面ニリダイレクトする
			//そうでない場合、ブログ編集画面にリダイレクトする
			if(admin == null) {
				return "redirect:/admin/login";
			}else {
				String fileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date())
						+img.getOriginalFilename();
				try {
					Files.copy(img.getInputStream(),Path.of("src/main/resources/static/blog-img/"+fileName));
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(blogService.blogUpdate(blogId, cate, content,fileName, title, date, admin.getAdminId())) {
					return "redirect:/blog/list";
				}else {
					return "redirect:/blog/edit"+blogId;
				}
			}
			
		}
		
		
}
