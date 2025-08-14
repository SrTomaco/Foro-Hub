package com.alura.forohub.security;

import com.alura.forohub.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository repo;

    public UsuarioDetailsServiceImpl(UsuarioRepository repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username)
            .map(u -> User.withUsername(u.getUsername())
                          .password(u.getPassword())
                          .roles(u.getRoles().stream().map(Enum::name).toArray(String[]::new))
                          .build())
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
