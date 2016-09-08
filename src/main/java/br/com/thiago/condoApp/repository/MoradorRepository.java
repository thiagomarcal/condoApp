package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Apartamento;
import br.com.thiago.condoApp.modelo.Morador;

public interface MoradorRepository extends CrudRepository<Morador, Long>{
	
	public List<Morador> findByApartamento(Apartamento apartamento);
	
}
