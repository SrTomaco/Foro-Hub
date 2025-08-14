package com.alura.forohub.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupLogger {
    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        System.out.println("");
        System.out.println("***************************************************************************************************************");
        System.out.println("\n  Foro Hub listo: Swagger en http://localhost:8080/swagger-ui.html  |  H2 en http://localhost:8080/h2-console\n");
        System.out.println("***************************************************************************************************************");
    }
}
