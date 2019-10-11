package br.com.iagocolodetti.heroi.controller;

import br.com.iagocolodetti.heroi.model.Heroi;
import br.com.iagocolodetti.heroi.repository.HeroiRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author iagocolodetti
 */
@RestController
@RequestMapping({"/herois"})
public class HeroiController {
    
    private final HeroiRepository repository;

    HeroiController(HeroiRepository heroiRepository) {
        this.repository = heroiRepository;
    }
   
    @GetMapping
    public List findAll() {
        return repository.findAll();
    }

    @PostMapping
    public Heroi create(@RequestBody Heroi heroi) {
        return repository.save(heroi);
    }
    
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> logicalDelete(@PathVariable Integer id) {
        System.out.println(id);
        return repository.findById(id)
                .map(record -> {
                    record.setAtivo(false);
                    repository.save(record);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
