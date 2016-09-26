package br.com.thiago.condoApp.modelo.factory;

import java.io.Serializable;

import br.com.thiago.condoApp.modelo.Pessoa;
import br.com.thiago.condoApp.modelo.User;

public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String pass;
	private String roles;
	private Pessoa pessoa;
	
	public UserVO() {
		super();
	}

	public UserVO(String username, String roles, Pessoa pessoa) {
		this.username = username;
		this.roles = roles;
		this.pessoa = pessoa;
	}

	public UserVO(User user) {
		this.username = user.getUsername();
		this.roles = user.getAuthorities();
		this.pessoa = user.getPessoa();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	

}
