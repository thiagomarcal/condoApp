package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Encomenda;
import br.com.thiago.condoApp.modelo.Encomenda.Tipo;

public interface EncomendaRepository extends CrudRepository<Encomenda, Long>{
	
	public List<Encomenda> findByTipo(Tipo tipo);
	

}
