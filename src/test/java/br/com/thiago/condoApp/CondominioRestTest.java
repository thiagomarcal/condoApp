package br.com.thiago.condoApp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

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

import br.com.thiago.condoApp.modelo.Area;
import br.com.thiago.condoApp.modelo.Bloco;
import br.com.thiago.condoApp.modelo.Condominio;
import br.com.thiago.condoApp.repository.CondominioRepository;
import br.com.thiago.condoApp.servico.CondominioService;

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
	private CondominioRepository condominioRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		
		this.condominioRepository.deleteAll();
		
		Condominio cond1 = new Condominio();
		cond1.setName("Libertá Resort");
		cond1.setEndereco("Av Di Cavalcanti 25");
		
		Area area1 = new Area();
		area1.setNome("Sauna");
		area1.setDescricao("Sauna");
		area1.setCondominio(cond1);
		
		Area area2 = new Area();
		area2.setNome("Piscina");
		area2.setDescricao("Piscina");
		area2.setCondominio(cond1);
		
		Area area3 = new Area();
		area3.setNome("Salão de Festa");
		area3.setDescricao("Salão de Festa");
		area3.setCondominio(cond1);
		
		Area area4 = new Area();
		area4.setNome("Churrasqueira");
		area4.setDescricao("Churrasqueira");
		area4.setCondominio(cond1);
				
		Bloco bloco1 = new Bloco();
		bloco1.setCondominio(cond1);
		bloco1.setNome("Bloco1");
		
		Bloco bloco2 = new Bloco();
		bloco2.setCondominio(cond1);
		bloco2.setNome("Bloco2");
		
		Set<Bloco> listaBloco = new HashSet<Bloco>();
		
		listaBloco.add(bloco1);
		listaBloco.add(bloco2);
		
		cond1.setBlocos(listaBloco);
		
		Set<Area> listaArea = new HashSet<>();
		listaArea.add(area1);
		listaArea.add(area2);
		listaArea.add(area3);
		listaArea.add(area4);
		
		cond1.setAreas(listaArea);
		
		condominioService.save(cond1);
		
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}
	
	@After
	public void shutdown() {
		
	}
	
	@Test
	public void readCondominios() throws Exception {
		mockMvc.perform(get("/condominios"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(contentType));
	}

}
