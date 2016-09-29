package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Destino;
import br.com.thiago.condoApp.modelo.Mural;

public interface MuralRepository extends CrudRepository<Mural, Long>{
	
	public List<Mural> findByDestino(Destino destino);
	
}
