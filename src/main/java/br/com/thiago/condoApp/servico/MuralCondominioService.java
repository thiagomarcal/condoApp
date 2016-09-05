package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Condominio;
import br.com.thiago.condoApp.modelo.MuralCondominio;

public interface MuralCondominioService {
	
	public List<MuralCondominio> findAll();
	public void save(MuralCondominio mural);
	public MuralCondominio findOne(Long id);
	public void delete(Long id);
	public List<MuralCondominio> findByCondominio(Condominio condominio);

}
