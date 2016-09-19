package br.com.thiago.condoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.condoApp.modelo.User;
import br.com.thiago.condoApp.repository.UserRepository;
import br.com.thiago.condoApp.security.TokenUtils;

@RestController
public class UserController {
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/user/roles", method = RequestMethod.GET)
	public ResponseEntity<?> listar(@RequestHeader("X-Auth-Token") String authToken) {
		
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		User user = this.userRepository.findByUsername(username);
		
		return ResponseEntity.ok(user.getAuthorities());
		//return new ResponseEntity<String>(user.getAuthorities(), HttpStatus.OK);
	}	

}
