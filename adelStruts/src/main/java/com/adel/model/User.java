package com.adel.model;

import java.util.Date;

public class User extends EObject {

	private Integer							idUser;
	private String							login;
	private String							password;
	private String							email;
	private Date							lastConnexion;
	private boolean							enable;
	
	// Constructor
	public User() {}
	public User(Integer idUser, String login, String password, String email, Date lastConnexion, boolean enable) {
		super();
		this.idUser = idUser;
		this.login = login;
		this.password = password;
		this.lastConnexion = lastConnexion;
		this.enable = enable;
		this.email = email;
	}
	
	// Getters and setters
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastConnexion() {
		return lastConnexion;
	}
	public void setLastConnexion(Date lastConnexion) {
		this.lastConnexion = lastConnexion;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
