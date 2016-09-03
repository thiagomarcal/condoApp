package br.com.thiago.condoApp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import br.com.thiago.condoApp.modelo.Area;
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

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private List<Condominio> condList = new ArrayList<Condominio>();

	@Autowired
	private CondominioService condominioService;
	
	@Autowired
	private CondominioRepository condominioRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays
				.asList(converters)
				.stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny().get();

		Assert.assertNotNull("the JSON message converter must not be null",
				this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setup() throws Exception {
		
		this.condominioRepository.deleteAll();
		
		Condominio cond1 = new Condominio();
		cond1.setName("Teste1");
		cond1.setEndereco("TesteEndereco");
		
		Area area1 = new Area();
		area1.setNome("Sauna");
		area1.setDescricao("SaunaT");
		area1.setCondominio(cond1);
		
//		Condominio cond2 = new Condominio();
//		cond1.setName("Teste2");
//		cond1.setEndereco("TesteEndereco2");
//		
//		Bloco bloco1 = new Bloco();
//		bloco1.setCondominio(cond1);
//		bloco1.setNome("Bloco1");
//		
//		Bloco bloco2 = new Bloco();
//		bloco1.setCondominio(cond1);
//		bloco1.setNome("Bloco2");
//		
//		Bloco bloco3 = new Bloco();
//		bloco1.setCondominio(cond1);
//		bloco1.setNome("Bloco3");
		
//		List<Bloco> listaBloco = new ArrayList<Bloco>();
		
//		listaBloco.add(bloco1);
//		listaBloco.add(bloco2);
//		listaBloco.add(bloco3);
//		
//		cond1.setBlocos(listaBloco);
//		cond2.setBlocos(listaBloco);
		
		
		List<Area> listaArea = new ArrayList<Area>();
		listaArea.add(area1);
		
		cond1.setAreas(listaArea);
		
		condList.add(cond1);
		
		condominioService.save(cond1);
//		condominioService.save(cond2);
		
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
