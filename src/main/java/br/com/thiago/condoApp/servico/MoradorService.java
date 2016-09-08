package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Apartamento;
import br.com.thiago.condoApp.modelo.Morador;

public interface MoradorService {
	
	public List<Morador> findAll();
	public void save(Morador morador);
	public Morador findOne(Long id);
	public void delete(Long id);
	public List<Morador> findByApartamento(Apartamento apartamento);

}
