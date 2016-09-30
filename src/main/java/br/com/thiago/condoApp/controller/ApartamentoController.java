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
import br.com.thiago.condoApp.modelo.Morador;
import br.com.thiago.condoApp.servico.ApartamentoService;
import br.com.thiago.condoApp.servico.MoradorService;

@RestController
public class ApartamentoController {
	
	@Autowired
	private ApartamentoService apartamentoService;
	
	@Autowired
	private MoradorService moradorService;
	
	@RequestMapping(value = "/apartamentos", method = RequestMethod.GET)
	public ResponseEntity<List<Apartamento>> listar() {
		return new ResponseEntity<List<Apartamento>>(apartamentoService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/apartamento", method = RequestMethod.GET)
	public ResponseEntity<List<Apartamento>> listarPorNome(@RequestParam("nome") String nome) {
		return new ResponseEntity<List<Apartamento>>(apartamentoService.findByNome(nome), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/apartamento/{id}", method = RequestMethod.GET)
	public ResponseEntity<Apartamento> pegar(@PathVariable("id") Long id) {
		return new ResponseEntity<Apartamento>(apartamentoService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/apartamento/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletar(@PathVariable("id") Long id) {
		apartamentoService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/apartamento", method = RequestMethod.PUT)
	public ResponseEntity<Apartamento> update(@RequestBody Apartamento apartamento) {
		apartamentoService.save(apartamento);
		return new ResponseEntity<Apartamento>(apartamento, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/apartamento", method = RequestMethod.POST)
	public ResponseEntity<Apartamento> criarApartamento(@RequestBody Apartamento apartamento) {
		apartamentoService.save(apartamento);
		return new ResponseEntity<Apartamento>(apartamento, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/apartamento/{id}/morador", method = RequestMethod.POST)
	public ResponseEntity<Morador> criarMorador(@PathVariable("id") Long id, @RequestBody Morador morador) {
		morador.setApartamento(apartamentoService.findOne(id));
		moradorService.save(morador);
		return new ResponseEntity<Morador>(morador, HttpStatus.OK);
	}
	
	

}
