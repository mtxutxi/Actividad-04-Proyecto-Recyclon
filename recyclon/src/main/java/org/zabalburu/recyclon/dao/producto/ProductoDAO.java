package org.zabalburu.recyclon.dao.producto;

import java.util.List;

import org.zabalburu.recyclon.modelo.Categoria;
import org.zabalburu.recyclon.modelo.Producto;

public interface ProductoDAO {
	Producto nuevoProducto(Producto nuevo);
	Producto modificarProducto(Producto modificado);
	void eliminarProducto(Integer id);
	Producto getProducto(Integer id); //Seleccionar un Producto
	List<Producto> getProductosPorCategoria(); //Para filtrar todos los productos por cada categoria
	List<Producto> getCategoriaProducto(Integer idCategoria); //Para mostrar los productos de una categoria
	List<Producto> buscarProducto(String busqueda); //Buscar producto con un LIKE
}
