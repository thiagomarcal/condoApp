package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.User;

public interface UsuarioService {
	
	public List<User> findAll();
	public void save(User user);
	public User findOne(Long id);
	public void delete(Long id);
	public User findByUserName(String username);
	public List<User> findByAuthorities(String name);

}
