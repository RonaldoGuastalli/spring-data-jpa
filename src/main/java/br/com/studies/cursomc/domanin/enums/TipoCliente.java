package br.com.studies.cursomc.domanin.enums;

import lombok.Getter;

@Getter
public enum TipoCliente {

    PESSOA_FISICA(1, "Pessoa Física"),
    PESSOA_JURIDICA(2, "Pessoa Jurídica");

    private int cod;
    private String descricao;

    TipoCliente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static TipoCliente toEnum(Integer cod){
        if(cod == null) return null;
        for (TipoCliente tipoCliente : TipoCliente.values()){
            if(cod.equals(tipoCliente.getCod()))
                return tipoCliente;
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
