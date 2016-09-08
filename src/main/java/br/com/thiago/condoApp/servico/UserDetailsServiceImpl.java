package br.com.thiago.condoApp.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.thiago.condoApp.modelo.User;
import br.com.thiago.condoApp.modelo.factory.CondoUserFactory;
import br.com.thiago.condoApp.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(
					String.format("Nenhum usuário encontrado com username '%s'.", username));
		} else {
			return CondoUserFactory.create(user);
		}

	}

}
