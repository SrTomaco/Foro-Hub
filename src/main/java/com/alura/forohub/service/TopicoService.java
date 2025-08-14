package com.alura.forohub.service;

import com.alura.forohub.dto.topico.*;
import com.alura.forohub.model.Topico;
import com.alura.forohub.repository.TopicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicoService {
    private final TopicoRepository repo;
    public TopicoService(TopicoRepository repo) { this.repo = repo; }

    private DatosRespuestaTopico map(Topico t) {
        return new DatosRespuestaTopico(
            t.getId(), t.getTitulo(), t.getMensaje(),
            t.getAutor(), t.getCurso(), t.getFechaCreacion(), t.getStatus()
        );
    }

    @Transactional
    public DatosRespuestaTopico crear(DatosRegistroTopico d) {
        if (repo.existsByTituloIgnoreCaseAndCursoIgnoreCase(d.titulo(), d.curso())) {
            throw new IllegalArgumentException("Ya existe un tópico con ese título para ese curso.");
        }
        Topico t = new Topico();
        t.setTitulo(d.titulo());
        t.setMensaje(d.mensaje());
        t.setAutor(d.autor());
        t.setCurso(d.curso());
        repo.save(t);
        return map(t);
    }

    @Transactional(readOnly = true)
    public Page<DatosRespuestaTopico> listar(Pageable pageable) {
        return repo.findAll(pageable).map(this::map);
    }

    @Transactional(readOnly = true)
    public DatosRespuestaTopico obtener(Long id) {
        Topico t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));
        return map(t);
    }

    @Transactional
    public DatosRespuestaTopico actualizar(Long id, DatosActualizarTopico d) {
        Topico t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));
        // También valida unicidad si cambian título/curso
        if ((!t.getTitulo().equalsIgnoreCase(d.titulo()) || !t.getCurso().equalsIgnoreCase(d.curso()))
            && repo.existsByTituloIgnoreCaseAndCursoIgnoreCase(d.titulo(), d.curso())) {
            throw new IllegalArgumentException("Ya existe un tópico con ese título para ese curso.");
        }
        t.setTitulo(d.titulo());
        t.setMensaje(d.mensaje());
        t.setAutor(d.autor());
        t.setCurso(d.curso());
        repo.save(t);
        return map(t);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Tópico no encontrado");
        repo.deleteById(id);
    }
}