-- Unicidad (titulo, curso) en topicos
USE foro_hub;
ALTER TABLE topicos
ADD CONSTRAINT uk_topico_titulo_curso UNIQUE (titulo, curso);