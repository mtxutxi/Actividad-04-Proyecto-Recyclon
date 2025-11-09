package org.zabalburu.recyclon.dao;

import java.util.List;

import org.zabalburu.recyclon.modelo.Categoria;
import org.zabalburu.recyclon.modelo.Producto;

public interface ProductoDAO {
	Producto nuevoProducto(Producto nuevo);
	Producto modificarProducto(Producto modificado);
	void eliminarProducto(Integer id);
	Producto getProducto(Integer id); //Seleccionar un Producto
	List<Categoria> getProductosPorCategoria(); //Para filtrar todos los productos por cada categoria
	List<Categoria> getCategoriaProducto(String categoria); //Para mostrar los productos de una categoria
}
