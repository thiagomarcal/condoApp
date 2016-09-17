package br.com.thiago.condoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.condoApp.modelo.User;
import br.com.thiago.condoApp.modelo.factory.UserVO;
import br.com.thiago.condoApp.repository.UserRepository;
import br.com.thiago.condoApp.security.TokenUtils;

@RestController
public class UserController {
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/user/roles", method = RequestMethod.GET)
	public ResponseEntity<UserVO> listar(@RequestHeader("X-Auth-Token") String authToken) {
		
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		User user = this.userRepository.findByUsername(username);
		
		UserVO userVO = new UserVO(user);

		return new ResponseEntity<UserVO>(userVO, HttpStatus.OK);
	}	

}
