package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Destino;
import br.com.thiago.condoApp.repository.DestinoRepository;

@Repository
public class DestinoServiceImpl implements DestinoService {
	
	@Autowired
	private DestinoRepository destinoRepository;
	
	@Override
	public List<Destino> findAll() {
		return (List<Destino>) destinoRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Destino condominio) {
		destinoRepository.save(condominio);
	}

	@Override
	public Destino findOne(Long id) {
		return destinoRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		destinoRepository.delete(id);
	}

}
