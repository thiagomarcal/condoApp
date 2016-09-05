package br.com.thiago.condoApp.servico;

import java.util.List;

import br.com.thiago.condoApp.modelo.Reserva;
import br.com.thiago.condoApp.modelo.Reserva.Situacao;

public interface ReservaService {
	
	public List<Reserva> findAll();
	public void save(Reserva reserva);
	public Reserva findOne(Long id);
	public void delete(Long id);
	public List<Reserva> findBySituacao(Situacao situacao);

}
