package com.alura.forohub.controller;

import jakarta.validation.Valid;
import com.alura.forohub.dto.topico.*;
import com.alura.forohub.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/topicos")
@Tag(name = "Tópicos", description = "Operaciones CRUD de tópicos")
public class TopicoController {
  private final TopicoService service;
  public TopicoController(TopicoService service) { this.service = service; }

  @PostMapping
  @Operation(summary = "Crear un nuevo tópico")
  public ResponseEntity<DatosRespuestaTopico> crear(@RequestBody @Valid DatosRegistroTopico d) {
    DatosRespuestaTopico r = service.crear(d);
    return ResponseEntity.created(URI.create("/topicos/" + r.id())).body(r);
  }

  @GetMapping
  @Operation(summary = "Listar tópicos paginados")
  public ResponseEntity<Page<DatosRespuestaTopico>> listar(Pageable pageable) {
    return ResponseEntity.ok(service.listar(pageable));
  }

  @GetMapping("/{id}")
  @Operation(summary = "Obtener un tópico por id")
  public ResponseEntity<DatosRespuestaTopico> obtener(@PathVariable Long id) {
    return ResponseEntity.ok(service.obtener(id));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Actualizar un tópico por id")
  public ResponseEntity<DatosRespuestaTopico> actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico d) {
    return ResponseEntity.ok(service.actualizar(id, d));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Eliminar un tópico por id")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    service.eliminar(id);
    return ResponseEntity.noContent().build();
  }
}