package br.com.iagocolodetti.heroi.security;

import br.com.iagocolodetti.heroi.model.Usuario;
import br.com.iagocolodetti.heroi.repository.UsuarioRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author iagocolodetti
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByNome(username);
        if (usuario != null) {
            return new CustomUser(usuario.getId(), usuario.getNome(), usuario.getSenha(), Collections.emptyList());
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }
    
}
