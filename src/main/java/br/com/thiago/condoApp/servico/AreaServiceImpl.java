package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Area;
import br.com.thiago.condoApp.repository.AreaRepository;

@Repository
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Override
	public List<Area> findAll() {
		return (List<Area>) areaRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Area area) {
		areaRepository.save(area);
	}

	@Override
	public Area findOne(Long id) {
		return areaRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		areaRepository.delete(id);
	}

	@Override
	public List<Area> findByNome(String nome) {
		return areaRepository.findByNome(nome);
	}

}
