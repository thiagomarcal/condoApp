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

import br.com.thiago.condoApp.modelo.AuthenticationRequest;
import br.com.thiago.condoApp.modelo.AuthenticationResponse;
import br.com.thiago.condoApp.modelo.Bloco;
import br.com.thiago.condoApp.modelo.Edificio;
import br.com.thiago.condoApp.security.TestApiConfig;
import br.com.thiago.condoApp.servico.BlocoService;
import br.com.thiago.condoApp.util.ModeloUtil;
import br.com.thiago.condoApp.util.RequestEntityBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
public class BlocoRestTest {
	
	private RestTemplate client;
	private AuthenticationRequest authenticationRequest;
	private String authenticationToken;
	
	private String authenticationRoute = "auth";
	
	@Autowired
	private ModeloUtil modeloUtil;
	
	@Autowired
	private BlocoService blocoService;
	

	@Before
	public void setUp() throws Exception {
		client = new RestTemplate();
	}

	@After
	public void tearDown() throws Exception {
		client = null;
	}
	
	@Test
	public void requisicaoPegarTodosOsBlocos() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		
		Bloco bloco1 = modeloUtil.criaBlocoComCondominio("Bloco 1");
		Bloco bloco2 = modeloUtil.criaBlocoComCondominio("Bloco 2");
		
		this.blocoService.save(bloco1);
		this.blocoService.save(bloco2);

		ResponseEntity<List<Bloco>> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("blocos"), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				new ParameterizedTypeReference<List<Bloco>>(){});

		try {
			
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			List<Bloco> listaBloco= responseEntity.getBody();
			
			for (Bloco bloco : listaBloco) {
				if (bloco.getId() == bloco1.getId() || bloco.getId() == bloco2.getId()) {
					assertTrue(bloco.getNome().equals(bloco1.getNome()) || bloco.getNome().equals(bloco2.getNome()));
				}
			}
			
			this.blocoService.delete(bloco1.getId());
			this.blocoService.delete(bloco2.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	

	@Test
	public void requisicaoPegarBlocoPorId() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Bloco bloco1 = modeloUtil.criaBlocoComCondominio("Bloco 1");
		this.blocoService.save(bloco1);
		
		ResponseEntity<Bloco> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("bloco/" + bloco1.getId()), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				Bloco.class);
		
		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Bloco bloco= responseEntity.getBody();
			assertTrue(bloco.getNome().equals(bloco1.getNome()));
			
			this.blocoService.delete(bloco1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
		
	}
	
	@Test
	public void requisicaoPegarBlocoPorNome() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Bloco bloco1 = modeloUtil.criaBlocoComCondominio("Bloco1 Teste JUnit");
		
		this.blocoService.save(bloco1);
		
		ResponseEntity<List<Bloco>> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("bloco?nome=" + bloco1.getNome()), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				new ParameterizedTypeReference<List<Bloco>>(){});

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			List<Bloco> listaCondominio= responseEntity.getBody();
			
			for (Bloco bloco : listaCondominio) {
				if (bloco.getId() == bloco1.getId()) {
					assertTrue(bloco.getNome().equals(bloco1.getNome()));
				}
			}
		
			this.blocoService.delete(bloco1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoSalvarNovoBloco() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Bloco bloco1 = modeloUtil.criaBlocoComCondominio("Bloco1 Teste JUnit");
		

		ResponseEntity<Bloco> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("bloco"), HttpMethod.POST, buildAuthenticationComBodyEToken(bloco1),
				Bloco.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Bloco blocoResp= responseEntity.getBody();
			assertTrue(blocoResp.getNome().equals(bloco1.getNome()));
			this.blocoService.delete(blocoResp.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoUpdateBloco() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Bloco blocoOrigem = modeloUtil.criaBlocoComCondominio("Bloco1 Teste JUnit");
		
		this.blocoService.save(blocoOrigem);
		
		Bloco blocoAlterado = new Bloco();
		blocoAlterado.setId(blocoOrigem.getId());
		blocoAlterado.setNome("Bloco1 Teste JUnit Alterado");
		

		ResponseEntity<Bloco> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("bloco"), HttpMethod.PUT, buildAuthenticationComBodyEToken(blocoAlterado),
				Bloco.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Bloco blocoResp= responseEntity.getBody();
			assertTrue(blocoResp.getNome().equals(blocoAlterado.getNome()));
			this.blocoService.delete(blocoResp.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoDeleteBloco() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Bloco blocoOrigem = modeloUtil.criaBlocoComCondominio("Bloco1 Teste JUnit");
		
		this.blocoService.save(blocoOrigem);

		ResponseEntity<Long> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("bloco/" + blocoOrigem.getId() ), HttpMethod.DELETE, buildAuthenticationSemBodyEToken(),
				Long.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			assertTrue(blocoOrigem.getId().equals(responseEntity.getBody()));
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoAdicionaEdificioNoBloco() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Bloco bloco1 = modeloUtil.criaBlocoComCondominio("Bloco1 Teste JUnit");
		this.blocoService.save(bloco1);
	
		Edificio edificio = new Edificio();
		edificio.setNome("Edificio1");
		edificio.setDescricao("Edificio1 Teste JUnit");
				

		ResponseEntity<Edificio> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("/bloco/"+ bloco1.getId() + "/edificio"), HttpMethod.POST, buildAuthenticationComBodyEToken(edificio),
				Edificio.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Edificio edificioResp= responseEntity.getBody();
			assertTrue(edificioResp.getNome().equals(edificio.getNome()));
			this.blocoService.delete(bloco1.getId());
			
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
