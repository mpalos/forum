package br.com.boot.forum.modelo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class Resposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensagem;

	@ManyToOne
	private Topico topico;
	private LocalDateTime dataCriacao = LocalDateTime.now();

	@ManyToOne
	private Usuario autor;
	private Boolean solucao = false;

}
