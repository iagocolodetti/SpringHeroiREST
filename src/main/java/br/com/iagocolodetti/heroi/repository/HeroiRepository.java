package br.com.iagocolodetti.heroi.repository;

import br.com.iagocolodetti.heroi.model.Heroi;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author iagocolodetti
 */
@Repository
public interface HeroiRepository extends CustomRepository<Heroi, Integer> {
    
    @Query("SELECT h FROM Heroi h WHERE h.ativo = '1' AND h.usuario.id = ?1")
    List<Heroi> findAllByUsuario(Integer usuario_id);
    
    @Query("SELECT h FROM Heroi h WHERE h.ativo = '1' AND h.usuario.id = ?1 AND h.id = ?2")
    Optional<Heroi> findByUsuarioAndId(Integer usuario_id, Integer id);
    
}
