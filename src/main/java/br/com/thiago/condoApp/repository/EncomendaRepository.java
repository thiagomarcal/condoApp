package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Encomenda;
import br.com.thiago.condoApp.modelo.Encomenda.Situacao;
import br.com.thiago.condoApp.modelo.Morador;

public interface EncomendaRepository extends CrudRepository<Encomenda, Long>{
	
	public List<Encomenda> findBySituacao(Situacao situacao);
	public List<Encomenda> findByMorador(Morador morador);
}
