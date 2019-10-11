package br.com.iagocolodetti.heroi.repository;

import br.com.iagocolodetti.heroi.model.Heroi;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author iagocolodetti
 */
@Repository
public interface HeroiRepository extends JpaRepository<Heroi, Integer> {
    
    @Query("SELECT h FROM Heroi h WHERE h.ativo = '1'")
    @Override
    List<Heroi> findAll();
    
    @Query("SELECT h FROM Heroi h WHERE h.ativo = '1' AND h.id = ?1")
    @Override
    Optional<Heroi> findById(Integer id);
}
