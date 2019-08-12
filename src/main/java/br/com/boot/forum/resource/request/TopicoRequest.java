package br.com.boot.forum.resource.request;

import br.com.boot.forum.modelo.Curso;
import br.com.boot.forum.modelo.Topico;
import br.com.boot.forum.repository.CursoRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class TopicoRequest {

    @NotNull @NotEmpty
    private String titulo;

    @NotNull @NotEmpty @Length(min = 5, max = 50)
    private String mensagem;

    @NotNull @NotEmpty
    private String nomeCurso;

    public Topico convert(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);

    }
}
