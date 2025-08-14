package com.alura.forohub.dto.auth;

import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAuthRequest(
    @NotBlank @JsonAlias({ "username", "email" }) String username,
    @NotBlank @JsonAlias({ "password", "contrasena", "contraseña" }) String password
) {}