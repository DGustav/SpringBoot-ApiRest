package com.gus.demo.spring.boot.demo.repositories;

import com.gus.demo.spring.boot.demo.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
