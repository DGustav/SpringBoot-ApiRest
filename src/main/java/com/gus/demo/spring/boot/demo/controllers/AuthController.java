package com.gus.demo.spring.boot.demo.controllers;

import com.gus.demo.spring.boot.demo.DTOs.AuthRequest;
import com.gus.demo.spring.boot.demo.DTOs.AuthResponse;
import com.gus.demo.spring.boot.demo.models.Usuario;
import com.gus.demo.spring.boot.demo.repositories.UsuarioRepository;
import com.gus.demo.spring.boot.demo.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {

        Usuario user = usuarioRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password incorrecto");
        }

        String token = jwtService.generarToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registrar(@RequestBody Usuario req) {
        if (usuarioRepository.findByUsername(req.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }

        Usuario user = new Usuario();
        user.setUsername(req.getUsername());
        user.setRol(req.getRol());
        user.setPassword(passwordEncoder.encode(req.getPassword())); //

        usuarioRepository.save(user);

        return ResponseEntity.ok("Usuario registrado correctamente");
    }
}
