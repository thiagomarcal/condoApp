package br.com.thiago.condoApp.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	private String password;
	private String email;
	private Date lastPasswordReset;
	private String authorities;
	
	
	public User() {
		super();
	}


	public User(String username, String password, String email, Date lastPasswordReset, String authorities) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.lastPasswordReset = lastPasswordReset;
		this.authorities = authorities;
	}

	@Id
	@Column(name="id")
	@GeneratedValue
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "last_password_reset")
	public Date getLastPasswordReset() {
		return lastPasswordReset;
	}


	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}

	@Column(name = "authorities")
	public String getAuthorities() {
		return authorities;
	}


	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

}
