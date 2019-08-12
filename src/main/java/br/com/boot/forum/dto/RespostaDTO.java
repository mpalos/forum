package br.com.boot.forum.dto;

import br.com.boot.forum.modelo.Resposta;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RespostaDTO {

    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public RespostaDTO(Resposta resposta){
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataCriacao();
        this.nomeAutor = resposta.getAutor().getNome();
    }
}
