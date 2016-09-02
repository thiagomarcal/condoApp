package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Condominio;

public interface CondominioService {
	
	public List<Condominio> findAll();
	public void save(Condominio condominio);
	public Condominio findOne(Long id);
	public void delete(Long id);
	public List<Condominio> findByName(String name);
	

}
