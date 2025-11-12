package org.zabalburu.recyclon.dao;

import java.util.List;

import org.zabalburu.recyclon.modelo.Categoria;
import org.zabalburu.recyclon.modelo.LineaPedido;
import org.zabalburu.recyclon.modelo.Pedido;
import org.zabalburu.recyclon.modelo.Producto;
import org.zabalburu.recyclon.modelo.Usuario;



public interface GestionDAO {
	//PRODUCTO
	void eliminarProducto(Integer id);
	void eliminarProducto(Producto eliminar);
	Producto nuevoProducto(Producto nuevo); //Añadir productos
	Producto getProducto(Integer id); //Ver el producto individual
	
	//CATEGORIA
	List<Categoria> getProductosPorCategoria(); //Listamos TODO, sacando su lista de categorias con "sublistas" de productos.Es un extra.
	List<Categoria> getCategoriaFilter(String categoria); //Filtro por categoria
	
	//PEDIDO
	Pedido modificarPedido(Pedido modificado);//Para cambiar el estado del pedido
	Pedido nuevoPedido(Pedido nuevo);  //Añadir nuevo pedido 
	List<Pedido> getPedidos(); //Listamos pedidos, y en la ultima columna, saldra su estado (finalizado/pdte)
	
	//LINEA PEDIDO
	List<Producto> getProductosPedido(); //Listar los productos de cada 
	LineaPedido getLineaPedido(Integer id); 
	
    //USUARIO añadidos metodos user para jugar login
    Usuario getUsuario(Integer id);
    Usuario buscarUsuarioPorEmail(String email);
    Usuario nuevoUsuario(Usuario nuevo);
} 
