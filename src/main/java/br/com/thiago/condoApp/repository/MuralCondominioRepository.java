package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Condominio;
import br.com.thiago.condoApp.modelo.MuralCondominio;

public interface MuralCondominioRepository extends CrudRepository<MuralCondominio, Long>{
	
	public List<MuralCondominio> findByCondominio(Condominio condominio);
	
}
