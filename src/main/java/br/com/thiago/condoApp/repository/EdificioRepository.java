package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Edificio;

public interface EdificioRepository extends CrudRepository<Edificio, Long>{
	
	public List<Edificio> findByNome(String nome);
	
}
