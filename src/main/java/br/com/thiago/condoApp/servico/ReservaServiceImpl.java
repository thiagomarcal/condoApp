package br.com.thiago.condoApp.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.thiago.condoApp.modelo.Morador;
import br.com.thiago.condoApp.modelo.Reserva;
import br.com.thiago.condoApp.modelo.Reserva.Situacao;
import br.com.thiago.condoApp.repository.ReservaRepository;

@Repository
public class ReservaServiceImpl implements ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Override
	public List<Reserva> findAll() {
		return (List<Reserva>) reservaRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Reserva reserva) {
		reservaRepository.save(reserva);
	}

	@Override
	public Reserva findOne(Long id) {
		return reservaRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		reservaRepository.delete(id);
	}

	@Override
	public List<Reserva> findBySituacao(Situacao situacao) {
		return reservaRepository.findBySituacao(situacao);
	}
	
	@Override
	public List<Reserva> findByMorador(Morador morador) {
		return reservaRepository.findByMorador(morador);
	}

}
