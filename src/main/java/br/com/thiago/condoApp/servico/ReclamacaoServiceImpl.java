package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Morador;
import br.com.thiago.condoApp.modelo.Reclamacao;
import br.com.thiago.condoApp.modelo.Reclamacao.Situacao;
import br.com.thiago.condoApp.modelo.Reclamacao.Tipo;
import br.com.thiago.condoApp.repository.ReclamacaoRepository;

@Repository
public class ReclamacaoServiceImpl implements ReclamacaoService {
	
	@Autowired
	private ReclamacaoRepository reclamacaoRepository;
	

	@Override
	public List<Reclamacao> findAll() {
		return (List<Reclamacao>) reclamacaoRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Reclamacao encomenda) {
		reclamacaoRepository.save(encomenda);
	}

	@Override
	public Reclamacao findOne(Long id) {
		return reclamacaoRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		reclamacaoRepository.delete(id);
	}

	@Override
	public List<Reclamacao> findBySituacao(Situacao situacao) {
		return reclamacaoRepository.findBySituacao(situacao);
	}
	
	@Override
	public List<Reclamacao> findByTipo(Tipo tipo) {
		return reclamacaoRepository.findByTipo(tipo);
	}

	@Override
	public List<Reclamacao> findByMorador(Morador morador) {
		return reclamacaoRepository.findByMorador(morador);
	}

}
