package br.com.thiago.condoApp.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Mensagem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String mensagem;
	
	@Column
	private Date dataEnvio;
	
	@Lob
	@Column(name="pic")
    private byte[] picture;
	
	@OneToOne
	@JoinColumn(name = "muralBloco_id")
	@JsonIgnore
	private MuralBloco muralBloco;
	
	
	@OneToOne
	@JoinColumn(name = "muralCondominio_id")
	@JsonIgnore
	private MuralCondominio muralCondominio;
	
	
	@OneToOne
	@JoinColumn(name = "muralEdificio_id")
	@JsonIgnore
	private MuralEdificio muralEdificio;


}
