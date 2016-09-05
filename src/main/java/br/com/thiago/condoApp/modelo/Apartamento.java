package br.com.thiago.condoApp.modelo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Apartamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private Long numero;
	
	@Column
	private String nome;
	
	@OneToOne
	@JoinColumn(name = "edificio_id")
	@JsonIgnore
	private Edificio edificio;
	
	@OneToMany(mappedBy = "apartamento", targetEntity = Morador.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Morador> moradores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public Set<Morador> getMoradores() {
		return moradores;
	}

	public void setMoradores(Set<Morador> moradores) {
		this.moradores = moradores;
	}

	

}
