package br.com.studies.cursomc.domanin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private Double preco;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA",
                joinColumns = @JoinColumn(name = "produto_id"),
                inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    @Builder.Default
    private List<Categoria> categorias = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.produto")
    @Builder.Default
    private Set<ItemPedido> itens = new HashSet<>();

    // Listar os pedidos do produto
    @JsonIgnore
    public List<Pedido> getPedidos(){
        List<Pedido> pedidos = new ArrayList<>();
        for(ItemPedido itemPedido : itens){
            pedidos.add(itemPedido.getPedido());
        }
        //itens.stream().forEach(itemPedido -> pedidos.add(itemPedido.getPedido()));
        return pedidos;
    }

}
