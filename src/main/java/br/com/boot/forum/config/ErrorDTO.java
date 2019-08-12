package br.com.boot.forum.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDTO {

    private String campo;
    private String mensagem;

}
