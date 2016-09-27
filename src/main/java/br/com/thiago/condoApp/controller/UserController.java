package br.com.thiago.condoApp.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.condoApp.modelo.Pessoa;
import br.com.thiago.condoApp.modelo.User;
import br.com.thiago.condoApp.modelo.factory.UserVO;
import br.com.thiago.condoApp.repository.UserRepository;
import br.com.thiago.condoApp.security.TokenUtils;
import br.com.thiago.condoApp.servico.UsuarioService;

@RestController
public class UserController {
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UsuarioService userService;
	
	
	@RequestMapping(value = "/user/roles", method = RequestMethod.GET)
	public ResponseEntity<UserVO> listar(@RequestHeader("X-Auth-Token") String authToken) {
		
		String username = this.tokenUtils.getUsernameFromToken(authToken);
		User user = this.userRepository.findByUsername(username);
		
		UserVO userVO = new UserVO(user);

		return new ResponseEntity<UserVO>(userVO, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<UserVO>> listar() {
		
		List<User> listaUsuarios = (List<User>) this.userService.findAll();
		Set<UserVO> listaUserVO = new HashSet<>();
		
		for (User user : listaUsuarios) {
			UserVO userVO = new UserVO(user);
			listaUserVO.add(userVO);
		}
		
		return new ResponseEntity<List<UserVO>>(new ArrayList<>(listaUserVO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UserVO> listarPorNome(@RequestParam("username") String username) {
		UserVO user = new UserVO(this.userService.findByUserName(username));
		return new ResponseEntity<UserVO>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UserVO> alterar(@RequestBody UserVO usuario) {
		
		User user = this.userService.findByUserName(usuario.getUsername());
		
		user.setAuthorities(usuario.getRoles());
		user.setUsername(usuario.getUsername());
		user.setPessoa(usuario.getPessoa());
		
		Pessoa pessoa = usuario.getPessoa();
		pessoa.setUser(user);
		
		this.userRepository.save(user);
		
		return new ResponseEntity<UserVO>(new UserVO(user), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Long> deletar(@RequestParam("username") String username) {
		User user = this.userService.findByUserName(username);
		this.userService.delete(user.getId());
		return new ResponseEntity<Long>(1l, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UserVO> criar(@RequestBody UserVO usuario) {
		
		
		User user = new User(usuario.getUsername(), new BCryptPasswordEncoder().encode(usuario.getPass()), null, null, usuario.getRoles(), usuario.getPessoa());
		Pessoa pessoa = usuario.getPessoa();
		pessoa.setUser(user);
				
		this.userService.save(user);
		
		return new ResponseEntity<UserVO>(new UserVO(user), HttpStatus.OK);
	}

}
