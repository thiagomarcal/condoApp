package br.com.thiago.condoApp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Mensagem;

public interface MensagemRepository extends CrudRepository<Mensagem, Long>{
	
}
