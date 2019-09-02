package br.com.studies.cursomc.domanin.enums;

import lombok.Getter;

@Getter
public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private int cod;
    private String descricao;

    EstadoPagamento(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static EstadoPagamento toEnum(Integer cod){
        if(cod == null) return null;
        for (EstadoPagamento tipoCliente : EstadoPagamento.values()){
            if(cod.equals(tipoCliente.getCod()))
                return tipoCliente;
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
