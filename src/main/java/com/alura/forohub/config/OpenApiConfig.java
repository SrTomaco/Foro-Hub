package com.alura.forohub.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "ForoHub",
        version = "1.0.0",
        description = "API REST para gestionar tópicos del foro. Demo con JWT + Swagger UI personalizada.",
        contact = @Contact(name = "ForoHub", email = "tomassola98@gmail.com"),
        license = @License(name = "Hecho por Tomás Solá para el curso de Alura Latam & Oracle")
    )
)
public class OpenApiConfig {}
