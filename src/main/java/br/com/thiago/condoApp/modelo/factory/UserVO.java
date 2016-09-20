package br.com.thiago.condoApp.modelo.factory;

import java.io.Serializable;

import br.com.thiago.condoApp.modelo.User;

public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String roles;

	public UserVO(String username, String roles) {
		this.username = username;
		this.roles = roles;
	}

	public UserVO(User user) {
		this.username = user.getUsername();
		this.roles = user.getAuthorities();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}
