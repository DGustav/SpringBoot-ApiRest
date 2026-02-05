package com.gus.demo.spring.boot.demo.repositories;

import com.gus.demo.spring.boot.demo.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
