package br.com.thiago.condoApp;

import org.junit.After;
import org.junit.Before;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import br.com.thiago.condoApp.modelo.AuthenticationRequest;

import br.com.thiago.condoApp.servico.EdificioService;
import br.com.thiago.condoApp.util.ModeloUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebIntegrationTest
public class EdificioRestTest {

	
	private RestTemplate client;
	private AuthenticationRequest authenticationRequest;
	private String authenticationToken;
	
	private String authenticationRoute = "auth";
	
	@Autowired
	private ModeloUtil modeloUtil;
	
	@Autowired
	private EdificioService edificioService;
	

	@Before
	public void setUp() throws Exception {
		client = new RestTemplate();
	}

	@After
	public void tearDown() throws Exception {
		client = null;
	}
	
	
	
}
