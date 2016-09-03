package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Bloco;

public interface BlocoRepository extends CrudRepository<Bloco, Long>{
	
	public List<Bloco> findByNome(String nome);
	
}
