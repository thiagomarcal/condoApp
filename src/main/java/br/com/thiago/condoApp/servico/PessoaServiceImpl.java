package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Pessoa;
import br.com.thiago.condoApp.repository.PessoaRepository;

@Repository
public class PessoaServiceImpl implements PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Override
	public List<Pessoa> findAll() {
		return (List<Pessoa>) pessoaRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Pessoa apartamento) {
		pessoaRepository.save(apartamento);
	}

	@Override
	public Pessoa findOne(Long id) {
		return pessoaRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		pessoaRepository.delete(id);
	}

	@Override
	public List<Pessoa> findByNome(String nome) {
		return pessoaRepository.findByNome(nome);
	}

}
