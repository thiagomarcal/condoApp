package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Edificio;
import br.com.thiago.condoApp.modelo.MuralEdificio;

public interface MuralEdificioService {
	
	public List<MuralEdificio> findAll();
	public void save(MuralEdificio mural);
	public MuralEdificio findOne(Long id);
	public void delete(Long id);
	public List<MuralEdificio> findByEdificio(Edificio edificio);

}
