package org.zabalburu.recyclon.dao;

import java.util.List;

import org.zabalburu.recyclon.modelo.Categoria;
import org.zabalburu.recyclon.modelo.LineaPedido;
import org.zabalburu.recyclon.modelo.Pedido;
import org.zabalburu.recyclon.modelo.Producto;

public class GestionDAOImpl implements GestionDAO {

	public GestionDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eliminarProducto(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarProducto(Producto eliminar) {
		// TODO Auto-generated method stub

	}

	@Override
	public Producto nuevoProducto(Producto nuevo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto getProducto(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> getProductosPorCategoria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> getCategoriaFilter(String categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido modificarPedido(Pedido modificado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido nuevoPedido(Pedido nuevo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> getPedidos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> getProductosPedido() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LineaPedido getLineaPedido(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
