package br.com.thiago.condoApp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.thiago.condoApp.modelo.Morador;
import br.com.thiago.condoApp.modelo.Reserva;
import br.com.thiago.condoApp.modelo.Reserva.Situacao;

public interface ReservaRepository extends CrudRepository<Reserva, Long>{
	
	public List<Reserva> findBySituacao(Situacao situacao);
	public List<Reserva> findByMorador(Morador morador);
	
}
