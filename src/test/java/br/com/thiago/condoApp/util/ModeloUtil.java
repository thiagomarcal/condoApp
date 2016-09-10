package br.com.thiago.condoApp.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiago.condoApp.modelo.Apartamento;
import br.com.thiago.condoApp.modelo.Area;
import br.com.thiago.condoApp.modelo.Bloco;
import br.com.thiago.condoApp.modelo.Condominio;
import br.com.thiago.condoApp.modelo.Edificio;
import br.com.thiago.condoApp.modelo.Encomenda;
import br.com.thiago.condoApp.modelo.Morador;
import br.com.thiago.condoApp.modelo.Pessoa;
import br.com.thiago.condoApp.modelo.Veiculo;
import br.com.thiago.condoApp.modelo.Visitante;
import br.com.thiago.condoApp.servico.ApartamentoService;
import br.com.thiago.condoApp.servico.AreaService;
import br.com.thiago.condoApp.servico.BlocoService;
import br.com.thiago.condoApp.servico.CondominioService;
import br.com.thiago.condoApp.servico.EdificioService;
import br.com.thiago.condoApp.servico.EncomendaService;
import br.com.thiago.condoApp.servico.MoradorService;
import br.com.thiago.condoApp.servico.PessoaService;
import br.com.thiago.condoApp.servico.VeiculoService;
import br.com.thiago.condoApp.servico.VisitanteService;

@Service
public class ModeloUtil {
	
	@Autowired
	private CondominioService condominioService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private BlocoService blocoService;
	
	@Autowired
	private EdificioService edificioService;
	
	@Autowired
	private ApartamentoService apartamentoService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private MoradorService moradorService;
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private EncomendaService encomendaService;
	
	@Autowired
	private VisitanteService visitanteService;
	
	
	
	public Encomenda criarEncomenda(String tipo, Apartamento apartamento) {
		Encomenda encomenda = new Encomenda();
		encomenda.setApartamento(apartamento);
		encomenda.setTipo(tipo);
		
		this.encomendaService.save(encomenda);
		
		return encomenda;
	}
	
	public Visitante criarVisitante(String nome, Apartamento apartamento) {
		Visitante visitante = new Visitante();
		visitante.setDataVisita(new Date());
		visitante.setNome(nome);
		visitante.setApartamento(apartamento);
		
		this.visitanteService.save(visitante);
		
		return visitante;
	}

	public Veiculo criaVeiculo(String marca, String modelo, String placa, String Renavan, String cor, Morador morador) {
		Veiculo veiculo = new Veiculo();
		veiculo.setMarca(marca);
		veiculo.setModelo(modelo);
		veiculo.setPlaca(placa);
		veiculo.setRenavan(Renavan);
		veiculo.setCor(cor);
		veiculo.setMorador(morador);
		
		this.veiculoService.save(veiculo);
		return veiculo;
	}

	public Morador criaMorador(Apartamento apartamento, Pessoa pessoa) {
		Morador mr1 = new Morador();
		mr1.setApartamento(apartamento);
		mr1.setPessoa(pessoa);
		
		this.moradorService.save(mr1);
		return mr1;
		
	}

	public Pessoa criaPessoa(String cpf, String email, String nome) {
		Pessoa p1 = new Pessoa();		
		p1.setCpf(cpf);
		p1.setEmail(email);
		p1.setNome(nome);
		
		this.pessoaService.save(p1);
		return p1;
		
	}

	public Apartamento criaApartamento(String nome, Long numero, Edificio edificio) {
		Apartamento ap1 = new Apartamento();
		ap1.setEdificio(edificio);
		ap1.setNumero(numero);
		ap1.setNome(nome);
		
		this.apartamentoService.save(ap1);
		
		return ap1;
		
	}

	public Edificio criaEdificio(String nome, String descricao, Bloco bloco) {
		Edificio ed1 = new Edificio();
		ed1.setNome(nome);
		ed1.setDescricao(descricao);
		ed1.setBloco(bloco);
		
		this.edificioService.save(ed1);
		return ed1;
	}

	public Bloco criaBloco(String nome, Condominio condominio) {
		Bloco bloco1 = new Bloco();
		bloco1.setNome(nome);
		bloco1.setCondominio(condominio);
		
		this.blocoService.save(bloco1);
		return bloco1;
	}

	public Area criaArea(String nome, String descricao, Condominio condominio) {
		Area area1 = new Area();
		area1.setNome(nome);
		area1.setDescricao(descricao);
		area1.setCondominio(condominio);
		
		this.areaService.save(area1);
		
		return area1;
	}

	public Condominio criaCondominio(String name, String logradouro, String numero, String uf) {
		Condominio condominio = new Condominio();
		condominio.setName(name);
		condominio.setLogradouro(logradouro);
		condominio.setNumero(numero);
		condominio.setUf(uf);
		
		this.condominioService.save(condominio);
		
		return condominio;
		
	}
}
