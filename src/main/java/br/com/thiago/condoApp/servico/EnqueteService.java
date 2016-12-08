package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Enquete;

public interface EnqueteService {
	
	public List<Enquete> findAll();
	public void save(Enquete enquete);
	public Enquete findOne(Long id);
	public void delete(Long id);
}
