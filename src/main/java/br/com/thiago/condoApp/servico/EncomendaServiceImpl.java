package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Encomenda;
import br.com.thiago.condoApp.repository.EncomendaRepository;

public class EncomendaServiceImpl implements EncomendaService {
	
	@Autowired
	private EncomendaRepository encomendaRepository;
	

	@Override
	public List<Encomenda> findAll() {
		return (List<Encomenda>) encomendaRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Encomenda encomenda) {
		encomendaRepository.save(encomenda);
	}

	@Override
	public Encomenda findOne(Long id) {
		return encomendaRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		encomendaRepository.delete(id);
	}

	@Override
	public List<Encomenda> findByTipo(String tipo) {
		return (List<Encomenda>) encomendaRepository.findByTipo(tipo);
	}

}
