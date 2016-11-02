package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Bloco;
import br.com.thiago.condoApp.modelo.Edificio;

public interface EdificioService {
	
	public List<Edificio> findAll();
	public void save(Edificio edificio);
	public Edificio findOne(Long id);
	public void delete(Long id);
	public List<Edificio> findByNome(String nome);
	public List<Edificio> findByBloco(Bloco bloco);

}
