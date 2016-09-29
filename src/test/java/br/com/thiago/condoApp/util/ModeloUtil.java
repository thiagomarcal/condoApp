package br.com.thiago.condoApp.util;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiago.condoApp.modelo.Apartamento;
import br.com.thiago.condoApp.modelo.Area;
import br.com.thiago.condoApp.modelo.Bloco;
import br.com.thiago.condoApp.modelo.Condominio;
import br.com.thiago.condoApp.modelo.Destino;
import br.com.thiago.condoApp.modelo.Edificio;
import br.com.thiago.condoApp.modelo.Encomenda;
import br.com.thiago.condoApp.modelo.Encomenda.Tipo;
import br.com.thiago.condoApp.modelo.Mensagem;
import br.com.thiago.condoApp.modelo.Morador;
import br.com.thiago.condoApp.modelo.Mural;
import br.com.thiago.condoApp.modelo.Pessoa;
import br.com.thiago.condoApp.modelo.Reserva;
import br.com.thiago.condoApp.modelo.Reserva.Situacao;
import br.com.thiago.condoApp.modelo.Veiculo;
import br.com.thiago.condoApp.modelo.Visitante;
import br.com.thiago.condoApp.servico.ApartamentoService;
import br.com.thiago.condoApp.servico.AreaService;
import br.com.thiago.condoApp.servico.BlocoService;
import br.com.thiago.condoApp.servico.CondominioService;
import br.com.thiago.condoApp.servico.EdificioService;
import br.com.thiago.condoApp.servico.EncomendaService;
import br.com.thiago.condoApp.servico.MoradorService;
import br.com.thiago.condoApp.servico.MuralService;
import br.com.thiago.condoApp.servico.PessoaService;
import br.com.thiago.condoApp.servico.ReservaService;
import br.com.thiago.condoApp.servico.VeiculoService;
import br.com.thiago.condoApp.servico.VisitanteService;

@Service
public class ModeloUtil {

	@Autowired
	private CondominioService condominioService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private BlocoService blocoService;

	@Autowired
	private EdificioService edificioService;

	@Autowired
	private ApartamentoService apartamentoService;

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private MoradorService moradorService;

	@Autowired
	private VeiculoService veiculoService;

	@Autowired
	private EncomendaService encomendaService;

	@Autowired
	private VisitanteService visitanteService;

	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private MuralService muralService;
	

	public Reserva criaReserva() {
		Morador morador = this.criaMorador();
		Area area = this.criaArea("PRISALAO", "Priscina do Salão");

		Reserva reserva = new Reserva();
		reserva.setDataInicio(new Date());
		reserva.setDataFim(new Date());
		reserva.setMorador(morador);
		reserva.setSituacao(Situacao.EM_ANDAMENTO);
		reserva.setArea(area);

		this.reservaService.save(reserva);

		return reserva;

	}

	public Encomenda criarEncomenda(String tipo, Apartamento apartamento) {
		Encomenda encomenda = new Encomenda();
		encomenda.setApartamento(apartamento);
		encomenda.setTipo(Tipo.CORREIOS);

		this.encomendaService.save(encomenda);

		return encomenda;
	}
	
	
	public Encomenda criarEncomenda(String tipo) {

		Apartamento apartamento = this.criaApartamento("Apartamento 210", (long) 210);

		Encomenda encomenda = new Encomenda();
		encomenda.setApartamento(apartamento);
		encomenda.setNome("CORREIOS");
		encomenda.setTipo(Tipo.EMPRESA);

		this.encomendaService.save(encomenda);

		return encomenda;
	}

	public Visitante criarVisitante(String nome, Apartamento apartamento) {
		Visitante visitante = new Visitante();
		visitante.setDataVisita(new Date());
		visitante.setNome(nome);
		visitante.setApartamento(apartamento);

		this.visitanteService.save(visitante);

		return visitante;
	}

	public Visitante criarVisitante(String nome) {

		Apartamento apartamento = this.criaApartamento("Apartamento 210", (long) 210);

		Visitante visitante = new Visitante();
		visitante.setDataVisita(new Date());
		visitante.setNome(nome);
		visitante.setApartamento(apartamento);

		this.visitanteService.save(visitante);

		return visitante;
	}

	public Veiculo criaVeiculo(String marca, String modelo, String placa, String Renavan, String cor, Morador morador) {
		Veiculo veiculo = new Veiculo();
		veiculo.setMarca(marca);
		veiculo.setModelo(modelo);
		veiculo.setPlaca(placa);
		veiculo.setRenavan(Renavan);
		veiculo.setCor(cor);
		veiculo.setMorador(morador);

		this.veiculoService.save(veiculo);
		return veiculo;
	}

	public Veiculo criaVeiculo(String marca, String modelo, String placa, String Renavan, String cor) {

		Morador morador = this.criaMorador();

		Veiculo veiculo = new Veiculo();
		veiculo.setMarca(marca);
		veiculo.setModelo(modelo);
		veiculo.setPlaca(placa);
		veiculo.setRenavan(Renavan);
		veiculo.setCor(cor);
		veiculo.setMorador(morador);

		this.veiculoService.save(veiculo);
		return veiculo;
	}

	public Morador criaMorador(Apartamento apartamento, Pessoa pessoa) {
		Morador mr1 = new Morador();
		mr1.setApartamento(apartamento);
		mr1.setPessoa(pessoa);

		this.moradorService.save(mr1);
		return mr1;

	}

