package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Encomenda;

public interface EncomendaService {

	public List<Encomenda> findAll();
	public void save(Encomenda visitante);
	public Encomenda findOne(Long id);
	public void delete(Long id);
	public List<Encomenda> findByTipo(String tipo);
	
}
