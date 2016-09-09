package br.com.thiago.condoApp;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

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
import br.com.thiago.condoApp.repository.CondominioRepository;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class CondominioAllServiceRestTest {
	

	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

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
	
	@Autowired
	private CondominioRepository condominioRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		
		this.condominioRepository.deleteAll();
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
	@After
	public void shutdown() {
		
	}
	
	@Test
	public void criaCondominioTest() throws Exception {
		Condominio cond = criaCondominio();
		
		Area area1 = criaArea("Sauna", "Sauna", cond);
		Area area2 = criaArea("Piscina", "Piscina", cond);
		Area area3 = criaArea("Salão de Festa", "Salão de Festa", cond);
		Area area4 = criaArea("Churrasqueira", "Churrasqueira", cond);
	
		Bloco bloco1 = criaBloco("Bloco 1", cond);
		Bloco bloco2 = criaBloco("Bloco 2", cond);
	
		Edificio edf1 = criaEdificio("Ed1", "Ed1Desc", bloco1);
		Edificio edf2 = criaEdificio("Ed2", "Ed2Desc", bloco1);
		
		Apartamento ap1 = criaApartamento("Ap 101", 101L, edf1);
		Apartamento ap2 = criaApartamento("Ap 102", 102L, edf2);
		
		Pessoa p1 = criaPessoa("111111", "balbal@ashuu.com", "John Doe");
		Pessoa p2 = criaPessoa("222222", "thiagormarcal@gmail.com", "Thiago Marcal");
		
		Morador mr1 = criaMorador(ap1, p1);
		Morador mr2 = criaMorador(ap2, p2);
		
		Veiculo veic = criaVeiculo("BMW", "Z3", "LLL-1921", "002928182122", "PRETO", mr1);
		
		Encomenda encomenda = criarEncomenda("CORREIOS", ap1);
		
		Visitante visitante = criarVisitante("Carla Azevedo", ap2);
		
	}
	
	
	private Encomenda criarEncomenda(String tipo, Apartamento apartamento) {
		Encomenda encomenda = new Encomenda();
		encomenda.setApartamento(apartamento);
		encomenda.setTipo("CORREIOS");
		
		this.encomendaService.save(encomenda);
		
		return encomenda;
	}
	
	private Visitante criarVisitante(String nome, Apartamento apartamento) {
		Visitante visitante = new Visitante();
		visitante.setDataVisita(new Date());
		visitante.setNome(nome);
		visitante.setApartamento(apartamento);
		
		this.visitanteService.save(visitante);
		
		return visitante;
	}

	private Veiculo criaVeiculo(String marca, String modelo, String placa, String Renavan, String cor, Morador morador) {
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

	private Morador criaMorador(Apartamento apartamento, Pessoa pessoa) {
		Morador mr1 = new Morador();
		mr1.setApartamento(apartamento);
		mr1.setPessoa(pessoa);
		
		this.moradorService.save(mr1);
		return mr1;
		
	}

	private Pessoa criaPessoa(String cpf, String email, String nome) {
		Pessoa p1 = new Pessoa();		
		p1.setCpf(cpf);
		p1.setEmail(email);
		p1.setNome(nome);
		
		this.pessoaService.save(p1);
		return p1;
		
	}

	private Apartamento criaApartamento(String nome, Long numero, Edificio edificio) {
		Apartamento ap1 = new Apartamento();
		ap1.setEdificio(edificio);
		ap1.setNumero(numero);
		ap1.setNome(nome);
		
		this.apartamentoService.save(ap1);
		
		return ap1;
		
	}

	private Edificio criaEdificio(String nome, String descricao, Bloco bloco) {
		Edificio ed1 = new Edificio();
		ed1.setNome(nome);
		ed1.setDescricao(descricao);
		ed1.setBloco(bloco);
		
		this.edificioService.save(ed1);
		return ed1;
	}

	private Bloco criaBloco(String nome, Condominio condominio) {
		Bloco bloco1 = new Bloco();
		bloco1.setNome("nome");
		bloco1.setCondominio(condominio);
		
		this.blocoService.save(bloco1);
		return bloco1;
	}

	private Area criaArea(String nome, String descricao, Condominio condominio) {
		Area area1 = new Area();
		area1.setNome(nome);
		area1.setDescricao(descricao);
		area1.setCondominio(condominio);
		
		this.areaService.save(area1);
		
		return area1;
	}

	private Condominio criaCondominio() {
		Condominio condominio = new Condominio();
		condominio.setName("Liberta Resort");
		condominio.setLogradouro("Av Di Cavalcanti");
		condominio.setNumero("25");
		condominio.setUf("RJ");
		
		this.condominioService.save(condominio);
		
		return condominio;
		
	}

}
