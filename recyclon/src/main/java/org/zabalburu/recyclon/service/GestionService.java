package org.zabalburu.recyclon.service;

import java.util.List;

import org.zabalburu.recyclon.dao.CategoriaDAO;
import org.zabalburu.recyclon.dao.LineaPedidoDAO;
import org.zabalburu.recyclon.dao.PedidoDAO;
import org.zabalburu.recyclon.dao.ProductoDAO;
import org.zabalburu.recyclon.dao.UsuarioDAO;
import org.zabalburu.recyclon.modelo.Categoria;
import org.zabalburu.recyclon.modelo.LineaPedido;
import org.zabalburu.recyclon.modelo.Pedido;
import org.zabalburu.recyclon.modelo.Producto;
import org.zabalburu.recyclon.modelo.Usuario;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped //Para que haya una única instancia del Service
public class GestionService {
	
	@Inject //Para no tener que crear un "new DAO();", lo hace solo
	private CategoriaDAO categoriaDAO;
	
	@Inject
	private LineaPedidoDAO lineaPedidoDAO;
	
	@Inject
	private PedidoDAO pedidoDAO;
	
	@Inject
	private ProductoDAO productoDAO;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	//CATEGORIA//
	public Categoria nuevaCategoria(Categoria nueva) {
		return categoriaDAO.nuevaCategoria(nueva);
	}
	
	public Categoria modificarCategoria(Categoria modificada) {
		return categoriaDAO.modificarCategoria(modificada);
	}
	
	public void eliminarCategoria(Integer id) {
		categoriaDAO.eliminarCategoria(id);
	}
	
	//LINEA_PEDIDO//
	public LineaPedido nuevaLineaPedido(LineaPedido nueva) {
		return lineaPedidoDAO.nuevaLineaPedido(nueva);
	}
	
	public LineaPedido modificarLineaPedido(LineaPedido modificada) {
		return lineaPedidoDAO.modificarLineaPedido(modificada);
	}
	
	public void eliminarLineaPedido(Integer id) {
		lineaPedidoDAO.eliminarLineaPedido(id);
	}
	
	public LineaPedido getLineaPedido(Integer id) {
		return lineaPedidoDAO.getLineaPedido(id);
	}
	
	//PEDIDO//
	public Pedido nuevoPedido(Pedido nuevo) {
		return pedidoDAO.nuevoPedido(nuevo);
	}
	
	public Pedido modificarPedido(Pedido modificado) {
		return modificarPedido(modificado);
	}
	
	public List<Pedido> getPedidos() {
		return pedidoDAO.getPedidos();
	}
	
	//PRODUCTO//
	public Producto nuevoProducto(Producto nuevo) {
		return productoDAO.nuevoProducto(nuevo);
	}
	
	public Producto modificarProducto(Producto modificado) {
		return productoDAO.modificarProducto(modificado);
	}
	
	public void eliminarProducto(Integer id) {
		productoDAO.eliminarProducto(id);
	}
	
	public Producto getProducto(Integer id) {
		return productoDAO.getProducto(id);
	}
	
	public List<Categoria> getProductosPorCategoria() {
		return productoDAO.getProductosPorCategoria();
	}
	
	public List<Categoria> getCategoriaProducto(String categoria) {
		return productoDAO.getCategoriaProducto(categoria);
	}
	
	//USUARIO//
	public Usuario nuevoUsuario(Usuario nuevo) {
		return usuarioDAO.nuevoUsuario(nuevo);
	}
	
	public Usuario modificarUsuario(Usuario modificado) {
		return usuarioDAO.modificarUsuario(modificado);
	}
}
