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
		//利用admindao里面的重复邮箱验证方法
		//如果null，则使用save方法进行注册处理。
		//保存成功后显示true
		//不是的话 false
		@Transactional
		public boolean createAdmin(String adminEmail,String adminName,String password) {
			if(adminDao.findByAdminEmail(adminEmail)==null) {
				adminDao.save(new Admin(adminEmail,adminName,password));
				return true;
			}else {
				return false;
			}
		}
		//登陆处理，如果email和密码使用adminDao里的方法
		//=null的时候，告诉控制台。
		//不是的话，把登录情报交给控制台
		public Admin loginCheck(String adminEmail,String password) {
			Admin admin = adminDao.findByAdminEmailAndPassword(adminEmail, password);
			if(admin == null) {
				return null;
			}else {
				return admin;
			}
		}
		
	}
