/**
 * 
 */
package com.jwp.webbase.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jwp.core.entitys.DataEntity;
import com.jwp.webbase.enums.AdminUserStatusEnum;

/**
 * @author fengmengyue
 *
 */
@Entity
@Table(name="adminuser")
public class AdminUser extends DataEntity {
	private static final long serialVersionUID = -7910479879273152660L;
	/**
	 * 昵称
	 */
	private String name;
	
	/**
	 * 账号
	 */
	@Column(unique=true)
	private String account;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 电话
	 */
	@Column(unique=true)
	private String mobile;
	
	/**
	 * 电子邮箱
	 */
	@Column(unique=true)
	private String email;
	
	/**
	 * 管理员状态
	 */
	private AdminUserStatusEnum status;
	
	/**
	 * 上次登录时间
	 */
	private Date lastLoginTime;
	
	/**
	 * 角色
	 */
	private String roles;

	/**
	 * 头像
	 */
	private String headPhoto;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AdminUserStatusEnum getStatus() {
		return status;
	}

	public void setStatus(AdminUserStatusEnum status) {
		this.status = status;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getHeadPhoto() {
		return headPhoto;
	}

	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}
}
