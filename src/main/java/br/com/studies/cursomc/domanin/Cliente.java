package br.com.studies.cursomc.domanin;

import br.com.studies.cursomc.domanin.enums.TipoCliente;
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
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    // Salvar um numero no BD (getCod()), não uma stirng.
    private Integer tipo;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Endereco> enderecos = new ArrayList<>();

    /*
    * Mapeamento de telefone, porem sem que a entidade telefone exista.
    * A tabela TELEFON será criada, relacionando o id do cliente e o número(string) do telefone.
    * */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TELEFONE")
    @Builder.Default
    private Set<String> telefones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    public TipoCliente getTipo(){
        return TipoCliente.toEnum(tipo);
    }

    public void setTipo(TipoCliente tipo){
        this.tipo = tipo.getCod();
    }


}
