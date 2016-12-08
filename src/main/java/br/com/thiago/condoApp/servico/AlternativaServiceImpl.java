package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Alternativa;
import br.com.thiago.condoApp.repository.AlternativaRepository;

@Repository
public class AlternativaServiceImpl implements AlternativaService {
	
	@Autowired
	private AlternativaRepository alternativaRepository;
	
	@Override
	public List<Alternativa> findAll() {
		return (List<Alternativa>) alternativaRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Alternativa alternativa) {
		alternativaRepository.save(alternativa);
	}

	@Override
	public Alternativa findOne(Long id) {
		return alternativaRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		alternativaRepository.delete(id);
	}

}
