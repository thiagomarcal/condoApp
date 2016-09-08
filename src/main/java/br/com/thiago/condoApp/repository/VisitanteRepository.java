package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Visitante;

public interface VisitanteRepository extends CrudRepository<Visitante, Long>{

	public List<Visitante> findByNome(String nome);
	
}

