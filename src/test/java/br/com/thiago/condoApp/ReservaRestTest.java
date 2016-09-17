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
import br.com.thiago.condoApp.modelo.Morador;
import br.com.thiago.condoApp.modelo.Reserva;
import br.com.thiago.condoApp.security.TestApiConfig;
import br.com.thiago.condoApp.servico.ReservaService;
import br.com.thiago.condoApp.util.ModeloUtil;
import br.com.thiago.condoApp.util.RequestEntityBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
public class ReservaRestTest {

	

	private RestTemplate client;
	private AuthenticationRequest authenticationRequest;
	private String authenticationToken;
	
	private String authenticationRoute = "auth";
	
	@Autowired
	private ModeloUtil modeloUtil;
	
	@Autowired
	private ReservaService reservaService;

	@Before
	public void setUp() throws Exception {
		client = new RestTemplate();
	}

	@After
	public void tearDown() throws Exception {
		client = null;
	}
	
	
	@Test
	public void requisicaoPegarBuscaTodasAsReservas() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Reserva reserva1 = modeloUtil.criaReserva();
;		
		this.reservaService.save(reserva1);

		
		ResponseEntity<List<Reserva>> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("reservas"), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				new ParameterizedTypeReference<List<Reserva>>(){});

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			List<Reserva> listaReservas= responseEntity.getBody();
			
			for (Reserva reserva : listaReservas) {
				if (reserva.getId() == reserva1.getId()) {
					assertTrue(reserva.getSituacao().equals(reserva1.getSituacao()));
				}
			}
			
			this.reservaService.delete(reserva1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	
	@Test
	public void requisicaoPegarReservaPorId() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Reserva reserva1 = modeloUtil.criaReserva();
		
		this.reservaService.save(reserva1);

		ResponseEntity<Reserva> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("reserva/" + reserva1.getId()), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				Reserva.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Reserva reserva = responseEntity.getBody();
			assertTrue(reserva.getId().equals(reserva1.getId()));
			
			this.reservaService.delete(reserva1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	

	@Test
	public void requisicaoPegarReservaPorSituacao() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Reserva reserva1 = modeloUtil.criaReserva();
		
		this.reservaService.save(reserva1);

		
		ResponseEntity<List<Reserva>> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("reserva?situacao=" + reserva1.getSituacao()), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				new ParameterizedTypeReference<List<Reserva>>(){});

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			List<Reserva> listaReserva= responseEntity.getBody();
			
			for (Reserva reserva : listaReserva) {
				if (reserva.getId() == reserva1.getId()) {
					assertTrue(reserva.getSituacao().equals(reserva1.getSituacao()));
				}
			}
		
			this.reservaService.delete(reserva1.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoUpdateReserva() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Reserva reserva1 = modeloUtil.criaReserva();
		
		ResponseEntity<Reserva> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("reserva"), HttpMethod.POST, buildAuthenticationComBodyEToken(reserva1),
				Reserva.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Reserva reservaResp = responseEntity.getBody();
			assertTrue(reservaResp.getId().equals(reserva1.getId()));
			
			this.reservaService.delete(reservaResp.getId());
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoDeleteReserva() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Reserva reservaOrigem = modeloUtil.criaReserva();

		this.reservaService.save(reservaOrigem);
		
		ResponseEntity<Long> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("reserva/" + reservaOrigem.getId() ), HttpMethod.DELETE, buildAuthenticationSemBodyEToken(),
				Long.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			assertTrue(reservaOrigem.getId().equals(responseEntity.getBody()));
			
		} catch (Exception e) {
			fail("Should have returned an HTTP 400: Ok status code");
		}
	}
	
	
	@Test
	public void requisicaoAdicionaReservaParaMorador() throws Exception {
		this.inicializaAutorizacaoValidaComTokenAdmin();
		
		Reserva reserva = modeloUtil.criaReserva();

		this.reservaService.save(reserva);

		
		ResponseEntity<List<Area>> responseEntityArea = client.exchange(
				TestApiConfig.getAbsolutePath("area"), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
				new ParameterizedTypeReference<List<Area>>(){});
		
		Area areaResp = null;
		
		try {
			assertThat(responseEntityArea.getStatusCode(), is(HttpStatus.OK));
			areaResp = (Area) responseEntityArea.getBody();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		Morador morador = modeloUtil.criaMorador();

		ResponseEntity<Reserva> responseEntity = client.exchange(
				TestApiConfig.getAbsolutePath("reserva/salvar/?area=" + areaResp.getId() +"morador=" + morador.getId()), HttpMethod.POST, buildAuthenticationComBodyEToken(reserva),
				Reserva.class);

		try {
			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			Reserva reservaResp= responseEntity.getBody();
			assertTrue(reservaResp.getId().equals(reserva.getId()));
			this.reservaService.delete(reserva.getId());
			
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
