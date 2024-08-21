package blog.com.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.BlogDao;
import blog.com.models.entity.Blog;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

	// 商品一览チェック
	// 如果adminId==null 返回null
	// findAll内容交给控制器
	public List<Blog> selectAllBlogList(Long adminId) {
		if (adminId == null) {
			return null;
		} else {
			return blogDao.findAll();
		}
	}
	//ブログ登録チェック
	//もし、findByBlogTitleがNULLだったら、保存,true
	//そうでない場合、FALSE
	public boolean createBlog(String blogCategory, String blogDescription, String blogImage, String blogTitle,
			LocalDate date, Long adminId) {
		if (blogDao.findByBlogTitle(blogTitle) == null) {
			blogDao.save(new Blog(blogCategory, blogDescription, blogImage, blogTitle, date, adminId));
			return true;
		} else {
			return false;
		}
	}
}
