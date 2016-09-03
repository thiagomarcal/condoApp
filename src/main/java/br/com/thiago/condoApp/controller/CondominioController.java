package br.com.thiago.condoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.condoApp.modelo.Area;
import br.com.thiago.condoApp.modelo.Bloco;
import br.com.thiago.condoApp.modelo.Condominio;
import br.com.thiago.condoApp.servico.AreaService;
import br.com.thiago.condoApp.servico.BlocoService;
import br.com.thiago.condoApp.servico.CondominioService;

@RestController
public class CondominioController {
	
	@Autowired
	private CondominioService condominioService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private BlocoService blocoService;
	
	@RequestMapping(value = "/condominios", method = RequestMethod.GET)
	public ResponseEntity<List<Condominio>> listar() {
		return new ResponseEntity<List<Condominio>>(condominioService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/condominio", method = RequestMethod.GET)
	public ResponseEntity<List<Condominio>> listarPorNome(@RequestParam("name") String name) {
		return new ResponseEntity<List<Condominio>>(condominioService.findByName(name), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/condominio/{id}", method = RequestMethod.GET)
	public ResponseEntity<Condominio> pegarCondominio(@PathVariable("id") Long id) {
		return new ResponseEntity<Condominio>(condominioService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/condominio", method = RequestMethod.POST)
	public ResponseEntity<Condominio> criarCondominio(@RequestBody Condominio condominio) {
		condominioService.save(condominio);
		return new ResponseEntity<Condominio>(condominio, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/condominio/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletarCondominio(@PathVariable("id") Long id) {
		condominioService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/condominio", method = RequestMethod.PUT)
	public ResponseEntity<Condominio> updateCondominio(@RequestBody Condominio condominio) {
		condominioService.save(condominio);
		return new ResponseEntity<Condominio>(condominio, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/condominio/{id}/area", method = RequestMethod.POST)
	public ResponseEntity<Area> criarArea(@PathVariable("id") Long id, @RequestBody Area area) {
		area.setCondominio(condominioService.findOne(id));
		areaService.save(area);
		return new ResponseEntity<Area>(area, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/condominio/{id}/bloco", method = RequestMethod.POST)
	public ResponseEntity<Bloco> criarBloco(@PathVariable("id") Long id, @RequestBody Bloco bloco) {
		bloco.setCondominio(condominioService.findOne(id));
		blocoService.save(bloco);
		return new ResponseEntity<Bloco>(bloco, HttpStatus.OK);
	}
	
	

}
