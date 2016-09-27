//package br.com.thiago.condoApp;
//
//import static org.hamcrest.core.Is.is;
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.boot.test.WebIntegrationTest;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.web.client.RestTemplate;
//
//import br.com.thiago.condoApp.modelo.AuthenticationRequest;
//import br.com.thiago.condoApp.modelo.AuthenticationResponse;
//import br.com.thiago.condoApp.modelo.Mensagem;
//import br.com.thiago.condoApp.modelo.MuralBloco;
//import br.com.thiago.condoApp.security.TestApiConfig;
//import br.com.thiago.condoApp.servico.MuralBlocoService;
//import br.com.thiago.condoApp.util.ModeloUtil;
//import br.com.thiago.condoApp.util.RequestEntityBuilder;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
//@WebIntegrationTest
//public class MuralBlocoRestTest {
//
//	private RestTemplate client;
//	private AuthenticationRequest authenticationRequest;
//	private String authenticationToken;
//	
//	private String authenticationRoute = "auth";
//	
//	@Autowired
//	private ModeloUtil modeloUtil;
//	
//	@Autowired
//	private MuralBlocoService muralBlocoService;
//	
//	
//	@Before
//	public void setUp() throws Exception {
//		client = new RestTemplate();
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		client = null;
//	}
//	
//	@Test
//	public void requisicaoPegarTodosOsMurais() throws Exception {
//		this.inicializaAutorizacaoValidaComTokenAdmin();
//		
//		MuralBloco muralBloco = modeloUtil.criaMuralBloco();
//
//		ResponseEntity<List<MuralBloco>> responseEntity = client.exchange(
//				TestApiConfig.getAbsolutePath("muralBlocos"), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
//				new ParameterizedTypeReference<List<MuralBloco>>(){});
//
//		try {
//			
//			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
//			List<MuralBloco> listaMuralbloco = responseEntity.getBody();
//			
//			for (MuralBloco muralBlocoResp : listaMuralbloco) {
//				if (muralBlocoResp.getId() == muralBloco.getId()) {
//					assertTrue(muralBlocoResp.getId().equals(muralBloco.getId()));
//				}
//			}
//			
//			this.muralBlocoService.delete(muralBloco.getId());
//			
//		} catch (Exception e) {
//			fail("Should have returned an HTTP 400: Ok status code");
//		}
//	}
//	
//	
//	
//	@Test
//	public void requisicaoPegarMuralBlocoPorId() throws Exception {
//		this.inicializaAutorizacaoValidaComTokenAdmin();
//		
//		MuralBloco muralBloco = modeloUtil.criaMuralBloco();
//
//		ResponseEntity<MuralBloco> responseEntity = client.exchange(
//				TestApiConfig.getAbsolutePath("muralBloco/" + muralBloco.getId()), HttpMethod.GET, buildAuthenticationSemBodyEToken(),
//				MuralBloco.class);
//
//		try {
//
//			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
//			MuralBloco muralBlocoResp = responseEntity.getBody();
//			assertTrue(muralBlocoResp.getId().equals(muralBloco.getId()));
//			
//			this.muralBlocoService.delete(muralBloco.getId());
//			
//		} catch (Exception e) {
//			fail("Should have returned an HTTP 400: Ok status code");
//		}
//	}
//	
//	
//	
//	@Test
//	public void requisicaoDeleteMuralBloco() throws Exception {
//		this.inicializaAutorizacaoValidaComTokenAdmin();
//		
//		MuralBloco muralBloco = modeloUtil.criaMuralBloco();
//		
//		ResponseEntity<Long> responseEntity = client.exchange(
//				TestApiConfig.getAbsolutePath("muralBloco/" + muralBloco.getId() ), HttpMethod.DELETE, buildAuthenticationSemBodyEToken(),
//				Long.class);
//
//		try {
//			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
//			assertTrue(muralBloco.getId().equals(responseEntity.getBody()));
//			
//		} catch (Exception e) {
//			fail("Should have returned an HTTP 400: Ok status code");
//		}
//	}
//	
//	
//	@Test
//	public void requisicaoUpdateMuralBloco() throws Exception {
//		this.inicializaAutorizacaoValidaComTokenAdmin();
//		
//		MuralBloco muralBloco = modeloUtil.criaMuralBloco();
//		
//		Mensagem ms2 = modeloUtil.criaMensagem("TesteJunitUpdate", new Date());
//		
//		muralBloco.getMensagens().add(ms2);
//	
//		ResponseEntity<MuralBloco> responseEntity = client.exchange(
//				TestApiConfig.getAbsolutePath("muralBloco"), HttpMethod.PUT, buildAuthenticationComBodyEToken(muralBloco),
//				MuralBloco.class);
//
//		try {
//			assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
//			MuralBloco muralBlocoResp = responseEntity.getBody();
//			
//			assertTrue(muralBlocoResp.getMensagens().size() == 2);
//			
//			Set<String> mensagensSet = new HashSet<>();
//			for (Mensagem mensagem : muralBlocoResp.getMensagens()) {
//				mensagensSet.add(mensagem.getMensagem());
//			}
//			
//			assertTrue(mensagensSet.contains("TESTE JUNIT BLOCO"));
//			assertTrue(mensagensSet.contains("TesteJunitUpdate"));
//			
//			this.muralBlocoService.delete(muralBlocoResp.getId());
//			
//		} catch (Exception e) {
//			fail("Should have returned an HTTP 400: Ok status code");
//		}
//	}
//	
//
//	private void inicializaAutorizacaoValidaComTokenAdmin() {
//		authenticationRequest = TestApiConfig.ADMIN_AUTHENTICATION_REQUEST;
//
//		ResponseEntity<AuthenticationResponse> authenticationResponse = client.postForEntity(
//				TestApiConfig.getAbsolutePath(authenticationRoute), authenticationRequest,
//				AuthenticationResponse.class);
//
//		authenticationToken = authenticationResponse.getBody().getToken();
//	}
//	
//	@SuppressWarnings("unused")
//	private void inicializaAutorizacaoValidaComTokenUser() {
//		authenticationRequest = TestApiConfig.USER_AUTHENTICATION_REQUEST;
//
//		ResponseEntity<AuthenticationResponse> authenticationResponse = client.postForEntity(
//				TestApiConfig.getAbsolutePath(authenticationRoute), authenticationRequest,
//				AuthenticationResponse.class);
//
//		authenticationToken = authenticationResponse.getBody().getToken();
//	}
//
//	
//	private HttpEntity<Object> buildAuthenticationSemBodyEToken() {
//		return RequestEntityBuilder.buildRequestEntityWithoutBody(authenticationToken);
//	}
//	
//
//	private HttpEntity<Object> buildAuthenticationComBodyEToken(Object body) {
//		return RequestEntityBuilder.buildRequestEntity(authenticationToken, body);
//	}
//	
//}
