package br.com.iagocolodetti.heroi.controller;

import br.com.iagocolodetti.heroi.error.CustomJsonError;
import br.com.iagocolodetti.heroi.repository.UniversoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author iagocolodetti
 */
@RestController
@RequestMapping({"/universos"})
@Api(value = "Universo", tags = {"Universo"}, description = "REST API para Universo")
public class UniversoController {
    
    @Autowired
    private UniversoRepository repository;
    
    @ApiOperation(value = "Retorna uma lista de universos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna a lista de universos"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAll(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomJsonError(request, HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível buscar os universos").toString());
        }
    }
    
}
