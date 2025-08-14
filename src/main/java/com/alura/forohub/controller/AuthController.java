package com.alura.forohub.controller;

import jakarta.validation.Valid;
import com.alura.forohub.dto.auth.DatosAuthRequest;
import com.alura.forohub.dto.auth.DatosAuthResponse;
import com.alura.forohub.dto.auth.DatosRegisterRequest;
import com.alura.forohub.model.Role;
import com.alura.forohub.model.Usuario;
import com.alura.forohub.repository.UsuarioRepository;
import com.alura.forohub.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtService jwt;
    private final PasswordEncoder encoder;
    private final UsuarioRepository repo;

    public AuthController(AuthenticationManager authManager, JwtService jwt, PasswordEncoder encoder, UsuarioRepository repo) {
        this.authManager = authManager;
        this.jwt = jwt;
        this.encoder = encoder;
        this.repo = repo;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid DatosRegisterRequest d) {
        if (repo.findByUsername(d.username()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Usuario u = new Usuario();
        u.setUsername(d.username());
        u.setPassword(encoder.encode(d.password()));
        u.setRoles(Set.of(Role.USER));
        repo.save(u);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<DatosAuthResponse> auth(@RequestBody @Valid DatosAuthRequest req) {
        Authentication a = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        String token = jwt.generateToken(a.getName());
        return ResponseEntity.ok(new DatosAuthResponse(token, "Bearer"));
    }

    @PostMapping("/login")
    public ResponseEntity<DatosAuthResponse> login(@RequestBody @Valid DatosAuthRequest req) {
        return auth(req);
    }
}