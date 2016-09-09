package br.com.thiago.condoApp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import br.com.thiago.condoApp.modelo.Visitante;
import br.com.thiago.condoApp.repository.VisitanteRepository;
import br.com.thiago.condoApp.servico.ApartamentoService;
import br.com.thiago.condoApp.servico.VisitanteService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class VisitanteRestTest {
	
	
	private MediaType contentType = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	
	private MockMvc mockMvc;
	
	@Autowired
	private VisitanteService visitanteService;
	
	@Autowired
	private ApartamentoService apartamentoService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	@Before
	public void setup() throws Exception {
		

		this.criarVisita();
	}
	
	
	public void criarVisita() {
		Apartamento apartamento = apartamentoService.findOne((long)1);
		
		Visitante visitante = new Visitante();
		visitante.setDataVisita(new Date());
		visitante.setNome("Marco Marques");
		visitante.setApartamento(apartamento);
		
		visitanteService.save(visitante);
		
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
	
	@After
	public void shutdown() {
		
	}
	
	
	@Test
	public void readEncomendas() throws Exception {
		mockMvc.perform(get("/encomendas"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType));
	}
	
}
