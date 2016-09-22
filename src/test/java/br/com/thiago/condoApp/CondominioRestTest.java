package br.com.thiago.condoApp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import br.com.thiago.condoApp.modelo.Area;
import br.com.thiago.condoApp.modelo.AuthenticationRequest;
import br.com.thiago.condoApp.modelo.AuthenticationResponse;
import br.com.thiago.condoApp.modelo.Bloco;
import br.com.thiago.condoApp.modelo.Condominio;
import br.com.thiago.condoApp.security.TestApiConfig;
import br.com.thiago.condoApp.servico.CondominioService;
import br.com.thiago.condoApp.util.ModeloUtil;
import br.com.thiago.condoApp.util.RequestEntityBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
public class CondominioRestTest {

	private RestTemplate client;
	private AuthenticationRequest authenticationRequest;
	private String authenticationToken;
	
	private String authenticationRoute = "auth";
	
	@Autowired
	private ModeloUtil modeloUtil;
	
	@Autowired
	private CondominioService condominioService;

	@Before
	public void setUp() throws Exception {
		client = new RestTemplate();
	}

	@After
	public void tearDown() throws Exception {
		client = null;
	}
	
	@Test
	public void requisicaoPegarTodosOsCondominios() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Condominio cond1 = modeloUtil.criaCondominio("TesteJunitCondo", "TesteJunit", "25", "RJ");
		
		this.condominioService.save(cond1);

		ResponseEntity<List<Condominio>> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("condominios"), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				new ParameterizedTypeReference<List<Condominio>>(){});

		try {
			
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			List<Condominio> listaCondominio= responseEntity.getBody();
			
			for (Condominio condominio : listaCondominio) {
				if (condominio.getId() == cond1.getId()) {
					assertTrue(condominio.getName().equals(cond1.getName()));
				}
			}
			
			this.condominioService.delete(cond1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	@Test
	public void requisicaoPegarCondominioPorId() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Condominio cond1 = modeloUtil.criaCondominio("TesteJunitCondo", "TesteJunit", "25", "RJ");
				
		this.condominioService.save(cond1);

		ResponseEntity<Condominio> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("condominio/" + cond1.getId()), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				Condominio.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Condominio condominio= responseEntity.getBody();
			assertTrue(condominio.getName().equals(cond1.getName()));
			
			this.condominioService.delete(cond1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	@Test
	public void requisicaoPegarCondominioPorNome() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Condominio cond1 = modeloUtil.criaCondominio("TesteJunitCondo", "TesteJunit", "25", "RJ");
		
		this.condominioService.save(cond1);
		
		ResponseEntity<List<Condominio>> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("condominio?name=" + cond1.getName()), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				new ParameterizedTypeReference<List<Condominio>>(){});

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			List<Condominio> listaCondominio= responseEntity.getBody();
			
			for (Condominio condominio : listaCondominio) {
				if (condominio.getId() == cond1.getId()) {
					assertTrue(condominio.getName().equals(cond1.getName()));
				}
			}
		
			this.condominioService.delete(cond1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	@Test
	public void requisicaoSalvarNovoCondominio() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Condominio cond1 = modeloUtil.criaCondominio("TesteJunitCondo", "TesteJunit", "25", "RJ");
		

		ResponseEntity<Condominio> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("condominio"), HttpMethod.POST, buildAuthenticationComBodyEToken(cond1),
				Condominio.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Condominio condoResp= responseEntity.getBody();
			assertTrue(condoResp.getName().equals(cond1.getName()));
			this.condominioService.delete(condoResp.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	@Test
	public void requisicaoUpdateCondominio() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Condominio condOrigem = modeloUtil.criaCondominio("TesteJunitCondo", "TesteJunit", "25", "RJ");
		
		this.condominioService.save(condOrigem);
		
		Condominio condAlterado = new Condominio();
		condAlterado.setId(condOrigem.getId());
		condAlterado.setName("TesteJunitCondoALTERADO");
		

		ResponseEntity<Condominio> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("condominio"), HttpMethod.PUT, buildAuthenticationComBodyEToken(condAlterado),
				Condominio.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Condominio condoResp= responseEntity.getBody();
			assertTrue(condoResp.getName().equals(condAlterado.getName()));
			this.condominioService.delete(condoResp.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoDeleteCondominio() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Condominio condOrigem = modeloUtil.criaCondominio("TesteJunitCondo", "TesteJunit", "25", "RJ");
		
		this.condominioService.save(condOrigem);

		ResponseEntity<Long> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("condominio/" + condOrigem.getId() ), HttpMethod.DELETE, buildAuthenticationSemBodyEToken(),
				Long.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			assertTrue(condOrigem.getId().equals(responseEntity.getBody()));
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoAdicionaAreaNoCondominio() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Condominio cond1 = modeloUtil.criaCondominio("TesteJunitCondo", "TesteJunit", "25", "RJ");
		this.condominioService.save(cond1);
	
		Area area = new Area();
		area.setNome("TesteJunit");
		area.setDescricao("TesteJunit");
				

		ResponseEntity<Area> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("condominio/"+cond1.getId()+"/area"), HttpMethod.POST, buildAuthenticationComBodyEToken(area),
				Area.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Area areaResp= responseEntity.getBody();
			assertTrue(areaResp.getNome().equals(area.getNome()));
			this.condominioService.delete(cond1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	@Test
	public void requisicaoAdicionaBlocoNoCondominio() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Condominio cond1 = modeloUtil.criaCondominio("TesteJunitCondo", "TesteJunit", "25", "RJ");
		this.condominioService.save(cond1);
	
		Bloco bloco = new Bloco();
		bloco.setNome("TesteJunit");
				

		ResponseEntity<Bloco> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("condominio/"+cond1.getId()+"/bloco"), HttpMethod.POST, buildAuthenticationComBodyEToken(bloco),
				Bloco.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Bloco blocoResp= responseEntity.getBody();
			assertTrue(blocoResp.getNome().equals(bloco.getNome()));
			this.condominioService.delete(cond1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	private void inicializaAutorizacaoValidaComTokenAdmin() {
		authenticationRequest = TestApiConfig.ADMIN_AUTHENTICATION_REQUEST;

		ResponseEntity<AuthenticationResponse> authenticationResponse = client.postForEntity(
				TestApiConfig.getAbsolutePath(authenticationRoute), authenticationRequest,
				AuthenticationResponse.class);

		authenticationToken = authenticationResponse.getBody().getToken();
	}
	
	@SuppressWarnings("unused")
	private void inicializaAutorizacaoValidaComTokenUser() {
		authenticationRequest = TestApiConfig.USER_AUTHENTICATION_REQUEST;

		ResponseEntity<AuthenticationResponse> authenticationResponse = client.postForEntity(
				TestApiConfig.getAbsolutePath(authenticationRoute), authenticationRequest,
				AuthenticationResponse.class);

		authenticationToken = authenticationResponse.getBody().getToken();
	}

	
	private HttpEntity<Object> buildAuthenticationSemBodyEToken() {
		return RequestEntityBuilder.buildRequestEntityWithoutBody(authenticationToken);
	}
	
	private HttpEntity<Object> buildAuthenticationComBodyEToken(Object body) {
		return RequestEntityBuilder.buildRequestEntity(authenticationToken, body);
	}

}
