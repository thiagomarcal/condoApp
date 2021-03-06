package br.com.thiago.condoApp.modelo;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Condominio implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String logradouro;
	
	@Column
	private String numero;
	
	@Column
	private String cidade;
	
	@Column
	private String uf;
	
	@Column
	private String cpnj;
	
	@Column
	private Date dataCriacao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "condominio", targetEntity = Bloco.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Bloco> blocos;
	
	@JsonIgnore
	@OneToMany(mappedBy = "condominio", targetEntity = Area.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Area> areas;
	
	@JsonIgnore
	@OneToMany(mappedBy = "condominio", targetEntity = Destino.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Destino> destinos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCpnj() {
		return cpnj;
	}

	public void setCpnj(String cpnj) {
		this.cpnj = cpnj;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Set<Bloco> getBlocos() {
		return blocos;
	}

	public void setBlocos(Set<Bloco> blocos) {
		this.blocos = blocos;
	}

	public Set<Area> getAreas() {
		return areas;
	}

	public void setAreas(Set<Area> areas) {
		this.areas = areas;
	}

	public Set<Destino> getDestinos() {
		return destinos;
	}

	public void setDestinos(Set<Destino> destinos) {
		this.destinos = destinos;
	}
	
}
