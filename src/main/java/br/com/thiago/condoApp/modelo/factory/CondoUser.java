package br.com.thiago.condoApp.modelo.factory;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CondoUser implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	private String password;
	private String email;
	private Date lastPasswordReset;
	private Collection<? extends GrantedAuthority> authorities;
	private Boolean accountNonExpired = true;
	private Boolean accountNonLocked = true;
	private Boolean credentialsNonExpired = true;
	private Boolean enabled = true;
	
	
	
	public CondoUser() {
		super();
	}
	
	

	public CondoUser(Long id, String username, String password, Date lastPasswordReset,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.lastPasswordReset = lastPasswordReset;
		this.authorities = authorities;
	}
	
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}


	@JsonIgnore
	public Date getLastPasswordReset() {
		return lastPasswordReset;
	}



	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}


	@JsonIgnore
	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}



	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}


	@JsonIgnore
	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}



	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}


	@JsonIgnore
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}



	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}


	@JsonIgnore
	public Boolean getEnabled() {
		return enabled;
	}

	

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.getAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.getAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.getCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return this.getEnabled();
	}

}
