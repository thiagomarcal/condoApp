package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Visitante;

public interface VisitanteService {
	
	
	public List<Visitante> findAll();
	public void save(Visitante visitante);
	public Visitante findOne(Long id);
	public void delete(Long id);
	public List<Visitante> findByNome(String nome);

}
