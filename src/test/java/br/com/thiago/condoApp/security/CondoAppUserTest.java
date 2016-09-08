package br.com.thiago.condoApp.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import static org.hamcrest.core.Is.is;

import br.com.thiago.condoApp.modelo.factory.CondoUser;

public class CondoAppUserTest {

	private final Long ID = 1L;
	private final String USERNAME = "username";
	private final String PASSWORD = "password";
	private final String EMAIL = "user@domain.tld";
	private final Date LAST_PASSWORD_RESET = new Date();
	private final Collection<? extends GrantedAuthority> AUTHORITIES = AuthorityUtils
			.commaSeparatedStringToAuthorityList("user,admin");
	private final Boolean ACCOUNT_NON_EXPIRED = false;
	private final Boolean ACCOUNT_NON_LOCKED = false;
	private final Boolean CREDENTIALS_NON_EXPIRED = false;
	private final Boolean ENABLED = false;

	@Test
	public void chamandoConstrutorFactorySemParametros() {
		CondoUser cerberusUser = new CondoUser();
		assertNull(cerberusUser.getId());
		assertNull(cerberusUser.getUsername());
		assertNull(cerberusUser.getPassword());
		assertNull(cerberusUser.getEmail());
		assertNull(cerberusUser.getLastPasswordReset());
		assertNull(cerberusUser.getAuthorities());
		assertTrue(cerberusUser.getAccountNonExpired());
		assertTrue(cerberusUser.getAccountNonLocked());
		assertTrue(cerberusUser.getCredentialsNonExpired());
		assertTrue(cerberusUser.getEnabled());
	}

	@Test
	public void chamandoConstrutorFactoryComParametros() {
		CondoUser condoUser = new CondoUser(ID, USERNAME, PASSWORD, LAST_PASSWORD_RESET, AUTHORITIES);

		assertThat(condoUser.getId(), is(ID));
		assertThat(condoUser.getUsername(), is(USERNAME));
		assertThat(condoUser.getPassword(), is(PASSWORD));
		assertThat(condoUser.getLastPasswordReset(), is(LAST_PASSWORD_RESET));
		assertEquals(condoUser.getAuthorities(), AUTHORITIES);
		assertTrue(condoUser.getAccountNonExpired());
		assertTrue(condoUser.getAccountNonLocked());
		assertTrue(condoUser.getCredentialsNonExpired());
		assertTrue(condoUser.getEnabled());
	}
	
	 @Test
	  public void chamandoCondoUserComGettersESetters() {
	    CondoUser condoUser = new CondoUser();

	    condoUser.setId(ID);
	    condoUser.setUsername(USERNAME);
	    condoUser.setPassword(PASSWORD);
	    condoUser.setEmail(EMAIL);
	    condoUser.setLastPasswordReset(LAST_PASSWORD_RESET);
	    condoUser.setAuthorities(AUTHORITIES);
	    condoUser.setAccountNonExpired(ACCOUNT_NON_EXPIRED);
	    condoUser.setAccountNonLocked(ACCOUNT_NON_LOCKED);
	    condoUser.setCredentialsNonExpired(CREDENTIALS_NON_EXPIRED);
	    condoUser.setEnabled(ENABLED);

	    assertThat(condoUser.getId(), is(ID));
	    assertThat(condoUser.getUsername(), is(USERNAME));
	    assertThat(condoUser.getPassword(), is(PASSWORD));
	    assertThat(condoUser.getEmail(), is(EMAIL));
	    assertThat(condoUser.getLastPasswordReset(), is(LAST_PASSWORD_RESET));
	    assertEquals(condoUser.getAuthorities(), AUTHORITIES);
	    assertThat(condoUser.getAccountNonExpired(), is(ACCOUNT_NON_EXPIRED));
	    assertThat(condoUser.getAccountNonLocked(), is(ACCOUNT_NON_LOCKED));
	    assertThat(condoUser.getCredentialsNonExpired(), is(CREDENTIALS_NON_EXPIRED));
	    assertThat(condoUser.getEnabled(), is(ENABLED));
	  }

}
