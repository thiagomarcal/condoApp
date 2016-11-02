package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Reclamacao;
import br.com.thiago.condoApp.modelo.Reclamacao.Situacao;
import br.com.thiago.condoApp.modelo.Reclamacao.Tipo;
import br.com.thiago.condoApp.modelo.Morador;

public interface ReclamacaoRepository extends CrudRepository<Reclamacao, Long>{
	
	public List<Reclamacao> findBySituacao(Situacao situacao);
	public List<Reclamacao> findByTipo(Tipo tipo);
	public List<Reclamacao> findByMorador(Morador morador);
}
