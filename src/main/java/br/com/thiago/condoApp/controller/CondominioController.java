package br.com.thiago.condoApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.condoApp.modelo.Condominio;

@RestController
public class CondominioController {
	
	private Map<Integer, Condominio> cond;

	public CondominioController() {
		cond = new HashMap<Integer, Condominio>();
		
		Condominio cond1 = new Condominio("teste1");
		Condominio cond2 = new Condominio("teste2");
		Condominio cond3 = new Condominio("teste3");
		
		cond.put(1, cond1);
		cond.put(2, cond2);
		cond.put(3, cond3);
		
	}
	
	@RequestMapping(value = "/condominios", method = RequestMethod.GET)
	public ResponseEntity<List<Condominio>> listar() {
		return new ResponseEntity<List<Condominio>>(new ArrayList<Condominio>(cond.values()), HttpStatus.OK);
	}
	
	

}
