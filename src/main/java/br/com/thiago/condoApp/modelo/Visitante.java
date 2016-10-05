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
public class Visitante implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private Date dataVisita;
	
	
	@OneToOne
	@JoinColumn(name = "apartamento_id")
	private Apartamento apartamento;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getDataVisita() {
		return dataVisita;
	}


	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}


	public Apartamento getApartamento() {
		return apartamento;
	}


	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}
	
	

}
