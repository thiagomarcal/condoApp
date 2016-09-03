package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Bloco;

public interface BlocoService {
	
	public List<Bloco> findAll();
	public void save(Bloco condominio);
	public Bloco findOne(Long id);
	public void delete(Long id);
	public List<Bloco> findByNome(String nome);

}
