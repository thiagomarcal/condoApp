package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Bloco;
import br.com.thiago.condoApp.modelo.MuralBloco;

public interface MuralBlocoRepository extends CrudRepository<MuralBloco, Long>{
	
	public List<MuralBloco> findByBloco(Bloco bloco);
	
}
