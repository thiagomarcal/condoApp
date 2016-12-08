package br.com.thiago.condoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.condoApp.modelo.Alternativa;
import br.com.thiago.condoApp.modelo.Enquete;
import br.com.thiago.condoApp.servico.AlternativaService;
import br.com.thiago.condoApp.servico.EnqueteService;

@RestController
public class EnqueteController {
	
	@Autowired
	private EnqueteService enqueteService;
	
	@Autowired
	private AlternativaService alternativaService;
	
	@RequestMapping(value = "/enquetes", method = RequestMethod.GET)
	public ResponseEntity<List<Enquete>> listar() {
		return new ResponseEntity<List<Enquete>>(enqueteService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/enquete/{id}", method = RequestMethod.GET)
	public ResponseEntity<Enquete> pegar(@PathVariable("id") Long id) {
		return new ResponseEntity<Enquete>(enqueteService.findOne(id), HttpStatus.OK);
	}
	
//	@RequestMapping(value = "/enquete", method = RequestMethod.GET)
//	public ResponseEntity<List<Enquete>> listarPorNome(@RequestParam("nome") String pergunta) {
//		return new ResponseEntity<List<Enquete>>(enqueteService.findByPergunta(pergunta), HttpStatus.OK);
//	}
	
	@RequestMapping(value = "/enquete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletar(@PathVariable("id") Long id) {
		enqueteService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/enquete", method = RequestMethod.PUT)
	public ResponseEntity<Enquete> update(@RequestBody Enquete enquete) {
		enqueteService.save(enquete);
		return new ResponseEntity<Enquete>(enquete, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/enquete", method = RequestMethod.POST)
	public ResponseEntity<Enquete> criaArea(@RequestBody Enquete area) {
		enqueteService.save(area);
		return new ResponseEntity<Enquete>(area, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/enquete/{id}/alternativa", method = RequestMethod.POST)
	public ResponseEntity<Alternativa> criarReserva(@PathVariable("id") Long id, @RequestBody Alternativa alternativa) {
		alternativa.setEnquete(enqueteService.findOne(id));
		alternativaService.save(alternativa);
		return new ResponseEntity<Alternativa>(alternativa, HttpStatus.OK);
	}

}
