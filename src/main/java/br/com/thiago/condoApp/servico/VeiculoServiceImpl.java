package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Veiculo;
import br.com.thiago.condoApp.repository.VeiculoRepository;

@Repository
public class VeiculoServiceImpl implements VeiculoService{
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	

	@Override
	public List<Veiculo> findAll() {
		return (List<Veiculo>) veiculoRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Veiculo veiculo) {
		veiculoRepository.save(veiculo);
	}

	@Override
	public Veiculo findOne(Long id) {
		return veiculoRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		veiculoRepository.delete(id);
	}

	@Override
	public List<Veiculo> findByPlaca(String placa) {
		return (List<Veiculo>) veiculoRepository.findByPlaca(placa);
	}

}
