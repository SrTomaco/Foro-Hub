package com.alura.forohub.dto.auth;

import jakarta.validation.constraints.NotBlank;


public record DatosRegisterRequest(@NotBlank String username, @NotBlank String password) {}

