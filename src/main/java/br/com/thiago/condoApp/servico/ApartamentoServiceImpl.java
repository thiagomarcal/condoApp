package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Apartamento;
import br.com.thiago.condoApp.repository.ApartamentoRepository;

@Repository
public class ApartamentoServiceImpl implements ApartamentoService {
	
	@Autowired
	private ApartamentoRepository apartamentoRepository;
	
	@Override
	public List<Apartamento> findAll() {
		return (List<Apartamento>) apartamentoRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Apartamento apartamento) {
		apartamentoRepository.save(apartamento);
	}

	@Override
	public Apartamento findOne(Long id) {
		return apartamentoRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		apartamentoRepository.delete(id);
	}

	@Override
	public List<Apartamento> findByNome(String nome) {
		return apartamentoRepository.findByNome(nome);
	}

}
