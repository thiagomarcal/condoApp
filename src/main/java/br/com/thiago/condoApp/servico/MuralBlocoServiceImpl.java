package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Bloco;
import br.com.thiago.condoApp.modelo.MuralBloco;
import br.com.thiago.condoApp.repository.MuralBlocoRepository;

@Repository
public class MuralBlocoServiceImpl implements MuralBlocoService {
	
	@Autowired
	private MuralBlocoRepository muralRepository;
	
	@Override
	public List<MuralBloco> findAll() {
		return (List<MuralBloco>) muralRepository.findAll();
	}

	@Override
	@Transactional
	public void save(MuralBloco apartamento) {
		muralRepository.save(apartamento);
	}

	@Override
	public MuralBloco findOne(Long id) {
		return muralRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		muralRepository.delete(id);
	}

	@Override
	public List<MuralBloco> findByBloco(Bloco bloco) {
		return muralRepository.findByBloco(bloco);
	}

}
