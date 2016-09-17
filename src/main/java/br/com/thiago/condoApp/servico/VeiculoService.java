package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Veiculo;

public interface VeiculoService {
	
	public List<Veiculo> findAll();
	public void save(Veiculo veiculo);
	public Veiculo findOne(Long id);
	public void delete(Long id);
	public List<Veiculo> findByPlaca(String placa);

}
