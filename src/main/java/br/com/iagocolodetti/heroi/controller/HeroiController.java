package br.com.iagocolodetti.heroi.controller;

import br.com.iagocolodetti.heroi.error.CustomJsonError;
import br.com.iagocolodetti.heroi.model.Heroi;
import br.com.iagocolodetti.heroi.model.Usuario;
import br.com.iagocolodetti.heroi.repository.HeroiRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@Configuration
@RestController
@RequestMapping({"/herois"})
@Api(value = "Herói", tags = {"Herói"}, description = "REST API para Herói")
public class HeroiController {
    
    @Autowired
    private HeroiRepository repository;
    
    @ApiOperation(value = "Retorna uma lista de heróis")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de heróis"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAll(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(
                    repository.findAllByUsuario(Integer.parseInt((String) request.getAttribute("userid"))),
                    HttpStatus.OK);
        } catch (NumberFormatException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomJsonError(request, HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível buscar os heróis").toString());
        }
    }
    
    @ApiOperation(value = "Adiciona um herói")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Adiciona o herói"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> create(HttpServletRequest request, @RequestBody Heroi heroi) {
        try {
            Usuario usuario = new Usuario();
            usuario.setId(Integer.parseInt((String) request.getAttribute("userid")));
            heroi.setUsuario(usuario);
            repository.save(heroi);
            repository.refresh(heroi);
            return new ResponseEntity<>(heroi, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomJsonError(request, HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível cadastrar o herói").toString());
        }
    }
    
    @ApiOperation(value = "Exclui um herói")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Exclui o herói"),
        @ApiResponse(code = 404, message = "Herói não encontrado"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @DeleteMapping(path = {"/{heroi_id}"}, consumes = {MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> logicalDelete(HttpServletRequest request, @PathVariable Integer heroi_id) {
        try {
            return repository.findByUsuarioAndId(Integer.parseInt((String) request.getAttribute("userid")), heroi_id)
                    .map(record -> {
                        record.setAtivo(false);
                        repository.save(record);
                        return ResponseEntity.ok().build();
                    }).orElse(ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body(new CustomJsonError(request, HttpStatus.NOT_FOUND, "Herói não encontrado").toString())
                    );
        } catch (NumberFormatException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomJsonError(request, HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível excluir o herói").toString());
        }
    }
    
}
