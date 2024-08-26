package blog.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.com.models.dao.AdminDao;
import blog.com.models.dao.BlogDao;
import blog.com.models.entity.Admin;
import jakarta.transaction.Transactional;

	@Service
	public class AdminService {
	@Autowired
		private AdminDao adminDao;
		//admindaoの中の、重複メールチェックする方法を使う。
		//保存処理（登録処理）
		//如果null，则使用save方法进行注册处理。もしnullだったら、saveメソッドで登録処理をする。
		//保存できたらtrueを表示
		//そうでない場合、falseを表示
		@Transactional
		public boolean createAdmin(String adminEmail,String adminName,String password) {
			if(adminDao.findByAdminEmail(adminEmail)==null) {
				adminDao.save(new Admin(adminEmail,adminName,password));
				return true;
			}else {
				return false;
			}
		}
		
		//ログアウト処理，もしemailとパスワードがfindByAdminEmailAndPasswordを使用して存在しなかった場合、 
		//その場合は、存在しないnullであることをコントローラークラスに知らせる
		//そうでない場合、ログインしている人の情報をコントローラークラスに渡す
		public Admin loginCheck(String adminEmail,String password) {
			Admin admin = adminDao.findByAdminEmailAndPassword(adminEmail, password);
			if(admin == null) {
				return null;
			}else {
				return admin;
			}
		}
		
	}
