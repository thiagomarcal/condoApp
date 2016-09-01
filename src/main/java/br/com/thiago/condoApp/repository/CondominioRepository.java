package br.com.thiago.condoApp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Condominio;

public interface CondominioRepository extends CrudRepository<Condominio, Long>{
	
	List<Condominio> 
	
}
