package br.com.thiago.condoApp.controller;

import java.util.Date;
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

import br.com.thiago.condoApp.modelo.Visitante;
import br.com.thiago.condoApp.servico.VisitanteService;

@RestController
public class VisitanteController {
	
	@Autowired
	private VisitanteService visitanteService;
	
	
	@RequestMapping(value = "/visitantes", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Visitante>> listar() {
		return new ResponseEntity<List<Visitante>>(visitanteService.findAll(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/visitante", method = RequestMethod.GET)
	public ResponseEntity<List<Visitante>> listarPorNome(@RequestParam("nome") String nome) {
		return new ResponseEntity<List<Visitante>>(visitanteService.findByNome(nome), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/visitante/{id}", method = RequestMethod.GET)
	public ResponseEntity<Visitante> pegarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<Visitante>(visitanteService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/visitante/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
		visitanteService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/visitante", method = RequestMethod.PUT)
	public ResponseEntity<Visitante> update(@RequestBody Visitante visitante) {
		visitanteService.save(visitante);
		return new ResponseEntity<Visitante>(visitante, HttpStatus.OK);
	}

	@RequestMapping(value = "/visitante", method = RequestMethod.POST)
	public ResponseEntity<Visitante> criarVisitante(@RequestBody Visitante visitante) {
		if (visitante != null) {
			visitante.setDataVisita(new Date());
		}
		visitanteService.save(visitante);
		return new ResponseEntity<Visitante>(visitante, HttpStatus.OK);
	}



}
