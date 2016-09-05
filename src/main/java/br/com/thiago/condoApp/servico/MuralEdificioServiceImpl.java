package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Edificio;
import br.com.thiago.condoApp.modelo.MuralEdificio;
import br.com.thiago.condoApp.repository.MuralEdificioRepository;

@Repository
public class MuralEdificioServiceImpl implements MuralEdificioService {
	
	@Autowired
	private MuralEdificioRepository muralRepository;
	
	@Override
	public List<MuralEdificio> findAll() {
		return (List<MuralEdificio>) muralRepository.findAll();
	}

	@Override
	@Transactional
	public void save(MuralEdificio apartamento) {
		muralRepository.save(apartamento);
	}

	@Override
	public MuralEdificio findOne(Long id) {
		return muralRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		muralRepository.delete(id);
	}

	@Override
	public List<MuralEdificio> findByEdificio(Edificio edificio) {
		return muralRepository.findByEdificio(edificio);
	}

}
