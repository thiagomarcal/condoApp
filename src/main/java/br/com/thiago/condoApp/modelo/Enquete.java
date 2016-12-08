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
public class Enquete implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String descricao;
	
	@Column
	private String pergunta;
	
	@OneToMany(mappedBy = "enquete", targetEntity = Alternativa.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Alternativa> alternativas;

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

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public Set<Alternativa> getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(Set<Alternativa> alternativas) {
		this.alternativas = alternativas;
	}
	
}
