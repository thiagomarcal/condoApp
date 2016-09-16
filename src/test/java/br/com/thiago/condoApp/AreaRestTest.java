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
import br.com.thiago.condoApp.security.TestApiConfig;
import br.com.thiago.condoApp.servico.AreaService;
import br.com.thiago.condoApp.util.ModeloUtil;
import br.com.thiago.condoApp.util.RequestEntityBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
public class AreaRestTest {

	private RestTemplate client;
	private AuthenticationRequest authenticationRequest;
	private String authenticationToken;
	
	private String authenticationRoute = "auth";
	
	@Autowired
	private ModeloUtil modeloUtil;
	
	@Autowired
	private AreaService areaService;

	@Before
	public void setUp() throws Exception {
		client = new RestTemplate();
	}

	@After
	public void tearDown() throws Exception {
		client = null;
	}
	
	
	@Test
	public void requisicaoPegaTodasAsAreas() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Area area1 = modeloUtil.criaArea("Piscina JUNIT", "Piscina Teste JUNIT");

		ResponseEntity<List<Area>> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("/areas"), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				new ParameterizedTypeReference<List<Area>>(){});

		try {
			
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			List<Area> listaAreas= responseEntity.getBody();
			
			for (Area area : listaAreas) {
				if (area.getId() == area1.getId()) {
					assertTrue(area.getNome().equals(area1.getNome()));
				}
			}
			
			this.areaService.delete(area1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	
	@Test
	public void requisicaoPegaAreaPorId() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Area area1 = modeloUtil.criaArea("Piscina JUNIT", "Piscina Teste JUNIT");

		ResponseEntity<Area> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("area/" + area1.getId()), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				Area.class);

		try {
			
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Area area= responseEntity.getBody();
			assertTrue(area.getNome().equals(area1.getNome()));
			
			this.areaService.delete(area1.getId());
			
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
	

}
