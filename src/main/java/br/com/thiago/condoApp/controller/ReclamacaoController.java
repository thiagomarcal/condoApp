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

import br.com.thiago.condoApp.modelo.Reclamacao;
import br.com.thiago.condoApp.modelo.Reclamacao.Situacao;
import br.com.thiago.condoApp.modelo.User;
import br.com.thiago.condoApp.servico.ReclamacaoService;
import br.com.thiago.condoApp.servico.UsuarioService;

@RestController
public class ReclamacaoController {

	@Autowired
	private ReclamacaoService reclamacaoService;

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/reclamacoes", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Reclamacao>> listar() {
		return new ResponseEntity<List<Reclamacao>>(reclamacaoService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/reclamacao/{id}", method = RequestMethod.GET)
	public ResponseEntity<Reclamacao> pegar(@PathVariable("id") Long id) {
		return new ResponseEntity<Reclamacao>(reclamacaoService.findOne(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/reclamacoespendentes", method = RequestMethod.GET)
	public ResponseEntity<List<Reclamacao>> listarPorSituacaoPendente() {
		return new ResponseEntity<List<Reclamacao>>(reclamacaoService.findBySituacao(Situacao.Pendente), HttpStatus.OK);
	}

	@RequestMapping(value = "/minhasreclamacoes", method = RequestMethod.GET)
	public ResponseEntity<List<Reclamacao>> listarReclamacoesMorador() {
		User user = usuarioService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<List<Reclamacao>>(reclamacaoService.findByMorador(user.getPessoa().getMorador()),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/reclamacao/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletar(@PathVariable("id") Long id) {
		reclamacaoService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}

	@RequestMapping(value = "/reclamacao", method = RequestMethod.PUT)
	public ResponseEntity<Reclamacao> update(@RequestBody Reclamacao reclamacao) {
		reclamacaoService.save(reclamacao);
		return new ResponseEntity<Reclamacao>(reclamacao, HttpStatus.OK);
	}

	@RequestMapping(value = "/reclamacao", method = RequestMethod.POST)
	public ResponseEntity<Reclamacao> post(@RequestBody Reclamacao reclamacao) {
		User user = usuarioService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		reclamacao.setMorador(user.getPessoa().getMorador());
		reclamacao.setSituacao(Situacao.Pendente);
		reclamacaoService.save(reclamacao);
		return new ResponseEntity<Reclamacao>(reclamacao, HttpStatus.OK);
	}
}
