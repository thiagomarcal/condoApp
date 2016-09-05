package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Bloco;
import br.com.thiago.condoApp.modelo.MuralBloco;

public interface MuralBlocoService {
	
	public List<MuralBloco> findAll();
	public void save(MuralBloco mural);
	public MuralBloco findOne(Long id);
	public void delete(Long id);
	public List<MuralBloco> findByBloco(Bloco bloco);

}
