package br.com.studies.cursomc.domanin;

import br.com.studies.cursomc.domanin.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public abstract class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id // Tem que ter o mesmo id do pedido correspondente
    private Integer id;
    private Integer estado;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId // Mesmo id do pedido
    private Pedido pedido;

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getCod();
    }

    public EstadoPagamento getEstado(){
        return EstadoPagamento.toEnum(estado);
    }
}
