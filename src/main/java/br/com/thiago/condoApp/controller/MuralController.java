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

import br.com.thiago.condoApp.modelo.Mural;
import br.com.thiago.condoApp.servico.DestinoService;
import br.com.thiago.condoApp.servico.MuralService;

@RestController
public class MuralController {
	
	@Autowired
	private MuralService muralService;
	
	@Autowired
	private DestinoService destinoService;
	
//	@Autowired
//	private MuralBlocoService muralBlocoService;
//	
//	@Autowired
//	private MuralEdificioService muralEdificioService;
//	
//	@Autowired
//	private CondominioService condominioService;
//	
//	@Autowired
//	private BlocoService blocoService;
//	
//	@Autowired
//	private EdificioService edificioService;
	
	// Mural Condominio
	@RequestMapping(value = "/murais", method = RequestMethod.GET)
	public ResponseEntity<List<Mural>> listar() {
		return new ResponseEntity<List<Mural>>(muralService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mural", method = RequestMethod.GET)
	public ResponseEntity<List<Mural>> listarPorDestino(@RequestParam("condominioId") Long id) {
		return new ResponseEntity<List<Mural>>(muralService.findByDestino(destinoService.findOne(id)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mural/{id}", method = RequestMethod.GET)
	public ResponseEntity<Mural> pegar(@PathVariable("id") Long id) {
		return new ResponseEntity<Mural>(muralService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mural/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletar(@PathVariable("id") Long id) {
		muralService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mural", method = RequestMethod.PUT)
	public ResponseEntity<Mural> update(@RequestBody Mural mural) {
		muralService.save(mural);
		return new ResponseEntity<Mural>(mural, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/mural", method = RequestMethod.POST)
	public ResponseEntity<Mural> criarMural(@RequestBody Mural condominio) {
		muralService.save(condominio);
		return new ResponseEntity<Mural>(condominio, HttpStatus.OK);
	}
	
	
//	// Mural Bloco
//	@RequestMapping(value = "/muralBlocos", method = RequestMethod.GET)
//	public ResponseEntity<List<MuralBloco>> listarBloco() {
//		return new ResponseEntity<List<MuralBloco>>(muralBlocoService.findAll(), HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/muralBloco", method = RequestMethod.GET)
//	public ResponseEntity<List<MuralBloco>> listarPorBloco(@RequestParam("blocoId") Long id) {
//		return new ResponseEntity<List<MuralBloco>>(muralBlocoService.findByBloco(blocoService.findOne(id)), HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/muralBloco/{id}", method = RequestMethod.GET)
//	public ResponseEntity<MuralBloco> pegarBloco(@PathVariable("id") Long id) {
//		return new ResponseEntity<MuralBloco>(muralBlocoService.findOne(id), HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/muralBloco/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<Long> deletarBloco(@PathVariable("id") Long id) {
//		muralBlocoService.delete(id);
//		return new ResponseEntity<Long>(id, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/muralBloco", method = RequestMethod.PUT)
//	public ResponseEntity<MuralBloco> updateBloco(@RequestBody MuralBloco mural) {
//		muralBlocoService.save(mural);
//		return new ResponseEntity<MuralBloco>(mural, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/muralBloco", method = RequestMethod.POST)
//	public ResponseEntity<MuralBloco> criarMuralBloco(@RequestBody MuralBloco bloco) {
//		muralBlocoService.save(bloco);
//		return new ResponseEntity<MuralBloco>(bloco, HttpStatus.OK);
//	}
//	
//	
//	// Mural Edificio
//	@RequestMapping(value = "/muralEdificios", method = RequestMethod.GET)
//	public ResponseEntity<List<MuralEdificio>> listarEdificio() {
//		return new ResponseEntity<List<MuralEdificio>>(muralEdificioService.findAll(), HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/muralEdificio", method = RequestMethod.GET)
//	public ResponseEntity<List<MuralEdificio>> listarPorEdificio(@RequestParam("edificioId") Long id) {
//		return new ResponseEntity<List<MuralEdificio>>(muralEdificioService.findByEdificio(edificioService.findOne(id)), HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/muralEdificio/{id}", method = RequestMethod.GET)
//	public ResponseEntity<MuralEdificio> pegarEdificio(@PathVariable("id") Long id) {
//		return new ResponseEntity<MuralEdificio>(muralEdificioService.findOne(id), HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/muralEdificio/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<Long> deletarEdificio(@PathVariable("id") Long id) {
//		muralEdificioService.delete(id);
//		return new ResponseEntity<Long>(id, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/muralEdificio", method = RequestMethod.PUT)
//	public ResponseEntity<MuralEdificio> updateEdificio(@RequestBody MuralEdificio mural) {
//		muralEdificioService.save(mural);
//		return new ResponseEntity<MuralEdificio>(mural, HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/muralEdificio", method = RequestMethod.POST)
//	public ResponseEntity<MuralEdificio> criarMuralEdificio(@RequestBody MuralEdificio edificio) {
//		muralEdificioService.save(edificio);
//		return new ResponseEntity<MuralEdificio>(edificio, HttpStatus.OK);
//	}

}
