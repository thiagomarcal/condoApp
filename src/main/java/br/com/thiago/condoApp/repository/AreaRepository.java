package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Area;

public interface AreaRepository extends CrudRepository<Area, Long>{
	
	public List<Area> findByNome(String nome);
	
}
