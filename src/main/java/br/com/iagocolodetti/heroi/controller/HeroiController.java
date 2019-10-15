package br.com.iagocolodetti.heroi.controller;

import br.com.iagocolodetti.heroi.model.Heroi;
import br.com.iagocolodetti.heroi.repository.HeroiRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    
    @ApiOperation(value = "Retorna uma lista de heróis")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de heróis"),
        @ApiResponse(code = 401, message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido"),
        @ApiResponse(code = 404, message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping
    public List findAll() {
        return repository.findAll();
    }
    
    @ApiOperation(value = "Adiciona um herói")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Adiciona o herói"),
        @ApiResponse(code = 201, message = "Criado"),
        @ApiResponse(code = 401, message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido"),
        @ApiResponse(code = 404, message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Erro interno")
    })
    @PostMapping
    public Heroi create(@RequestBody Heroi heroi) {
        return repository.save(heroi);
    }
    
    @ApiOperation(value = "Exclui um herói")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Exclui o herói"),
        @ApiResponse(code = 204, message = "Sem conteúdo"),
        @ApiResponse(code = 401, message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido"),
        @ApiResponse(code = 404, message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Erro interno")
    })
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
