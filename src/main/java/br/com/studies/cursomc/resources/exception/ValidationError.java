package br.com.studies.cursomc.resources.exception;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class ValidationError extends StandardError {
    private static final long serialVersionUID = 1L;

    @Builder.Default
    private List<FieldMessage> erros = new ArrayList<>();

    @Builder
    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public void addError(String filedName, String messagem){
        erros.add(new FieldMessage(filedName, messagem));
    }


}
