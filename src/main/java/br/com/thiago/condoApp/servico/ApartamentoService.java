package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Apartamento;
import br.com.thiago.condoApp.modelo.Edificio;

public interface ApartamentoService {
	
	public List<Apartamento> findAll();
	public void save(Apartamento apartamento);
	public Apartamento findOne(Long id);
	public void delete(Long id);
	public List<Apartamento> findByNome(String nome);
	public List<Apartamento> findByEdificio(Edificio edificio);

}
