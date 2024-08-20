package blog.com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Admin;
import blog.com.models.entity.Blog;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BlogDao extends JpaRepository<Blog, Long> {
	//保存和更新处理
	Blog save(Blog blog);
	//SELECT * FROM blog
	//用途：展示blog一览
	List<Blog>findAll();
	//功能：編集画面を表示する
	//SELECT * FROM blog WHERE blog_id = ?
	Blog findByBlogId(Long blogId);
	//削除
	void deleteByBlogId(Long blogId);
	
}
