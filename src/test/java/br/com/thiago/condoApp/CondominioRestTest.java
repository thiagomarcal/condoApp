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

import br.com.thiago.condoApp.modelo.Apartamento;
import br.com.thiago.condoApp.modelo.Area;
import br.com.thiago.condoApp.modelo.Bloco;
import br.com.thiago.condoApp.modelo.Condominio;
import br.com.thiago.condoApp.modelo.Edificio;
import br.com.thiago.condoApp.modelo.Morador;
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
		
		Set<Area> listaArea = new HashSet<>();
		listaArea.add(area1);
		listaArea.add(area2);
		listaArea.add(area3);
		listaArea.add(area4);
		
		cond1.setAreas(listaArea);
				
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
		
		Edificio ed1 = new Edificio();
		ed1.setNome("Ed1");
		ed1.setDescricao("Ed1Desc");
		ed1.setBloco(bloco1);
		
		Edificio ed2 = new Edificio();
		ed2.setNome("Ed2");
		ed2.setDescricao("Ed2Desc");
		ed2.setBloco(bloco2);
		
		Set<Edificio> listaEdificio1 = new HashSet<>();
		listaEdificio1.add(ed1);
		
		Set<Edificio> listaEdificio2 = new HashSet<>();
		listaEdificio2.add(ed2);
		
		bloco1.setEdificios(listaEdificio1);
		bloco2.setEdificios(listaEdificio2);
		
		
		Apartamento ap1 = new Apartamento();
		ap1.setEdificio(ed1);
		ap1.setNumero(101L);
		ap1.setNome("Ap 101");
		
		Set<Apartamento> listaApart1 = new HashSet<>();
		listaApart1.add(ap1);
		
		ed1.setApartamentos(listaApart1);
		
		
		Apartamento ap2 = new Apartamento();
		ap2.setEdificio(ed2);
		ap2.setNumero(101L);
		ap2.setNome("Ap 101");
		
		Set<Apartamento> listaApart2 = new HashSet<>();
		listaApart2.add(ap2);
		
		ed2.setApartamentos(listaApart2);
		
		Morador mr1 = new Morador();
		mr1.setApartamento(ap1);
		mr1.setCpf("11111111111");
		mr1.setEmail("thiagormarcal@gmail.com");
		mr1.setNome("Thiago Marcal");
		
		Set<Morador> listaMorador1 = new HashSet<>();
		listaMorador1.add(mr1);
		
		Morador mr2 = new Morador();
		mr2.setApartamento(ap2);
		mr2.setCpf("222222222");
		mr2.setEmail("johndoe@gmail.com");
		mr2.setNome("John Doe");
		
		Set<Morador> listaMorador2 = new HashSet<>();
		listaMorador2.add(mr2);
		
		ap1.setMoradores(listaMorador1);
		ap2.setMoradores(listaMorador2);
	
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
