package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Encomenda;
import br.com.thiago.condoApp.modelo.Encomenda.Situacao;
import br.com.thiago.condoApp.modelo.Morador;

public interface EncomendaService {

	public List<Encomenda> findAll();
	public void save(Encomenda visitante);
	public Encomenda findOne(Long id);
	public void delete(Long id);
	public List<Encomenda> findBySituacao(Situacao situacao);
	public List<Encomenda> findByMorador(Morador morador);
	
	
}
