  ForoHub 

**ForoHub** es una API REST de tópicos con **Spring Boot 3**, **JWT**, **Swagger UI** y **H2** en memoria para desarrollo.

## Demo en IntelliJ IDEA
1. Abrí el proyecto (`pom.xml`) → *Open as Project*.
2. JDK **17** y Maven instalados.
3. Ejecutá `com.alura.forohub.ForoHubApplication`.

**URLs clave**
- Swagger UI: `http://localhost:8080/swagger-ui.html` (branding personalizado “ForoHub”, modo oscuro, neon suave).
- H2 Console (opcional): `http://localhost:8080/h2-console` — JDBC: `jdbc:h2:mem:foro_hub` (user: `sa`, pass vacío).

## Autenticación
- `POST /auth/register` — `{ "username": "user", "password": "pass" }`
- `POST /auth/login` — devuelve `{ "token": "..." }`
- Usá **Authorize** en Swagger (pegá `Bearer <token>`).

## CRUD de Tópicos
- `GET /topicos` — lista
- `POST /topicos` — crea
- `GET /topicos/{id}` — detalle
- `PUT /topicos/{id}` — actualiza
- `DELETE /topicos/{id}` — elimina

## UX/UI y estilo
- **Swagger UI** con tema oscuro, paneles redondeados, botones con gradiente y neon suave.
- **Persist Authorization** activo, **Try it out** habilitado, búsqueda (filter) y expansión cómoda.
- Validación con **Jakarta Validation** y manejo de errores consistente (400 con detalle de campos).

## Perfiles
- Desarrollo por defecto con H2 en memoria. Podés configurar MySQL con Flyway si lo necesitás.

---
Hecho por **Tomás Solá** para el desafío de literatura del curso de **Alura Latam con Oracle**.

## Mejoras realizadas (agosto 2025)
- **Estado del tópico (`status`)** agregado (ABIERTO, CERRADO, RESUELTO).
- **Listados paginados** en `GET /topicos` (`page`, `size`, `sort`).
- **Validación de unicidad** de `titulo+curso` (servicio + restricción en BD).
- **Flyway** actualizado (V1 y V2) para que MySQL coincida con las entidades reales (`usuarios`, `topicos`).
- **Autenticación**: alias `POST /auth` (igual a `/auth/login`) y respuesta `{ token, type: "Bearer" }`. El login acepta `username`/`password` o `email`/`contrasena|contraseña`.
- **Swagger UI** listo en `/swagger-ui.html` (endpoints documentados).

### Ejemplos en Insomnia
**Login**
```http
POST http://localhost:8080/auth
Content-Type: application/json

{
  "username": "juan",
  "password": "juan123"
}
```
**Respuesta**
```json
{ "token": "eyJ...", "type": "Bearer" }
```

**Crear tópico**
```http
POST http://localhost:8080/topicos
Authorization: Bearer <token>
Content-Type: application/json

{
  "titulo": "Dudas Java",
  "mensaje": "¿Cómo configuro Spring?",
  "autor": "Juan",
  "curso": "Java"
}
```

**Listar paginado**
```http
GET http://localhost:8080/topicos?page=0&size=10&sort=fechaCreacion,desc
Authorization: Bearer <token>
```
