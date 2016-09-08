package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Pessoa;

public interface PessoaService {
	
	public List<Pessoa> findAll();
	public void save(Pessoa apartamento);
	public Pessoa findOne(Long id);
	public void delete(Long id);
	public List<Pessoa> findByNome(String nome);

}