	public Morador criaMorador() {
		Pessoa p1 = this.criaPessoa("110.201.102-21", "junit@junit.com.br", "Morador JUNIT");
		Apartamento apartamento = this.criaApartamento("APT 210", (long) 210);

		Morador mr1 = new Morador();
		mr1.setApartamento(apartamento);
		mr1.setPessoa(p1);

		this.moradorService.save(mr1);
		return mr1;

	}

	public Pessoa criaPessoa(String cpf, String email, String nome) {
		Pessoa p1 = new Pessoa();
		p1.setCpf(cpf);
		p1.setEmail(email);
		p1.setNome(nome);

		this.pessoaService.save(p1);
		return p1;

	}

	public Apartamento criaApartamento(String nome, Long numero, Edificio edificio) {
		Apartamento ap1 = new Apartamento();
		ap1.setEdificio(edificio);
		ap1.setNumero(numero);
		ap1.setNome(nome);

		this.apartamentoService.save(ap1);

		return ap1;

	}

	public Apartamento criaApartamento(String nome, Long numero) {
		Edificio edificio = this.criaEdificioComBloco("Edificio Junit", "Edificio de Teste JUnit");

		Apartamento ap1 = new Apartamento();
		ap1.setEdificio(edificio);
		ap1.setNumero(numero);
		ap1.setNome(nome);

		this.apartamentoService.save(ap1);

		return ap1;

	}

	public Edificio criaEdificio(String nome, String descricao, Bloco bloco) {
		Edificio ed1 = new Edificio();
		ed1.setNome(nome);
		ed1.setDescricao(descricao);
		ed1.setBloco(bloco);

		this.edificioService.save(ed1);
		return ed1;
	}

	public Edificio criaEdificioComBloco(String nome, String descricao) {
		Bloco bloco = this.criaBlocoComCondominio("Bloco1");

		Edificio ed1 = new Edificio();
		ed1.setNome(nome);
		ed1.setDescricao(descricao);
		ed1.setBloco(bloco);

		this.edificioService.save(ed1);
		return ed1;
	}

	public Bloco criaBloco(String nome, Condominio condominio) {
		Bloco bloco1 = new Bloco();
		bloco1.setNome(nome);
		bloco1.setCondominio(condominio);

		this.blocoService.save(bloco1);
		return bloco1;
	}

	public Bloco criaBlocoComCondominio(String nome) {
		Condominio condominio = this.criaCondominio("TesteJunitCondo", "TesteJunit", "25", "RJ");
		Bloco bloco1 = new Bloco();
		bloco1.setNome(nome);
		bloco1.setCondominio(condominio);
		this.blocoService.save(bloco1);
		return bloco1;
	}

	public Area criaArea(String nome, String descricao, Condominio condominio) {
		Area area1 = new Area();
		area1.setNome(nome);
		area1.setDescricao(descricao);
		area1.setCondominio(condominio);

		this.areaService.save(area1);

		return area1;
	}

	public Area criaArea(String nome, String descricao) {
		Condominio condominio = this.criaCondominio("BeijaFlor", "Estrada JUNIT", "221", "RJ");
		Area area1 = new Area();
		area1.setNome(nome);
		area1.setDescricao(descricao);
		area1.setCondominio(condominio);

		this.areaService.save(area1);

		return area1;
	}

	public Condominio criaCondominio(String name, String logradouro, String numero, String uf) {
		Condominio condominio = new Condominio();
		condominio.setName(name);
		condominio.setLogradouro(logradouro);
		condominio.setNumero(numero);
		condominio.setUf(uf);

		this.condominioService.save(condominio);

		return condominio;

	}
	
	
	public Mural criaMuralCondominio(){
		
		Condominio condominio = this.criaCondominio("Condo. JUNIT", "Estrada Junit", "260", "RJ");
		
		Mensagem mensagem = this.criaMensagem("TESTE JUNIT", new Date());
		
		Set<Mensagem> mensagemLista = new HashSet<Mensagem>();
		mensagemLista.add(mensagem);
		
		Mural muralCondo = new Mural();
		Destino destinoCondominio = new Destino();
		destinoCondominio.setCondominio(condominio);
		muralCondo.setDestino(destinoCondominio);
		muralCondo.setMensagens(mensagemLista);
		
		this.muralService.save(muralCondo);
		
		return muralCondo;
	}
	
	
	public Mural criaMuralBloco(){
		
		Bloco bloco = this.criaBlocoComCondominio("BLOCO1");
		
		Mensagem mensagem = this.criaMensagem("TESTE JUNIT BLOCO", new Date());
		
		Set<Mensagem> mensagemLista = new HashSet<Mensagem>();
		mensagemLista.add(mensagem);
		
		Mural muralBloco = new Mural();
		Destino destinoBloco = new Destino();
		destinoBloco.setBloco(bloco);
		muralBloco.setDestino(destinoBloco);
		muralBloco.setMensagens(mensagemLista);
		
		this.muralService.save(muralBloco);
		
		return muralBloco;
	}
	
	public Mensagem criaMensagem(String mensagem, Date dataEnvio){
		
		Mensagem novaMensagem = new Mensagem();
		novaMensagem.setDataEnvio(new Date());
		novaMensagem.setMensagem(mensagem);
		
		return novaMensagem;
		
	}
}
