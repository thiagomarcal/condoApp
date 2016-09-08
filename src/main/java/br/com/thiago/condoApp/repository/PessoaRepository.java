package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long>{
	
	public List<Pessoa> findByNome(String nome);
	
}
