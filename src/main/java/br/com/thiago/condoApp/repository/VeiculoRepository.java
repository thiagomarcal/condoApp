package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Veiculo;

public interface VeiculoRepository extends CrudRepository<Veiculo, Long>{
	
	public List<Veiculo> findByPlaca(String placa);

}
