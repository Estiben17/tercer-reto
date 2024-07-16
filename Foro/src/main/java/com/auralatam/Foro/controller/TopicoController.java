package com.auralatam.Foro.controller;

import com.auralatam.Foro.Dto.TopicoDTO;
import com.auralatam.Foro.entidades.Topico;
import com.auralatam.Foro.entidades.Usuario;
import com.auralatam.Foro.entidades.Curso;
import com.auralatam.Foro.repository.TopicoRepository;
import com.auralatam.Foro.repository.UsuarioRepository;
import com.auralatam.Foro.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Topico> registrarTopico(@Valid @RequestBody TopicoDTO topicoDTO) {
        Usuario autor = usuarioRepository.findById(topicoDTO.getAutorId()).orElseThrow(() -> new IllegalArgumentException("Invalid author ID"));
        Curso curso = cursoRepository.findById(topicoDTO.getCursoId()).orElseThrow(() -> new IllegalArgumentException("Invalid course ID"));

        Topico topico = new Topico();
        topico.setTitulo(topicoDTO.getTitulo());
        topico.setMensaje(topicoDTO.getMensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setStatus("ACTIVE");

        topicoRepository.save(topico);
        return new ResponseEntity<>(topico, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Topico>> listarTopicos() {
        List<Topico> topicos = topicoRepository.findAll();
        return new ResponseEntity<>(topicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerTopicoPorId(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid topic ID"));
        return new ResponseEntity<>(topico, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @Valid @RequestBody TopicoDTO topicoDTO) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid topic ID"));
        Usuario autor = usuarioRepository.findById(topicoDTO.getAutorId()).orElseThrow(() -> new IllegalArgumentException("Invalid author ID"));
        Curso curso = cursoRepository.findById(topicoDTO.getCursoId()).orElseThrow(() -> new IllegalArgumentException("Invalid course ID"));

        topico.setTitulo(topicoDTO.getTitulo());
        topico.setMensaje(topicoDTO.getMensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        topicoRepository.save(topico);
        return new ResponseEntity<>(topico, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid topic ID"));
        topicoRepository.delete(topico);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
