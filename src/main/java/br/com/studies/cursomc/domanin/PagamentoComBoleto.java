package br.com.studies.cursomc.domanin;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PagamentoComBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataVencimento;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataPagamento;

    @Builder(builderMethodName = "pagamentoComBoletoBuilder")
    public PagamentoComBoleto(Integer id, Integer estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
}
