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

import br.com.thiago.condoApp.modelo.Pessoa;
import br.com.thiago.condoApp.servico.PessoaService;

@RestController
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping(value = "/pessoas", method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> listar() {
		return new ResponseEntity<List<Pessoa>>(pessoaService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pessoa", method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> listarPorNome(@RequestParam("nome") String nome) {
		return new ResponseEntity<List<Pessoa>>(pessoaService.findByNome(nome), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> pegar(@PathVariable("id") Long id) {
		return new ResponseEntity<Pessoa>(pessoaService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletar(@PathVariable("id") Long id) {
		pessoaService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pessoa", method = RequestMethod.PUT)
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa apartamento) {
		pessoaService.save(apartamento);
		return new ResponseEntity<Pessoa>(apartamento, HttpStatus.OK);
	}
	
}
