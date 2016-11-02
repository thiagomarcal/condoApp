package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Apartamento;
import br.com.thiago.condoApp.modelo.Edificio;

public interface ApartamentoRepository extends CrudRepository<Apartamento, Long>{
	
	public List<Apartamento> findByNome(String nome);
	public List<Apartamento> findByEdificio(Edificio edificio);
	
}
