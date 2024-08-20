package blog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Long> {
	//保存和更新处理
	Admin save(Admin admin);
	//SELECT * FROM admin WHERE admin_eamil = ?
	//用途：注册时防止邮箱重复
	Admin findByAdminEmail(String adminEmail);
	//功能：登录时，是否有对应的email和密码
	//SELECT * FROM admin WHERE admin_emai= ? and password = ?
	Admin findByAdminEmailAndPassword(String adminEmail,String password);
	
}
