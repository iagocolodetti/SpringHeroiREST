package br.com.iagocolodetti.heroi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class Heroi implements Serializable {

    private static final long serialVersionUID = 2317478183460572016L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;
    
    @ApiModelProperty(value = "Nome do herói", example = "Nome")
    private String nome;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(hidden = true)
    private Date dataCadastro;
    @ApiModelProperty(hidden = true)
    private boolean ativo;
    @JoinColumn(name = "universo_id", referencedColumnName = "id")
    @OneToOne
    private Universo universo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "heroi", orphanRemoval = true)
    @JsonIgnoreProperties("heroi")
    private List<Poder> poderes;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Usuario usuario;
    
    @PrePersist
    private void prePersist() {
       setDataCadastro(new Date());
       setAtivo(true);
       poderes.forEach(poder -> poder.setHeroi(this));
    }
    
}
