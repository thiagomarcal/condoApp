package br.com.thiago.condoApp.modelo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	private String endereco;
	
	@OneToMany(mappedBy = "condominio", targetEntity = Bloco.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Bloco> blocos;
	
	@OneToMany(mappedBy = "condominio", targetEntity = Area.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Area> areas;

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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
}
