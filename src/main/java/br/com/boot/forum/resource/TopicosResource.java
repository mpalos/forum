package br.com.boot.forum.resource;

import br.com.boot.forum.dto.TopicoDTO;
import br.com.boot.forum.dto.TopicoDetalhesDTO;
import br.com.boot.forum.repository.CursoRepository;
import br.com.boot.forum.resource.request.AtualizacaoTopicoRequest;
import br.com.boot.forum.resource.request.TopicoRequest;
import br.com.boot.forum.modelo.Topico;
import br.com.boot.forum.repository.TopicoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

// TODO Implementar o 404 no cadastro, atualização e remoção

@Slf4j
@RestController
@RequestMapping("/topicos")
public class TopicosResource {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDTO> lista(@RequestParam(required = false) String nomeCurso){
        log.info("Param: {}", nomeCurso);

        List<Topico> topicos;

        if(nomeCurso == null){
            topicos = topicoRepository.findAll();
        } else{
            topicos = topicoRepository.findByCursoNome(nomeCurso);
        }

        return TopicoDTO.convert(topicos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoRequest topicoRequest, UriComponentsBuilder uriBuilder){
        Topico topico = topicoRequest.convert(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));

    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalhesDTO> detalhar(@PathVariable("id") Long idTopico){
        Optional<Topico> topico = topicoRepository.findById(idTopico);

        if(!topico.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new TopicoDetalhesDTO(topico.get()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id,
                                               @RequestBody @Valid AtualizacaoTopicoRequest topicoRequest){
        Topico topico = topicoRequest.atualizar(id, topicoRepository);

        return ResponseEntity.ok(new TopicoDTO(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remove(@PathVariable Long id){
        topicoRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
