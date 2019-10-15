package br.com.iagocolodetti.heroi.controller;

import br.com.iagocolodetti.heroi.repository.UniversoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    
    @ApiOperation(value = "Retorna uma lista de universos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de universos"),
        @ApiResponse(code = 401, message = "Não autorizado"),
        @ApiResponse(code = 403, message = "Proibido"),
        @ApiResponse(code = 404, message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Erro interno")
    })
    @GetMapping
    public List findAll() {
        return repository.findAll();
    }
}
