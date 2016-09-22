package br.com.thiago.condoApp.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Encomenda implements Serializable{

private static final long serialVersionUID = 1L;

	public enum Tipo {
		CORREIOS, EMPRESA, FOOD
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private Tipo tipo;
	
	@Column
	private String nome;
	
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}
	
	
	
	
	
}
