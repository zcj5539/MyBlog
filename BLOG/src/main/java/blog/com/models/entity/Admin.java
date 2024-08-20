package blog.com.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {
	//admin_idの設定
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long adminId;
	
	//admin_email
	@Column(name = "admin_email")
	private String adminEmail;
	
	//admin_name
	@Column(name = "admin_name")
	private String adminName;
	
	//password
	@Column(name = "password")
	private String password;
	//空的构造函数
	public Admin() {
	}
	//构造函数
	public Admin(String adminEmail, String adminName, String password) {
		this.adminEmail = adminEmail;
		this.adminName = adminName;
		this.password = password;
	}
	//GETTER,SETTER
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
