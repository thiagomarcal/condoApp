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
import br.com.thiago.condoApp.modelo.Veiculo;
import br.com.thiago.condoApp.security.TestApiConfig;
import br.com.thiago.condoApp.servico.VeiculoService;
import br.com.thiago.condoApp.util.ModeloUtil;
import br.com.thiago.condoApp.util.RequestEntityBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
public class VeiculoRestTest {

	
	private RestTemplate client;
	private AuthenticationRequest authenticationRequest;
	private String authenticationToken;
	
	private String authenticationRoute = "auth";
	
	@Autowired
	private ModeloUtil modeloUtil;
	
	@Autowired
	private VeiculoService veiculoService;
	
	
	@Before
	public void setUp() throws Exception {
		client = new RestTemplate();
	}

	@After
	public void tearDown() throws Exception {
		client = null;
	}
	
	
	@Test
	public void requisicaoPegarTodosOsVeiculos() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Veiculo veiculo1 = modeloUtil.criaVeiculo("JUNIT", "JUNIT", "JUNIT", "0029123", "JUNIT");
				
		this.veiculoService.save(veiculo1);

		ResponseEntity<List<Veiculo>> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("veiculos"), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				new ParameterizedTypeReference<List<Veiculo>>(){});

		try {
			
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			List<Veiculo> listaVeiculo = responseEntity.getBody();
			
			for (Veiculo veiculo : listaVeiculo) {
				if (veiculo.getId() == veiculo1.getId()) {
					assertTrue(veiculo.getModelo().equals(veiculo1.getModelo()));
				}
			}
			
			this.veiculoService.delete(veiculo1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}

	
	
	@Test
	public void requisicaoPegarEncomendaPorId() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Veiculo veiculo1 = modeloUtil.criaVeiculo("JUNIT", "JUNIT", "JUNIT", "0029123", "JUNIT");
				
		this.veiculoService.save(veiculo1);

		ResponseEntity<Veiculo> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("veiculo/" + veiculo1.getId()), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				Veiculo.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Veiculo veiculo = responseEntity.getBody();
			assertTrue(veiculo.getModelo().equals(veiculo.getModelo()));
			
			this.veiculoService.delete(veiculo.getId());
			
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
