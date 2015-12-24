/**
 * 
 */
package com.jwp.core.entitys;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author fengmengyue
 *
 */
@Entity
@Table(name="user")
public class User extends CoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5397631621588222816L;

	private String userName;
	
	private String nickName;
	
	private String address;
	
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
