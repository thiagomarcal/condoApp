package br.com.thiago.condoApp.modelo.factory;

import org.springframework.security.core.authority.AuthorityUtils;

import br.com.thiago.condoApp.modelo.User;

public class CondoUserFactory {
	
	public static CondoUser create (User user) {
		return new CondoUser(
				user.getId(), 
				user.getUsername(), 
				user.getPassword(),  
				user.getLastPasswordReset(),
				AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities()));
	}
	
}
