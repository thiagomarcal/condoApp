package br.com.thiago.condoApp.security;

import br.com.thiago.condoApp.modelo.AuthenticationRequest;

public class TestApiConfig {

	public static final String HOSTNAME = "localhost";
	public static final String SERVER_CONTEXT = "api";
	public static final Integer PORT = 8080;

	public static final AuthenticationRequest USER_AUTHENTICATION_REQUEST = new AuthenticationRequest("user@user.com",
			"password");
	public static final AuthenticationRequest ADMIN_AUTHENTICATION_REQUEST = new AuthenticationRequest("admin@admin.com",
			"admin");
	public static final AuthenticationRequest EXPIRED_AUTHENTICATION_REQUEST = new AuthenticationRequest("expired@expired.com",
			"expired");
	public static final AuthenticationRequest INVALID_AUTHENTICATION_REQUEST = new AuthenticationRequest("user@user.com",
			"abc123");

	public static String getAbsolutePath(String relativePath) {
		return String.format("http://%s:%d/%s/%s", HOSTNAME, PORT, SERVER_CONTEXT, relativePath);
	}

}
