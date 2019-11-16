package br.com.iagocolodetti.heroi.controller;

import br.com.iagocolodetti.heroi.error.CustomJsonError;
import br.com.iagocolodetti.heroi.model.Usuario;
import br.com.iagocolodetti.heroi.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author iagocolodetti
 */
@RestController
@RequestMapping({"/usuarios"})
@Api(value = "Usuário", tags = {"Usuário"}, description = "REST API para Usuário")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository repository;
    
    @ApiOperation(value = "Cria um usuário")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cria o usuário"),
        @ApiResponse(code = 409, message = "Usuário já existe"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> create(HttpServletRequest request, @RequestBody Usuario usuario) {
        try {
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            return new ResponseEntity<>(repository.save(usuario), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new CustomJsonError(request, HttpStatus.CONFLICT, "Usuário já existe").toString());
        }
    }
    
    @ApiOperation(value = "Retorna um token de autorização no cabeçalho")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna o token de autorização no cabeçalho"),
        @ApiResponse(code = 404, message = "Usuário não encontrado ou senha incorreta"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping(path = {"/login"}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        return null;
    }
    
}
