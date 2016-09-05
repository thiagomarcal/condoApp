package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Edificio;
import br.com.thiago.condoApp.repository.EdificioRepository;

@Repository
public class EdificioServiceImpl implements EdificioService {
	
	@Autowired
	private EdificioRepository edificioRepository;
	
	@Override
	public List<Edificio> findAll() {
		return (List<Edificio>) edificioRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Edificio edificio) {
		edificioRepository.save(edificio);
	}

	@Override
	public Edificio findOne(Long id) {
		return edificioRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		edificioRepository.delete(id);
	}

	@Override
	public List<Edificio> findByNome(String nome) {
		return edificioRepository.findByNome(nome);
	}

}
