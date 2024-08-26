package blog.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.com.models.entity.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Long> {
	//保存と更新処理
	Admin save(Admin admin);
	//SELECT * FROM admin WHERE admin_eamil = ?
	//用途：登録するとき、メールの重複を防ぐ
	Admin findByAdminEmail(String adminEmail);
	//機能：ログインするとき、対応するメールとパスワードが存在するかをチェック
	//SELECT * FROM admin WHERE admin_emai= ? and password = ?
	Admin findByAdminEmailAndPassword(String adminEmail,String password);
	
}
