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
	// もしadminId==null nullに戻る
	// findAllの内容をコントローラークラスにわたす  
    public List<Blog> selectAllBlogList(Long adminId) {
        if (adminId == null) {
            return null;
        } else {
            return blogDao.findByAdminId(adminId);
        }
    }

	// ブログ登録チェック
	// もし、findByBlogTitleがNULLだったら、保存,true
	// そうでない場合、FALSE
	public boolean createBlog(String blogCategory, String blogDescription, String blogImage, String blogTitle,
			LocalDate date, Long adminId) {
		if (blogDao.findByBlogTitle(blogTitle) == null) {
			blogDao.save(new Blog(blogCategory, blogDescription, blogImage, blogTitle, date, adminId));
			return true;
		} else {
			return false;
		}
	}

	// 編集画面を表示するときのチェック
	// もし、blogId＝＝null、null
	// そうでない場合、findByBlogIdの情報をコントローラクラスに渡す
	public Blog BlogEditCheck(Long blogId) {
		if (blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}

	// 更新処理のチェック、もしblogId==null、更新処理をしない false
	// そうでない場合、更新処理をする
	// コントローラクラスからもらった、blogIdを使って、編集する前の、データを取得
	// 変更すべきところだけ、セッターを使用してデータを更新
	// trueを返す
	public boolean blogUpdate(Long blogId, String blogCategory, 
							  String blogDescription, String blogImage,
							    String blogTitle, LocalDate date, Long adminId) {
		if (blogId == null) {
			return false;
		} else {
			Blog blog = blogDao.findByBlogId(blogId);
			blog.setBlogCategory(blogCategory);
			blog.setBlogDescription(blogDescription);
			blog.setBlogImage(blogImage);
			blog.setBlogTitle(blogTitle);
			blog.setDate(date);
			blog.setAdminId(adminId);
			blogDao.save(blog);
			return true;
		}
	}
	//用途：削除
	//もしコントローラからもらったblogIdがnullであれば、削除できないこと、false
	//そうでない場合、、deleteByBlogIdを使用してブログを削除
	public boolean deleteBlog(Long blogId) {
		if(blogId == null) {
			return false;
		}else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
	
}
