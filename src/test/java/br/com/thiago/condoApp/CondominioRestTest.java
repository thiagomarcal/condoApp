package br.com.thiago.condoApp;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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

import br.com.thiago.condoApp.modelo.Condominio;
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
public class CondominioRestTest {

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
		
		System.out.println("CONDOMINIO " + cond.getName() + " CRIADO COM SUCESSO!");
	}
	
	


	private Condominio criaCondominio() {
		Condominio condominio = new Condominio();
		condominio.setName("Liberta Resort");
		condominio.setLogradouro("Av Di Cavalcanti");
		condominio.setNumero("25");
		condominio.setUf("RJ");
		
		Condominio condominio2 = new Condominio();
		condominio2.setName("Beija Flor");
		condominio2.setLogradouro("Estrada João Paulo");
		condominio2.setNumero("260");
		condominio2.setUf("RJ");
		
		
		List<Condominio> listaCondominio = new ArrayList();
		
		listaCondominio.add(condominio);
		listaCondominio.add(condominio2);
		
		
		for(Condominio cond : listaCondominio){
		  this.condominioService.save(cond);
		}
		
		
		
		return condominio;
		
	}
	
}
