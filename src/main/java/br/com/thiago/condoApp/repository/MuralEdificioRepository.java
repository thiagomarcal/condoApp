package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Edificio;
import br.com.thiago.condoApp.modelo.MuralEdificio;

public interface MuralEdificioRepository extends CrudRepository<MuralEdificio, Long>{
	
	public List<MuralEdificio> findByEdificio(Edificio edificio);
	
}
