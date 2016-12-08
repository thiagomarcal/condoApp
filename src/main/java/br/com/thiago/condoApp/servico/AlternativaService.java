package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Alternativa;

public interface AlternativaService {
	
	public List<Alternativa> findAll();
	public void save(Alternativa alternativa);
	public Alternativa findOne(Long id);
	public void delete(Long id);

}
