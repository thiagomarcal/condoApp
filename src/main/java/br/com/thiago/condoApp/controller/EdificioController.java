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

import br.com.thiago.condoApp.modelo.Apartamento;
import br.com.thiago.condoApp.modelo.Edificio;
import br.com.thiago.condoApp.servico.ApartamentoService;
import br.com.thiago.condoApp.servico.EdificioService;

@RestController
public class EdificioController {
	
	@Autowired
	private EdificioService edificioService;
	
	@Autowired
	private ApartamentoService apartamentoService;

	
	@RequestMapping(value = "/edificios", method = RequestMethod.GET)
	public ResponseEntity<List<Edificio>> listar() {
		return new ResponseEntity<List<Edificio>>(edificioService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edificio", method = RequestMethod.GET)
	public ResponseEntity<List<Edificio>> listarPorNome(@RequestParam("nome") String nome) {
		return new ResponseEntity<List<Edificio>>(edificioService.findByNome(nome), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edificio/{id}", method = RequestMethod.GET)
	public ResponseEntity<Edificio> pegar(@PathVariable("id") Long id) {
		return new ResponseEntity<Edificio>(edificioService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edificio/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletar(@PathVariable("id") Long id) {
		edificioService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edificio", method = RequestMethod.PUT)
	public ResponseEntity<Edificio> update(@RequestBody Edificio edificio) {
		edificioService.save(edificio);
		return new ResponseEntity<Edificio>(edificio, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edificio", method = RequestMethod.POST)
	public ResponseEntity<Edificio> criarEdificio(@RequestBody Edificio edificio) {
		edificioService.save(edificio);
		return new ResponseEntity<Edificio>(edificio, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edificio/{id}/apartamento", method = RequestMethod.POST)
	public ResponseEntity<Apartamento> criarEdificio(@PathVariable("id") Long id, @RequestBody Apartamento apartamento) {
		apartamento.setEdificio(edificioService.findOne(id));
		apartamentoService.save(apartamento);
		return new ResponseEntity<Apartamento>(apartamento, HttpStatus.OK);
	}
	
	

}
