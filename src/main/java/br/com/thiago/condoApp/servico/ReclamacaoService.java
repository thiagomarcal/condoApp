package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Reclamacao;
import br.com.thiago.condoApp.modelo.Reclamacao.Situacao;
import br.com.thiago.condoApp.modelo.Reclamacao.Tipo;
import br.com.thiago.condoApp.modelo.Morador;

public interface ReclamacaoService {

	public List<Reclamacao> findAll();
	public void save(Reclamacao reclamacao);
	public Reclamacao findOne(Long id);
	public void delete(Long id);
	public List<Reclamacao> findBySituacao(Situacao situacao);
	public List<Reclamacao> findByTipo(Tipo tipo);
	public List<Reclamacao> findByMorador(Morador morador);
	
	
}
