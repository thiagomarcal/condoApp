package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Morador;
import br.com.thiago.condoApp.repository.MoradorRepository;

@Repository
public class MoradorServiceImpl implements MoradorService {
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	@Override
	public List<Morador> findAll() {
		return (List<Morador>) moradorRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Morador morador) {
		moradorRepository.save(morador);
	}

	@Override
	public Morador findOne(Long id) {
		return moradorRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		moradorRepository.delete(id);
	}

	@Override
	public List<Morador> findByNome(String nome) {
		return moradorRepository.findByNome(nome);
	}

}
