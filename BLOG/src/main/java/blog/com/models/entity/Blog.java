package blog.com.models.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Blog {
	//blog_id
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long blogId;
	private String blogCategory;
	private String blogDescription;
	private String blogImage;
	private String blogTitle;
	private LocalDate blogDate;
	private Long adminId;
	public Blog() {
	}
	public Blog(String blogCategory, String blogDescription, String blogImage, String blogTitle, LocalDate date,
			Long adminId) {
		this.blogCategory = blogCategory;
		this.blogDescription = blogDescription;
		this.blogImage = blogImage;
		this.blogTitle = blogTitle;
		this.blogDate = date;
		this.adminId = adminId;
	}
	public Long getBlogId() {
		return blogId;
	}
	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}
	public String getBlogCategory() {
		return blogCategory;
	}
	public void setBlogCategory(String blogCategory) {
		this.blogCategory = blogCategory;
	}
	public String getBlogDescription() {
		return blogDescription;
	}
	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}
	public String getBlogImage() {
		return blogImage;
	}
	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public LocalDate getBlogDate() {
		return blogDate;
	}
	public void setBlogDate(LocalDate blogDate) {
		this.blogDate = blogDate;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	
	
}
