package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Bloco;
import br.com.thiago.condoApp.repository.BlocoRepository;

@Repository
public class BlocoServiceImpl implements BlocoService {
	
	@Autowired
	private BlocoRepository blocoRepository;
	
	@Override
	public List<Bloco> findAll() {
		return (List<Bloco>) blocoRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Bloco bloco) {
		blocoRepository.save(bloco);
	}

	@Override
	public Bloco findOne(Long id) {
		return blocoRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		blocoRepository.delete(id);
	}

	@Override
	public List<Bloco> findByNome(String nome) {
		return blocoRepository.findByNome(nome);
	}

}
