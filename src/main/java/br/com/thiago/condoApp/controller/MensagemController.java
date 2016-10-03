package br.com.thiago.condoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.condoApp.modelo.Mensagem;
import br.com.thiago.condoApp.servico.MensagemService;
import br.com.thiago.condoApp.servico.MuralService;

@RestController
public class MensagemController {
	
	@Autowired
	private MensagemService mensagemService;
	
	@Autowired
	private MuralService muralService;
	
	@RequestMapping(value = "/mensagens", method = RequestMethod.GET)
	public ResponseEntity<List<Mensagem>> listar() {
		return new ResponseEntity<List<Mensagem>>(mensagemService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mensagem/{id}", method = RequestMethod.GET)
	public ResponseEntity<Mensagem> pegar(@PathVariable("id") Long id) {
		return new ResponseEntity<Mensagem>(mensagemService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mensagem/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletar(@PathVariable("id") Long id) {
		mensagemService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mensagem", method = RequestMethod.PUT)
	public ResponseEntity<Mensagem> update(@RequestBody Mensagem mensagem) {
		mensagemService.save(mensagem);
		return new ResponseEntity<Mensagem>(mensagem, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mensagem/mural", method = RequestMethod.POST)
	public ResponseEntity<Mensagem> criarMensagemMuralCondominio(@RequestParam("id") Long idMural, @RequestBody Mensagem mensagem) {
		mensagem.setMural(muralService.findOne(idMural));
		mensagemService.save(mensagem);
		return new ResponseEntity<Mensagem>(mensagem, HttpStatus.OK);
	}
	
//	@RequestMapping(value = "/mensagem/muralCondominio", method = RequestMethod.POST)
//	public ResponseEntity<Mensagem> criarMensagemMuralCondominio(@RequestParam("id") Long idMuralCondominio, @RequestBody Mensagem mensagem) {
//		mensagem.setMuralCondominio(muralCondominioService.findOne(idMuralCondominio));
//		mensagemService.save(mensagem);
//		return new ResponseEntity<Mensagem>(mensagem, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/mensagem/muralBloco", method = RequestMethod.POST)
//	public ResponseEntity<Mensagem> criarMensagemMuralBloco(@RequestParam("id") Long idMuralBloco, @RequestBody Mensagem mensagem) {
//		mensagem.setMuralBloco(muralBlocoService.findOne(idMuralBloco));
//		mensagemService.save(mensagem);
//		return new ResponseEntity<Mensagem>(mensagem, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/mensagem/muralEdificio", method = RequestMethod.POST)
//	public ResponseEntity<Mensagem> criarMensagemMuralEdificio(@RequestParam("id") Long idMuralEdificio, @RequestBody Mensagem mensagem) {
//		mensagem.setMuralEdificio(muralEdificioService.findOne(idMuralEdificio));
//		mensagemService.save(mensagem);
//		return new ResponseEntity<Mensagem>(mensagem, HttpStatus.OK);
//	}
	
	@RequestMapping(value = "/mensagem", method = RequestMethod.POST)
	public ResponseEntity<Mensagem> criarMensagem(@RequestBody Mensagem mensagem) {
		mensagemService.save(mensagem);
		return new ResponseEntity<Mensagem>(mensagem, HttpStatus.OK);
	}
	
	@MessageMapping("/message")
	@SendTo("/topic/message")
	public Mensagem mensageria(Mensagem mensagem) throws Exception {
		return mensagem;
	}
	

}
