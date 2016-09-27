package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Destino;

public interface DestinoService {
	
	public List<Destino> findAll();
	public void save(Destino destino);
	public Destino findOne(Long id);
	public void delete(Long id);

}
