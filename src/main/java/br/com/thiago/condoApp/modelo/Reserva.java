package br.com.thiago.condoApp.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public enum Situacao {
		APROVADO, EM_ANDAMENTO, REJEITADO
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private Date dataInicio;
	
	@Column
	private Date dataFim;
	
	@Column
	private Situacao situacao;
	
	@OneToOne
	@JoinColumn(name = "area_id")
	private Area area;
	
	@OneToOne
	@JoinColumn(name = "morador_id")
	private Morador morador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	
}
