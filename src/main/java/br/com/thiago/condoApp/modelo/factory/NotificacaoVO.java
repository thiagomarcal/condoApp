package br.com.thiago.condoApp.modelo.factory;

import java.io.Serializable;

public class NotificacaoVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String descricao;
	private Long morador;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getMorador() {
		return morador;
	}
	public void setMorador(Long morador) {
		this.morador = morador;
	}
	
}
