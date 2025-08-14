package com.alura.forohub.config;

import com.alura.forohub.model.Topico;
import com.alura.forohub.model.Usuario;
import com.alura.forohub.model.Role;
import com.alura.forohub.repository.TopicoRepository;
import com.alura.forohub.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(TopicoRepository topicos, UsuarioRepository usuarios, PasswordEncoder encoder) {
        return args -> {
            if (usuarios.count() == 0) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("adminpass"));
                admin.setRoles(Set.of(Role.ADMIN, Role.USER));
                usuarios.save(admin);

                Usuario juan = new Usuario();
                juan.setUsername("juan");
                juan.setPassword(encoder.encode("juan123"));
                juan.setRoles(Set.of(Role.USER));
                usuarios.save(juan);
            }
            if (topicos.count() == 0) {
                topicos.saveAll(List.of(
                    mk("Bienvenida", "Este es el primer post del foro", "Admin", "General"),
                    mk("Reglas", "Sé amable y respeta a los demás", "Moderación", "Normas")
                ));
            }
        };
    }

    private Topico mk(String t, String m, String a, String c) {
        Topico x = new Topico();
        x.setTitulo(t); x.setMensaje(m); x.setAutor(a); x.setCurso(c);
        return x;
    }
}