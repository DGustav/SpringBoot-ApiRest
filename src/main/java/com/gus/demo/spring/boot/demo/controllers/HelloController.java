package com.gus.demo.spring.boot.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hola, Spring Boot 🚀";
    }

    @GetMapping("/saludo")
    public String saludo() {
        return "Hola, soy Gus y estoy aprendiendo Spring Boot 💪";
    }

    @GetMapping("/saludo/{nombre}")
    public String saludarA(@PathVariable String nombre) {
        return "Hola " + nombre + ", bienvenido a Spring Boot 🎉";
    }
}
