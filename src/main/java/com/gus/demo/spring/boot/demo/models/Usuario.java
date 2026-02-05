package com.gus.demo.spring.boot.demo.models;

import com.gus.demo.spring.boot.demo.models.enums.Rol;
import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}