package br.com.iagocolodetti.heroi.repository;

import br.com.iagocolodetti.heroi.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author iagocolodetti
 */
@Repository
public interface UsuarioRepository extends CustomRepository<Usuario, Integer> {
    
    @Query("SELECT u FROM Usuario u WHERE u.nome = ?1")
    Usuario findByNome(String nome);
    
}
