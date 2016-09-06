package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Condominio;
import br.com.thiago.condoApp.modelo.MuralCondominio;
import br.com.thiago.condoApp.repository.MuralCondominioRepository;

@Repository
public class MuralCondominioServiceImpl implements MuralCondominioService {
	
	@Autowired
	private MuralCondominioRepository muralRepository;
	
	@Override
	public List<MuralCondominio> findAll() {
		return (List<MuralCondominio>) muralRepository.findAll();
	}

	@Override
	@Transactional
	public void save(MuralCondominio apartamento) {
		muralRepository.save(apartamento);
	}

	@Override
	public MuralCondominio findOne(Long id) {
		return muralRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		muralRepository.delete(id);
	}

	@Override
	public List<MuralCondominio> findByCondominio(Condominio condominio) {
		return muralRepository.findByCondominio(condominio);
	}

}
