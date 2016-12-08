package br.com.thiago.condoApp.servico;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Alternativa;
import br.com.thiago.condoApp.modelo.Enquete;
import br.com.thiago.condoApp.repository.AlternativaRepository;
import br.com.thiago.condoApp.repository.EnqueteRepository;

@Repository
public class EnqueteServiceImpl implements EnqueteService {
	
	@Autowired
	private EnqueteRepository enqueteRepository;
	
	@Autowired
	private AlternativaRepository alternativaRepository;
	
	@Override
	public List<Enquete> findAll() {
		return (List<Enquete>) enqueteRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Enquete enquete) {
		enqueteRepository.save(enquete);
		for (Alternativa alternativa : enquete.getAlternativas()) {
			alternativa.setEnquete(enquete);
		}
		alternativaRepository.save(enquete.getAlternativas());
	}

	@Override
	public Enquete findOne(Long id) {
		return enqueteRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		enqueteRepository.delete(id);
	}

}
