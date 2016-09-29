package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Destino;
import br.com.thiago.condoApp.modelo.Mural;

public interface MuralService {
	
	public List<Mural> findAll();
	public void save(Mural mural);
	public Mural findOne(Long id);
	public void delete(Long id);
	public List<Mural> findByDestino(Destino destino);

}
