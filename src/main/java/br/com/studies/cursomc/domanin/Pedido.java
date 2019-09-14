package br.com.studies.cursomc.domanin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date instante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega_id")
    private Endereco enderecoDeEntrega;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido", fetch = FetchType.EAGER)
    private Pagamento pagamento;

    @OneToMany(mappedBy = "id.pedido", fetch = FetchType.EAGER)
    @Builder.Default
    private Set<ItemPedido> itens = new HashSet<>();

    public List<Produto> getProdutos(){
        List<Produto> produtos = new ArrayList<>();
        for (ItemPedido itemPedido : itens){
            produtos.add(itemPedido.getProduto());
        }
        return produtos;
    }

}
