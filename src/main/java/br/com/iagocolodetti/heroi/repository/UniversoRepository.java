package br.com.iagocolodetti.heroi.repository;

import br.com.iagocolodetti.heroi.model.Universo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author iagocolodetti
 */
public interface UniversoRepository extends JpaRepository<Universo, Integer> {
    
}
