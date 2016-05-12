package org.gzccc.oa.bean;

import org.gzccc.oa.annotation.IdField;

public class User {
	@IdField
	private String id;
	private String username;
	private String password;
	private String salt;
	
	public User(){}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
}
