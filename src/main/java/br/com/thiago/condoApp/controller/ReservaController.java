package br.com.thiago.condoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiago.condoApp.modelo.Reserva;
import br.com.thiago.condoApp.modelo.Reserva.Situacao;
import br.com.thiago.condoApp.modelo.User;
import br.com.thiago.condoApp.servico.AreaService;
import br.com.thiago.condoApp.servico.MoradorService;
import br.com.thiago.condoApp.servico.ReservaService;
import br.com.thiago.condoApp.servico.UsuarioService;

@RestController
public class ReservaController {
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private MoradorService moradorService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/reservas", method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> listar() {
		return new ResponseEntity<List<Reserva>>(reservaService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reserva", method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> listarPorSituacao(@RequestParam("situacao") Situacao situacao) {
		return new ResponseEntity<List<Reserva>>(reservaService.findBySituacao(situacao), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reserva/{id}", method = RequestMethod.GET)
	public ResponseEntity<Reserva> pegar(@PathVariable("id") Long id) {
		return new ResponseEntity<Reserva>(reservaService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reserva/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletar(@PathVariable("id") Long id) {
		reservaService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reserva", method = RequestMethod.PUT)
	public ResponseEntity<Reserva> update(@RequestBody Reserva apartamento) {
		reservaService.save(apartamento);
		return new ResponseEntity<Reserva>(apartamento, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reserva/salvar", method = RequestMethod.POST)
	public ResponseEntity<Reserva> criar(@RequestParam("area") Long idArea, @RequestParam("morador") Long idMorador, @RequestBody Reserva reserva) {
		reserva.setArea(areaService.findOne(idArea));
		reserva.setMorador(moradorService.findOne(idMorador));
		reservaService.save(reserva);
		return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reserva", method = RequestMethod.POST)
	public ResponseEntity<Reserva> criarT(@RequestBody Reserva reserva) {
		
		User user = usuarioService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		
		reserva.setMorador(user.getPessoa().getMorador());
		
		reservaService.save(reserva);
		return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
	}
	
	@MessageMapping("/reserva")
	@SendTo("/topic/reserva")
	public Reserva mensageria(Reserva reserva) throws Exception {
		return reserva;
	}
	
	
	
	
	

}
