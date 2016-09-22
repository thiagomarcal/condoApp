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
import br.com.thiago.condoApp.modelo.Visitante;
import br.com.thiago.condoApp.security.TestApiConfig;
import br.com.thiago.condoApp.servico.VisitanteService;
import br.com.thiago.condoApp.util.ModeloUtil;
import br.com.thiago.condoApp.util.RequestEntityBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
public class VisitanteRestTest {
	
	private RestTemplate client;
	private AuthenticationRequest authenticationRequest;
	private String authenticationToken;
	
	private String authenticationRoute = "auth";
	
	@Autowired
	private ModeloUtil modeloUtil;
	
	@Autowired
	private VisitanteService visitanteService;
	
	
	@Before
	public void setUp() throws Exception {
		client = new RestTemplate();
	}

	@After
	public void tearDown() throws Exception {
		client = null;
	}
	
	
	@Test
	public void requisicaoPegarTodosOsVisitantes() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Visitante visitante1 = modeloUtil.criarVisitante("Marco MArques");
		
		this.visitanteService.save(visitante1);

		ResponseEntity<List<Visitante>> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("visitantes"), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				new ParameterizedTypeReference<List<Visitante>>(){});

		try {
			
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			List<Visitante> listaVisitante = responseEntity.getBody();
			
			for (Visitante visitante : listaVisitante) {
				if (visitante.getId() == visitante1.getId()) {
					assertTrue(visitante.getNome().equals(visitante1.getNome()));
				}
			}
			
			this.visitanteService.delete(visitante1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}

	
	
	@Test
	public void requisicaoPegarVisitantePorId() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Visitante visitante1 = modeloUtil.criarVisitante("Marco MArques");
				
		this.visitanteService.save(visitante1);

		ResponseEntity<Visitante> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("visitante/" + visitante1.getId()), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				Visitante.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Visitante visitante= responseEntity.getBody();
			assertTrue(visitante.getNome().equals(visitante1.getNome()));
			
			this.visitanteService.delete(visitante1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoPegarVisitantePorNome() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Visitante visitante1 = modeloUtil.criarVisitante("Marco MArques");
		
		this.visitanteService.save(visitante1);
		
		ResponseEntity<List<Visitante>> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("visitante?nome=" + visitante1.getNome()), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				new ParameterizedTypeReference<List<Visitante>>(){});

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			List<Visitante> listaVisitante= responseEntity.getBody();
			
			for (Visitante visitante : listaVisitante) {
				if (visitante.getId() == visitante1.getId()) {
					assertTrue(visitante.getNome().equals(visitante1.getNome()));
				}
			}
		
			this.visitanteService.delete(visitante1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoUpdateVisitante() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Visitante visitanteOrigem = modeloUtil.criarVisitante("Marco MArques");
		
		this.visitanteService.save(visitanteOrigem);
		
		Visitante visitanteAlterado = new Visitante();
		visitanteAlterado.setId(visitanteOrigem.getId());
		visitanteAlterado.setNome("TesteJunitCondoALTERADO");
		

		ResponseEntity<Visitante> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("visitante"), HttpMethod.PUT, buildAuthenticationComBodyEToken(visitanteAlterado),
				Visitante.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Visitante visitanteResp = responseEntity.getBody();
			assertTrue(visitanteResp.getNome().equals(visitanteAlterado.getNome()));
			this.visitanteService.delete(visitanteResp.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoDeleteVisitante() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Visitante visitanteOrigem = modeloUtil.criarVisitante("Marco MArques");
		
		this.visitanteService.save(visitanteOrigem);

		ResponseEntity<Long> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("visitante/" + visitanteOrigem.getId() ), HttpMethod.DELETE, buildAuthenticationSemBodyEToken(),
				Long.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			assertTrue(visitanteOrigem.getId().equals(responseEntity.getBody()));
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	
	@Test
	public void requisicaoAdicionaVisitanteParaApartamento() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();

		Visitante visitanteNovo = modeloUtil.criarVisitante("Marco MArques");

		Apartamento apartamento = modeloUtil.criaApartamento("210", (long) 210);

		ResponseEntity<Visitante> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("visitante/salvar/?apartamento=" + apartamento.getId()), HttpMethod.POST,
				buildAuthenticationComBodyEToken(visitanteNovo), Visitante.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Visitante visitanteResp = responseEntity.getBody();
			assertTrue(visitanteResp.getId().equals(visitanteNovo.getId()));

			this.visitanteService.delete(visitanteNovo.getId());

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
