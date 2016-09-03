package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Condominio;
import br.com.thiago.condoApp.repository.CondominioRepository;

@Repository
public class CondominioServiceImpl implements CondominioService {
	
	@Autowired
	private CondominioRepository condominioRepository;
	
	@Override
	public List<Condominio> findAll() {
		return (List<Condominio>) condominioRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Condominio condominio) {
		condominioRepository.save(condominio);
	}

	@Override
	public Condominio findOne(Long id) {
		return condominioRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		condominioRepository.delete(id);
	}

	@Override
	public List<Condominio> findByName(String name) {
		return condominioRepository.findByName(name);
	}

}
