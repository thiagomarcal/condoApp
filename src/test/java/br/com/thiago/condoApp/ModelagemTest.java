package br.com.thiago.condoApp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.thiago.condoApp.modelo.Apartamento;
import br.com.thiago.condoApp.modelo.Bloco;
import br.com.thiago.condoApp.modelo.Condominio;
import br.com.thiago.condoApp.modelo.Edificio;
import br.com.thiago.condoApp.modelo.Morador;
import br.com.thiago.condoApp.modelo.Pessoa;
import br.com.thiago.condoApp.repository.ApartamentoRepository;
import br.com.thiago.condoApp.repository.AreaRepository;
import br.com.thiago.condoApp.repository.BlocoRepository;
import br.com.thiago.condoApp.repository.CondominioRepository;
import br.com.thiago.condoApp.repository.EdificioRepository;
import br.com.thiago.condoApp.repository.EncomendaRepository;
import br.com.thiago.condoApp.repository.MoradorRepository;
import br.com.thiago.condoApp.repository.PessoaRepository;
import br.com.thiago.condoApp.repository.VeiculoRepository;
import br.com.thiago.condoApp.repository.VisitanteRepository;
import br.com.thiago.condoApp.util.ModeloUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class ModelagemTest {
	
	@Autowired
	private ModeloUtil modeloUtil;
	
	@Autowired
	private VisitanteRepository visitanteRepository;
	
	@Autowired
	private EncomendaRepository encomendaRepository;
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private MoradorRepository moradorRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApartamentoRepository apartamentoRepository;
	
	@Autowired
	private EdificioRepository edificioRepository;
	
	@Autowired
	private BlocoRepository blocoRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private CondominioRepository condominioRepository;

	@Before
	public void setup() throws Exception {
		this.visitanteRepository.deleteAll();
		this.encomendaRepository.deleteAll();
		this.veiculoRepository.deleteAll();
		this.moradorRepository.deleteAll();
		this.pessoaRepository.deleteAll();
		this.apartamentoRepository.deleteAll();
		this.edificioRepository.deleteAll();
		this.blocoRepository.deleteAll();
		this.areaRepository.deleteAll();
		this.condominioRepository.deleteAll();
	}
	
	@After
	public void shutdown() {
		
	}
	
	@Test
	public void criaTest() throws Exception {
		Condominio cond = modeloUtil.criaCondominio("Liberta Resort","Av Di Cavalcanti", "25", "RJ" );
		
		modeloUtil.criaArea("Sauna", "Sauna", cond);
		modeloUtil.criaArea("Piscina", "Piscina", cond);
		modeloUtil.criaArea("Salão de Festa", "Salão de Festa", cond);
		modeloUtil.criaArea("Churrasqueira", "Churrasqueira", cond);
	
		Bloco bloco1 = modeloUtil.criaBloco("Bloco 1", cond);
		Bloco bloco2 = modeloUtil.criaBloco("Bloco 2", cond);
	
		Edificio edf1 = modeloUtil.criaEdificio("Ed1", "Ed1Desc", bloco1);
		Edificio edf2 = modeloUtil.criaEdificio("Ed2", "Ed2Desc", bloco2);
		
		Apartamento ap1 = modeloUtil.criaApartamento("Ap 101", 101L, edf1);
		Apartamento ap2 = modeloUtil.criaApartamento("Ap 102", 102L, edf2);
		
		Pessoa p1 = modeloUtil.criaPessoa("111111", "balbal@ashuu.com", "John Doe");
		Pessoa p2 = modeloUtil.criaPessoa("222222", "thiagormarcal@gmail.com", "Thiago Marcal");
		
		Morador mr1 = modeloUtil.criaMorador(ap1, p1);
		modeloUtil.criaMorador(ap2, p2);
		
		modeloUtil.criaVeiculo("BMW", "Z3", "LLL-1921", "002928182122", "PRETO", mr1);
		
		modeloUtil.criarEncomenda("128391288312983", mr1);
		
		modeloUtil.criarVisitante("Carla Azevedo", ap2);
		
		
		
	}
	
	

}
