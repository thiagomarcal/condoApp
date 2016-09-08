package br.com.thiago.condoApp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	public User findByUsername(String username);
	
}
