package br.com.thiago.condoApp.modelo;

import java.io.Serializable;
import java.util.List;

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
	
	public Condominio(String nome) {
		this.nome = nome;
	}
	
	@Id
	@Column
	@GeneratedValue
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String endereco;
	
	@OneToMany(mappedBy = "condominio", targetEntity = Bloco.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Bloco> blocos;

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Bloco> getBlocos() {
		return blocos;
	}

	public void setBlocos(List<Bloco> blocos) {
		this.blocos = blocos;
	}


}
