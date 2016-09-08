package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Visitante;
import br.com.thiago.condoApp.repository.VisitanteRepository;


public class VisitanteServiceImpl implements VisitanteService{
	
	@Autowired
	private VisitanteRepository veiculoRepository;
	

	@Override
	public List<Visitante> findAll() {
		return (List<Visitante>) veiculoRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Visitante veiculo) {
		veiculoRepository.save(veiculo);
	}

	@Override
	public Visitante findOne(Long id) {
		return veiculoRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		veiculoRepository.delete(id);
	}

	@Override
	public List<Visitante> findByNome(String nome) {
		return (List<Visitante>) veiculoRepository.findByNome(nome);
	}
}
