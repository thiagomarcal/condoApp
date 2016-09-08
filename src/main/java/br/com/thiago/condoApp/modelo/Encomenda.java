package br.com.thiago.condoApp.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Encomenda implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String tipoEncomenda;
	
	@OneToOne
	@JoinColumn(name = "apartamento_id")
	@JsonIgnore
	private Apartamento apartamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoEncomenda() {
		return tipoEncomenda;
	}

	public void setTipoEncomenda(String tipoEncomenda) {
		this.tipoEncomenda = tipoEncomenda;
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}
	
	
	
}
