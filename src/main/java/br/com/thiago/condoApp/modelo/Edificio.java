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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Edificio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "bloco_id")
//	@JsonIgnore
	private Bloco bloco;
	
	@JsonIgnore
	@OneToMany(mappedBy = "edificio", targetEntity = Apartamento.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Apartamento> apartamentos;
	
	@OneToMany(mappedBy = "edificio", targetEntity = MuralEdificio.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<MuralEdificio> murais;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Bloco getBloco() {
		return bloco;
	}

	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}

	public Set<Apartamento> getApartamentos() {
		return apartamentos;
	}

	public void setApartamentos(Set<Apartamento> apartamentos) {
		this.apartamentos = apartamentos;
	}

	public Set<MuralEdificio> getMurais() {
		return murais;
	}

	public void setMurais(Set<MuralEdificio> murais) {
		this.murais = murais;
	}

}
