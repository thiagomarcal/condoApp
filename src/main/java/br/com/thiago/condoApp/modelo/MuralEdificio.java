package br.com.thiago.condoApp.modelo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MuralEdificio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(mappedBy = "mural", targetEntity = Mensagem.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Mensagem> mensagens;
	
	@ManyToOne
	@JoinColumn(name = "edificio_id")
	@JsonIgnore
	private Edificio edificio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(Set<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}
}
