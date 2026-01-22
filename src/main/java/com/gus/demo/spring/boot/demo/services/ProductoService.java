package com.gus.demo.spring.boot.demo.services;

import com.gus.demo.spring.boot.demo.models.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class ProductoService {
    private List<Producto> productos = new ArrayList<>();
    private Long contadorId = 1L;

    public List<Producto> listar() {
        return productos;
    }

    public Producto crear(Producto producto) {
        producto.setId(contadorId++);
        productos.add(producto);
        return producto;
    }

    public Producto obtenerPorId(Long id) {
        return productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Producto actualizar(Long id, Producto nuevo) {
        Producto existente = obtenerPorId(id);
        if (existente != null) {
            existente.setNombre(nuevo.getNombre());
            existente.setPrecio(nuevo.getPrecio());
        }
        return existente;
    }

    public boolean eliminar(Long id) {
        return productos.removeIf(p -> p.getId().equals(id));
    }
}
