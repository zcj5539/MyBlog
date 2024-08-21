package blog.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Admin;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogAddCon {
	@Autowired
	private BlogService blogService;

	@Autowired
	private HttpSession session;

	// 博客画面的表示
	@GetMapping("blog-add")
	public String getBlogAdd(Model model) {
		// 从session获得的登录者情报用admin格纳
		// 如果admin==null，重访问login
		// 否则情报给画面，并表示博客添加界面
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		if (admin == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("adminName", admin.getAdminName());
			return "blog-add";
		}
	}

	/// 博客追加处理
	@PostMapping("/blog/add/process")
	public String blogAddProcess(@RequestParam String title,   
			@RequestParam LocalDate date, 
			@RequestParam String cate,
			@RequestParam MultipartFile img,
			@RequestParam String content) {
		//从session获得的登录者情报用admin格纳
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		//もし、admin==nullだったら、ログイン画面にリダイレクトする
		//そうでない場合、画像のファイル名を取得、画像のアップロード
		//もし、同じファイルの名前がなかったら保存、ブログ一覧画面ニリダイレクトする
		//そうでない場合、ブログ登録画面ニとどまる。
		if(admin==null) {
			return "redirect:/admin/login";
		}else {
			//取得文件名
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())+img.getOriginalFilename();
			
			//ファイルの保存作業
			try {
				// ファイルの保存作業
				Files.copy(img.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(blogService.createBlog(cate, content, fileName, title, date, admin.getAdminId())){
				return "redirect:/blog/list";
		} else{
			return "blog-add";
		}
	}
}
}