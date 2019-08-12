package br.com.boot.forum.dto;

import br.com.boot.forum.modelo.Topico;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TopicoDTO {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    public TopicoDTO (Topico topico){
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }

    public static List<TopicoDTO> convert(List<Topico> topicos) {
        return topicos.stream().map(TopicoDTO::new).collect(Collectors.toList());
    }
}
