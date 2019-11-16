package br.com.iagocolodetti.heroi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author iagocolodetti
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = -6277534669996010157L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;
    
    @ApiModelProperty(value = "Nome de usuário", example = "Nome")
    private String nome;
    private String senha;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(hidden = true)
    private Date dataCadastro;
    
    @JsonIgnore
    public String getSenha() {
        return senha;
    }
    
    @JsonProperty
    @ApiModelProperty(value = "Senha de acesso", example = "MinhaSenha123")
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @PrePersist
    private void prePersist() {
       setDataCadastro(new Date());
    }
    
}