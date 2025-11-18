package org.zabalburu.recyclon.service;

import java.util.List;

import org.zabalburu.recyclon.dao.categoria.CategoriaDAO;
import org.zabalburu.recyclon.dao.lineapedido.LineaPedidoDAO;
import org.zabalburu.recyclon.dao.pedido.PedidoDAO;
import org.zabalburu.recyclon.dao.producto.ProductoDAO;
import org.zabalburu.recyclon.dao.usuario.UsuarioDAO;
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
	public Pedido nuevoPedido(Pedido nuevo) { //ponemos un estado inicial si no tiene
		if (nuevo.getEstado() == null || nuevo.getEstado().isBlank()) {
			nuevo.setEstado("Pendiente");
		}
		return pedidoDAO.nuevoPedido(nuevo);
	}
	
	public Pedido modificarPedido(Pedido modificado) {
		return pedidoDAO.modificarPedido(modificado);
	}
	
	public Pedido getPedido(Integer id) {
		return pedidoDAO.getPedido(id);
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
	
	public List<Producto> getProductosPorCategoria() {
		return productoDAO.getProductosPorCategoria();
	}
	
	public List<Producto> getCategoriaProducto(Integer idCategoria) {
		return productoDAO.getCategoriaProducto(idCategoria);
	}
	
	public List<Producto> buscarProducto(String busqueda) {
		return productoDAO.buscarProducto(busqueda);
	}
	
	//USUARIO//
	public Usuario nuevoUsuario(Usuario nuevo) {
		return usuarioDAO.nuevoUsuario(nuevo);
	}
	
	public Usuario modificarUsuario(Usuario modificado) {
		return usuarioDAO.modificarUsuario(modificado);
	}
	
	public Usuario getUsuario(Integer id) {
		return usuarioDAO.getUsuario(id);
	}
	
	public Usuario login(String email, String contrasenaHash) {
		return usuarioDAO.login(email, contrasenaHash);
	}
}
