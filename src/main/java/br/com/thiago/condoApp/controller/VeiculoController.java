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

import br.com.thiago.condoApp.modelo.Veiculo;
import br.com.thiago.condoApp.servico.MoradorService;
import br.com.thiago.condoApp.servico.VeiculoService;

@RestController
public class VeiculoController {
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private MoradorService moradorService;
	
	@RequestMapping(value = "/veiculos", method = RequestMethod.GET)
	public ResponseEntity<List<Veiculo>> listar() {
		return new ResponseEntity<List<Veiculo>>(veiculoService.findAll(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/veiculo", method = RequestMethod.GET)
	public ResponseEntity<List<Veiculo>> listarPorPlaca(@RequestParam("placa") String placa) {
		return new ResponseEntity<List<Veiculo>>(veiculoService.findByPlaca(placa), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/veiculo/{id}", method = RequestMethod.GET)
	public ResponseEntity<Veiculo> pegar(@PathVariable("id") Long id) {
		return new ResponseEntity<Veiculo>(veiculoService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/veiculo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletar(@PathVariable("id") Long id) {
		veiculoService.delete(id);
		return new ResponseEntity<Long>(id, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/veiculo", method = RequestMethod.PUT)
	public ResponseEntity<Veiculo> updateVeiculo(@RequestBody Veiculo veiculo) {
		veiculoService.save(veiculo);
		return new ResponseEntity<Veiculo>(veiculo, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/veiculo/salvar", method = RequestMethod.POST)
	public ResponseEntity<Veiculo> criar(@RequestParam("morador") Long idMorador, @RequestBody Veiculo veiculo) {
		veiculo.setMorador(moradorService.findOne(idMorador));
		veiculoService.save(veiculo);
		return new ResponseEntity<Veiculo>(veiculo, HttpStatus.OK);
	}

}
