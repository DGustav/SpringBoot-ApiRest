package com.gus.demo.spring.boot.demo.services;

import com.gus.demo.spring.boot.demo.exceptions.ProductoNoEncontradoException;
import com.gus.demo.spring.boot.demo.models.Producto;
import com.gus.demo.spring.boot.demo.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> listar() {
        return repository.findAll();
    }

    public Producto crear(Producto producto) {
        return repository.save(producto);
    }

    public Producto obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));
    }

    public Producto actualizar(Long id, Producto datos) {
        Producto existente = repository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException(id));

        existente.setNombre(datos.getNombre());
        existente.setPrecio(datos.getPrecio());
        existente.setCategoria(datos.getCategoria());

        return repository.save(existente);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ProductoNoEncontradoException(id);
        }
        repository.deleteById(id);
    }
}
