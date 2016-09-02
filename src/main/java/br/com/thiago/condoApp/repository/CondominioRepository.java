package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Condominio;

public interface CondominioRepository extends CrudRepository<Condominio, Long>{
	
	public List<Condominio> findByName(String name);
	
}
