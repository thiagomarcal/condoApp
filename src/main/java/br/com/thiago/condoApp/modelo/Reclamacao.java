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

@Entity
public class Reclamacao implements Serializable{

	private static final long serialVersionUID = 1L;

	public enum Situacao {
		Pendente, Atendida
	}
	
	public enum Tipo {
		Area, Apartamento, Outros 
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String assunto;
	
	@Column
	private String descricao;
	
	@Column
	private Date dataEnvio;
	
	@Column
	private Date dataAtendimento;
	
	@Column
	private Situacao situacao;
	
	@Column
	private Tipo tipo;
	
	@Lob
	@Column(name="pic")
    private byte[] picture;

	@OneToOne
	@JoinColumn(name = "area_id")
	private Area area;
	
	@OneToOne
	@JoinColumn(name = "apartamento_id")
	private Apartamento apartamento;
	
	@OneToOne
	@JoinColumn(name = "morador_id")
	private Morador morador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

}
