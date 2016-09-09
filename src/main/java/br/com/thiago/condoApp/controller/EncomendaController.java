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

import br.com.thiago.condoApp.modelo.Encomenda;
import br.com.thiago.condoApp.servico.EncomendaService;

@RestController
public class EncomendaController {

	
	@Autowired
	private EncomendaService encomendaService;
	
	@RequestMapping(value = "/encomendas", method = RequestMethod.GET)
	public ResponseEntity<List<Encomenda>> listar() {
		return new ResponseEntity<List<Encomenda>>(encomendaService.findAll(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/encomenda", method = RequestMethod.GET)
	public ResponseEntity<List<Encomenda>> listarPorNome(@RequestParam("nome") String tipo) {
		return new ResponseEntity<List<Encomenda>>(encomendaService.findByTipo(tipo), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/encomenda/{id}", method = RequestMethod.GET)
	public ResponseEntity<Encomenda> pegar(@PathVariable("id") Long id) {
		return new ResponseEntity<Encomenda>(encomendaService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/encomenda/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletar(@PathVariable("id") Long id) {
		encomendaService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/encomenda", method = RequestMethod.PUT)
	public ResponseEntity<Encomenda> update(@RequestBody Encomenda encomenda) {
		encomendaService.save(encomenda);
		return new ResponseEntity<Encomenda>(encomenda, HttpStatus.OK);
	}
}
