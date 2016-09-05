package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Apartamento;

public interface ApartamentoRepository extends CrudRepository<Apartamento, Long>{
	
	public List<Apartamento> findByNome(String nome);
	
}
