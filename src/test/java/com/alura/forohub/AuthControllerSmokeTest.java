package com.alura.forohub;

import com.alura.forohub.controller.AuthController;
import com.alura.forohub.dto.auth.DatosRegisterRequest;
import com.alura.forohub.repository.UsuarioRepository;
import com.alura.forohub.security.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
class AuthControllerSmokeTest {
  @Autowired private MockMvc mvc;
  @MockBean private UsuarioRepository repo;
  @MockBean private PasswordEncoder encoder;
  @MockBean private AuthenticationManager am;
  @MockBean private JwtService jwt;
  @Test void register_returnsOk() throws Exception {
    when(repo.existsByUsername(anyString())).thenReturn(false);
    mvc.perform(post("/auth/register")
      .contentType(MediaType.APPLICATION_JSON)
      .content("{\"username\":\"test\",\"password\":\"1234\"}")
    ).andExpect(status().isOk());
  }
}
