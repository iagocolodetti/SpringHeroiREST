package br.com.iagocolodetti.heroi.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Poder implements Serializable {

    private static final long serialVersionUID = -494770409437666040L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;
    
    @ApiModelProperty(value = "Nome do poder que o herói possui", example = "Poder")
    private String descricao;
    @JoinColumn(name = "heroi_id", referencedColumnName = "id")
    @ManyToOne
    private Heroi heroi;
    
}
