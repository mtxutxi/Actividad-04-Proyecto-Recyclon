package org.zabalburu.recyclon.modelo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Getter;

@Getter
@SessionScoped
@Named("cesta")
public class Cesta implements Serializable { 
    
    private static final long serialVersionUID = 1L;
    
    // mejor map esta vez que list porque el map solo guardamos id y cantidades. list guarda los objetos enteros
    private Map<Integer, Integer> productos = new HashMap<>();
    
    public Cesta() {
    }
    
    public void añadirProducto(Integer idProducto, Integer cantidad) {
        productos.put(idProducto, productos.getOrDefault(idProducto, 0) + cantidad);
    }
    
    public void eliminarProducto(Integer idProducto) {
        productos.remove(idProducto);
    }
    
    public void vaciar() {
        productos.clear();
    }
    
    public boolean estaVacia() {
        return productos.isEmpty();
    }
    
    public Integer getCantidad(Integer idProducto) {
        return productos.getOrDefault(idProducto, 0);
    }
    
    public int getTotalProductos() {
        return productos.values().stream().mapToInt(Integer::intValue).sum();
    }
}