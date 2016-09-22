package br.com.thiago.condoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.condoApp.modelo.Bloco;
import br.com.thiago.condoApp.modelo.Edificio;
import br.com.thiago.condoApp.servico.BlocoService;
import br.com.thiago.condoApp.servico.EdificioService;

@RestController
public class BlocoController {
	
	@Autowired
	private BlocoService blocoService;
	
	@Autowired
	private EdificioService edificioService;
	
	@RequestMapping(value = "/blocos", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Bloco>> listar() {
		return new ResponseEntity<List<Bloco>>(blocoService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bloco", method = RequestMethod.GET)
	public ResponseEntity<List<Bloco>> listarPorNome(@RequestParam("nome") String nome) {
		return new ResponseEntity<List<Bloco>>(blocoService.findByNome(nome), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bloco/{id}", method = RequestMethod.GET)
	public ResponseEntity<Bloco> pegar(@PathVariable("id") Long id) {
		return new ResponseEntity<Bloco>(blocoService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bloco/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletar(@PathVariable("id") Long id) {
		blocoService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bloco", method = RequestMethod.PUT)
	public ResponseEntity<Bloco> update(@RequestBody Bloco area) {
		blocoService.save(area);
		return new ResponseEntity<Bloco>(area, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bloco/{id}/edificio", method = RequestMethod.POST)
	public ResponseEntity<Edificio> criarBlocoEdificio(@PathVariable("id") Long id, @RequestBody Edificio edificio) {
		edificio.setBloco(blocoService.findOne(id));
		edificioService.save(edificio);
		return new ResponseEntity<Edificio>(edificio, HttpStatus.OK);
	}
	
	

}
