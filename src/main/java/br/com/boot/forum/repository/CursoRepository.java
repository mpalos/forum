package br.com.boot.forum.repository;

import br.com.boot.forum.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long > {
    Curso findByNome(String nome);
}
