package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Destino;
import br.com.thiago.condoApp.modelo.Mural;
import br.com.thiago.condoApp.repository.MuralRepository;

@Repository
public class MuralServiceImpl implements MuralService {
	
	@Autowired
	private MuralRepository muralRepository;
	
	@Override
	public List<Mural> findAll() {
		return (List<Mural>) muralRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Mural mural) {
		muralRepository.save(mural);
	}

	@Override
	public Mural findOne(Long id) {
		return muralRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		muralRepository.delete(id);
	}

	@Override
	public List<Mural> findByDestino(Destino destino) {
		return muralRepository.findByDestino(destino);
	}

}
