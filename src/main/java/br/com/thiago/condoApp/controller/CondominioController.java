package br.com.thiago.condoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.condoApp.modelo.Condominio;
import br.com.thiago.condoApp.servico.CondominioService;

@RestController
public class CondominioController {
	
	@Autowired
	private CondominioService condominioService;
	
	@RequestMapping(value = "/condominios", method = RequestMethod.GET)
	public ResponseEntity<List<Condominio>> listar() {
		return new ResponseEntity<List<Condominio>>(condominioService.findAll(), HttpStatus.OK);
	}

}
