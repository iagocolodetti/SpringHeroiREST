package br.com.iagocolodetti.heroi.controller;

import br.com.iagocolodetti.heroi.repository.UniversoRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author iagocolodetti
 */
@RestController
@RequestMapping({"/universos"})
public class UniversoController {
    
    private final UniversoRepository repository;

    UniversoController(UniversoRepository universoRepository) {
        this.repository = universoRepository;
    }
   
    @GetMapping
    public List findAll() {
        return repository.findAll();
    }
}
