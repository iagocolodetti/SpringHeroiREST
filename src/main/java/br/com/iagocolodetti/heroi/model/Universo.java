package br.com.iagocolodetti.heroi.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Universo implements Serializable {

    private static final long serialVersionUID = -7513921556457693741L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID do universo ao qual o herói pertence", example = "1")
    private Integer id;
    
    @ApiModelProperty(hidden = true)
    private String nome;
    
}
