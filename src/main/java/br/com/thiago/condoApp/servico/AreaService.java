package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Area;

public interface AreaService {
	
	public List<Area> findAll();
	public void save(Area condominio);
	public Area findOne(Long id);
	public void delete(Long id);
	public List<Area> findByNome(String nome);

}
