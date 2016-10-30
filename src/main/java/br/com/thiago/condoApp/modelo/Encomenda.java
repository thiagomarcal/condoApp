package br.com.thiago.condoApp.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Encomenda implements Serializable{

private static final long serialVersionUID = 1L;

	public enum Situacao {
		Pendente, Entregue
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private Situacao situacao;
	
	@Column
	private String identificador;
	
	@Column
	private Date dataChegada;
	
	@Column
	private Date dataEntregue;
	
	@OneToOne
	@JoinColumn(name = "morador_id")
	private Morador morador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Date getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(Date dataChegada) {
		this.dataChegada = dataChegada;
	}

	public Date getDataEntregue() {
		return dataEntregue;
	}

	public void setDataEntregue(Date dataEntregue) {
		this.dataEntregue = dataEntregue;
	}

	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

}
