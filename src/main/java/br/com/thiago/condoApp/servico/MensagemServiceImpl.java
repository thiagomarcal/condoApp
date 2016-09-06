package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Mensagem;
import br.com.thiago.condoApp.repository.MensagemRepository;

@Repository
public class MensagemServiceImpl implements MensagemService {
	
	@Autowired
	private MensagemRepository mensagemRepository;
	
	@Override
	public List<Mensagem> findAll() {
		return (List<Mensagem>) mensagemRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Mensagem apartamento) {
		mensagemRepository.save(apartamento);
	}

	@Override
	public Mensagem findOne(Long id) {
		return mensagemRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		mensagemRepository.delete(id);
	}

}
