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

import br.com.thiago.condoApp.modelo.Area;
import br.com.thiago.condoApp.modelo.Reserva;
import br.com.thiago.condoApp.servico.AreaService;
import br.com.thiago.condoApp.servico.ReservaService;

@RestController
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private ReservaService reservaService;
	
	@RequestMapping(value = "/areas", method = RequestMethod.GET)
	public ResponseEntity<List<Area>> listar() {
		return new ResponseEntity<List<Area>>(areaService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/area/{id}", method = RequestMethod.GET)
	public ResponseEntity<Area> pegar(@PathVariable("id") Long id) {
		return new ResponseEntity<Area>(areaService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/area", method = RequestMethod.GET)
	public ResponseEntity<List<Area>> listarPorNome(@RequestParam("nome") String nome) {
		return new ResponseEntity<List<Area>>(areaService.findByNome(nome), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/area/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletar(@PathVariable("id") Long id) {
		areaService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/area", method = RequestMethod.PUT)
	public ResponseEntity<Area> update(@RequestBody Area area) {
		areaService.save(area);
		return new ResponseEntity<Area>(area, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/area", method = RequestMethod.POST)
	public ResponseEntity<Area> criaArea(@RequestBody Area area) {
		areaService.save(area);
		return new ResponseEntity<Area>(area, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/area/{id}/reserva", method = RequestMethod.POST)
	public ResponseEntity<Reserva> criarReserva(@PathVariable("id") Long id, @RequestBody Reserva reserva) {
		reserva.setArea(areaService.findOne(id));
		reservaService.save(reserva);
		return new ResponseEntity<Reserva>(reserva, HttpStatus.OK);
	}

}
