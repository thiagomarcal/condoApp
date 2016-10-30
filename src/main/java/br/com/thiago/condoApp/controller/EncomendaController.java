package br.com.thiago.condoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.condoApp.modelo.Encomenda;
import br.com.thiago.condoApp.modelo.Encomenda.Situacao;
import br.com.thiago.condoApp.modelo.User;
import br.com.thiago.condoApp.servico.EncomendaService;
import br.com.thiago.condoApp.servico.UsuarioService;

@RestController
public class EncomendaController {

	@Autowired
	private EncomendaService encomendaService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/encomendas", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Encomenda>> listar() {
		return new ResponseEntity<List<Encomenda>>(encomendaService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/encomenda/{id}", method = RequestMethod.GET)
	public ResponseEntity<Encomenda> pegar(@PathVariable("id") Long id) {
		return new ResponseEntity<Encomenda>(encomendaService.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/encomendaspendentes", method = RequestMethod.GET)
	public ResponseEntity<List<Encomenda>> listarPorSituacaoPendente() {
		return new ResponseEntity<List<Encomenda>>(encomendaService.findBySituacao(Situacao.Pendente), HttpStatus.OK);
	}

	@RequestMapping(value = "/minhasencomendas", method = RequestMethod.GET)
	public ResponseEntity<List<Encomenda>> listarEncomendasMorador() {
		User user = usuarioService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<List<Encomenda>>(encomendaService.findByMorador(user.getPessoa().getMorador()),
				HttpStatus.OK);
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

	@RequestMapping(value = "/encomenda", method = RequestMethod.POST)
	public ResponseEntity<Encomenda> post(@RequestBody Encomenda encomenda) {
		encomenda.setSituacao(Situacao.Pendente);
		encomendaService.save(encomenda);
		return new ResponseEntity<Encomenda>(encomenda, HttpStatus.OK);
	}
}
