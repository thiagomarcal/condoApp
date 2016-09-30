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

import br.com.thiago.condoApp.modelo.Morador;
import br.com.thiago.condoApp.servico.ApartamentoService;
import br.com.thiago.condoApp.servico.MoradorService;

@RestController
public class MoradorController {
	
	@Autowired
	private MoradorService moradorService;
	
	@Autowired
	private ApartamentoService apartamentoService;
	
	@RequestMapping(value = "/moradores", method = RequestMethod.GET)
	public ResponseEntity<List<Morador>> listar() {
		return new ResponseEntity<List<Morador>>(moradorService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/morador", method = RequestMethod.GET)
	public ResponseEntity<List<Morador>> listarPorApartamento(@RequestParam("apartamentoId") Long apartamentoId) {
		return new ResponseEntity<List<Morador>>(moradorService.findByApartamento(apartamentoService.findOne(apartamentoId)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/morador/{id}", method = RequestMethod.GET)
	public ResponseEntity<Morador> pegar(@PathVariable("id") Long id) {
		return new ResponseEntity<Morador>(moradorService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/morador/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletar(@PathVariable("id") Long id) {
		moradorService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/morador", method = RequestMethod.PUT)
	public ResponseEntity<Morador> update(@RequestBody Morador morador) {
		moradorService.save(morador);
		return new ResponseEntity<Morador>(morador, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/morador", method = RequestMethod.POST)
	public ResponseEntity<Morador> criar(@RequestBody Morador morador) {
		moradorService.save(morador);
		return new ResponseEntity<Morador>(morador, HttpStatus.OK);
	}
	

}
