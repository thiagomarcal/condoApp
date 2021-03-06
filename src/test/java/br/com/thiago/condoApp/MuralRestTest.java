package br.com.thiago.condoApp;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import br.com.thiago.condoApp.modelo.Mensagem;
import br.com.thiago.condoApp.modelo.Mural;
import br.com.thiago.condoApp.security.TestApiConfig;
import br.com.thiago.condoApp.servico.MuralService;
import br.com.thiago.condoApp.util.ModeloUtil;
import br.com.thiago.condoApp.util.RequestEntityBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
public class MuralRestTest {
	
	
	private RestTemplate client;
	private AuthenticationRequest authenticationRequest;
	private String authenticationToken;
	
	private String authenticationRoute = "auth";
	
	@Autowired
	private ModeloUtil modeloUtil;
	
	@Autowired
	private MuralService muralService;
	
	@Before
	public void setUp() throws Exception {
		client = new RestTemplate();
	}

	@After
	public void tearDown() throws Exception {
		client = null;
	}
	
	
	@Test
	public void requisicaoPegarTodosOsMurais() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Mural muralCondo = modeloUtil.criaMuralCondominio();
		
		this.muralService.save(muralCondo);

		ResponseEntity<List<Mural>> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("murais"), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				new ParameterizedTypeReference<List<Mural>>(){});

		try {
			
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			List<Mural> listaMuralCondo = responseEntity.getBody();
			
			for (Mural muralCondominio : listaMuralCondo) {
				if (muralCondominio.getId() == muralCondo.getId()) {
					assertTrue(muralCondominio.getId().equals(muralCondo.getId()));
				}
			}
			
			this.muralService.delete(muralCondo.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	@Test
	public void requisicaoPegarMuralCondominioPorId() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Mural muralCondo = modeloUtil.criaMuralCondominio();

		ResponseEntity<Mural> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("mural/" + muralCondo.getId()), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				Mural.class);

		try {

			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Mural muraCondoResp = responseEntity.getBody();
			assertTrue(muraCondoResp.getId().equals(muralCondo.getId()));
			
			this.muralService.delete(muralCondo.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoDeleteMuralCondomominio() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Mural muralCondo = modeloUtil.criaMuralCondominio();
		
		ResponseEntity<Long> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("mural/" + muralCondo.getId() ), HttpMethod.DELETE, buildAuthenticationSemBodyEToken(),
				Long.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			assertTrue(muralCondo.getId().equals(responseEntity.getBody()));
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoUpdateMuralCondominio() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Mural muralCondo = modeloUtil.criaMuralCondominio();
		
		Mensagem ms2 = modeloUtil.criaMensagem("testeJunitUpdate", new Date());
		
		muralCondo.getMensagens().add(ms2);
	
		ResponseEntity<Mural> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("mural"), HttpMethod.PUT, buildAuthenticationComBodyEToken(muralCondo),
				Mural.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Mural muralCondoResp = responseEntity.getBody();
			
			assertTrue(muralCondoResp.getMensagens().size() == 2);
			
			Set<String> mensagensSet = new HashSet<>();
			for (Mensagem mensagem : muralCondoResp.getMensagens()) {
				mensagensSet.add(mensagem.getMensagem());
			}
			
			assertTrue(mensagensSet.contains("TESTE JUNIT"));
			assertTrue(mensagensSet.contains("testeJunitUpdate"));
		
			
			this.muralService.delete(muralCondoResp.getId());
			
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
