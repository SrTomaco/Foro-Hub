package com.alura.forohub.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Table(name = "topicos",
       uniqueConstraints = @UniqueConstraint(name = "uk_topico_titulo_curso", columnNames = {"titulo","curso"}))
@Entity
public class Topico {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String titulo;
  @Column(length = 4000, nullable = false)
  private String mensaje;
  @Column(nullable = false)
  private String autor;
  @Column(nullable = false)
  private String curso;
  @Column(nullable = false)
  private LocalDateTime fechaCreacion = LocalDateTime.now();
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private EstadoTopico status = EstadoTopico.ABIERTO;

  public Long getId() { return id; }
  public String getTitulo() { return titulo; }
  public void setTitulo(String titulo) { this.titulo = titulo; }
  public String getMensaje() { return mensaje; }
  public void setMensaje(String mensaje) { this.mensaje = mensaje; }
  public String getAutor() { return autor; }
  public void setAutor(String autor) { this.autor = autor; }
  public String getCurso() { return curso; }
  public void setCurso(String curso) { this.curso = curso; }
  public LocalDateTime getFechaCreacion() { return fechaCreacion; }
  public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
  public EstadoTopico getStatus() { return status; }
  public void setStatus(EstadoTopico status) { this.status = status; }
}