package com.alura.forohub.dto.topico;

import java.time.LocalDateTime;
import com.alura.forohub.model.EstadoTopico;

public record DatosRespuestaTopico(
    Long id,
    String titulo,
    String mensaje,
    String autor,
    String curso,
    LocalDateTime fechaCreacion,
    EstadoTopico status
) {}