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

import br.com.thiago.condoApp.modelo.Apartamento;
import br.com.thiago.condoApp.modelo.AuthenticationRequest;
import br.com.thiago.condoApp.modelo.AuthenticationResponse;
import br.com.thiago.condoApp.modelo.Encomenda;
import br.com.thiago.condoApp.modelo.Encomenda.Tipo;
import br.com.thiago.condoApp.security.TestApiConfig;
import br.com.thiago.condoApp.servico.EncomendaService;
import br.com.thiago.condoApp.util.ModeloUtil;
import br.com.thiago.condoApp.util.RequestEntityBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
public class EncomendaRestTest {


	private RestTemplate client;
	private AuthenticationRequest authenticationRequest;
	private String authenticationToken;
	
	private String authenticationRoute = "auth";
	
	@Autowired
	private ModeloUtil modeloUtil;
	
	@Autowired
	private EncomendaService encomendaService;
	

	@Before
	public void setUp() throws Exception {
		client = new RestTemplate();
	}

	@After
	public void tearDown() throws Exception {
		client = null;
	}
	
	
	@Test
	public void requisicaoPegarTodasAsEncomendas() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Encomenda encomenda1 = modeloUtil.criarEncomenda("CORREIOS");

		ResponseEntity<List<Encomenda>> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("encomendas"), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				new ParameterizedTypeReference<List<Encomenda>>(){});

		try {
			
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			List<Encomenda> listaEncomendas = responseEntity.getBody();
			
			for (Encomenda encomenda : listaEncomendas) {
				if (encomenda.getId() == encomenda1.getId()) {
					assertTrue(encomenda.getNome().equals(encomenda1.getNome()));
				}
			}
			
			this.encomendaService.delete(encomenda1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}

	
	
	@Test
	public void requisicaoPegarEncomendaPorId() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Encomenda encomenda1 = modeloUtil.criarEncomenda("CORREIOS");

		ResponseEntity<Encomenda> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("encomenda/" + encomenda1.getId()), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				Encomenda.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Encomenda encomenda = responseEntity.getBody();
			assertTrue(encomenda.getTipo().equals(encomenda1.getTipo()));
			
			this.encomendaService.delete(encomenda1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoPegarEncomendaPorTipo() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Encomenda encomenda1 = modeloUtil.criarEncomenda("CORREIOS");
		
		ResponseEntity<List<Encomenda>> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("encomenda?tipo=" + encomenda1.getTipo()), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				new ParameterizedTypeReference<List<Encomenda>>(){});

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			List<Encomenda> listaEncomenda= responseEntity.getBody();
			
			for (Encomenda encomenda : listaEncomenda) {
				if (encomenda.getId() == encomenda1.getId()) {
					assertTrue(encomenda.getTipo().equals(encomenda1.getTipo()));
				}
			}
		
			this.encomendaService.delete(encomenda1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoAdicionaEnomendaParaApartamento() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();

		Encomenda encomendaNova = modeloUtil.criarEncomenda("CORREIOS");

		Apartamento apartamento = modeloUtil.criaApartamento("210", (long) 210);

		ResponseEntity<Encomenda> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("/encomenda/salvar?apartamento=" + apartamento.getId()), HttpMethod.POST,
				buildAuthenticationComBodyEToken(encomendaNova), Encomenda.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Encomenda encomendaResp = responseEntity.getBody();
			assertTrue(encomendaResp.getId().equals(encomendaNova.getId()));

			this.encomendaService.delete(encomendaNova.getId());

		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	
	@Test
	public void requisicaoUpdateEncomenda() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Encomenda encomendaOrigem = modeloUtil.criarEncomenda("CORREIOS");
		
		Encomenda encomendaAlterada = new Encomenda();
		encomendaAlterada.setId(encomendaOrigem.getId());
		encomendaAlterada.setTipo(Tipo.CORREIOS);
		

		ResponseEntity<Encomenda> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("encomenda"), HttpMethod.PUT, buildAuthenticationComBodyEToken(encomendaAlterada),
				Encomenda.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Encomenda encomendaResp = responseEntity.getBody();
			assertTrue(encomendaResp.getTipo().equals(encomendaAlterada.getTipo()));
			this.encomendaService.delete(encomendaResp.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoDeleteEncomenda() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Encomenda encomendaOrigem = modeloUtil.criarEncomenda("CORREIOS");
		
		this.encomendaService.save(encomendaOrigem);

		ResponseEntity<Long> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("encomenda/" + encomendaOrigem.getId() ), HttpMethod.DELETE, buildAuthenticationSemBodyEToken(),
				Long.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			assertTrue(encomendaOrigem.getId().equals(responseEntity.getBody()));
			
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
