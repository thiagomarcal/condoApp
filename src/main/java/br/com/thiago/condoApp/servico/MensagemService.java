package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Mensagem;

public interface MensagemService {
	
	public List<Mensagem> findAll();
	public void save(Mensagem apartamento);
	public Mensagem findOne(Long id);
	public void delete(Long id);
}
