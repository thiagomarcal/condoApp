package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.User;
import br.com.thiago.condoApp.repository.UserRepository;

@Repository
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	@Transactional
	public void save(User condominio) {
		userRepository.save(condominio);
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public User findByUserName(String name) {
		return userRepository.findByUsername(name);
	}
	
	@Override
	public List<User> findByAuthorities(String name) {
		return userRepository.findByAuthorities(name);
	}

}
