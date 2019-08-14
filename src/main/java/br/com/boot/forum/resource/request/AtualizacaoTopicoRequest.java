package br.com.boot.forum.resource.request;

import br.com.boot.forum.modelo.Topico;
import br.com.boot.forum.repository.TopicoRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class AtualizacaoTopicoRequest {

    @NotNull
    @NotEmpty
    private String titulo;

    @NotNull @NotEmpty @Length(min = 5, max = 50)
    private String mensagem;

    public Topico atualizar(Long id, TopicoRepository topicoRepository) {
        Topico topico = topicoRepository.getOne(id);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;
    }
}
