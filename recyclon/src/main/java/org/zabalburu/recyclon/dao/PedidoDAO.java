package org.zabalburu.recyclon.dao;

import java.util.List;

import org.zabalburu.recyclon.modelo.Pedido;

public interface PedidoDAO {
	Pedido nuevoPedido(Pedido nuevo);
	Pedido modificarPedido(Pedido modificado); //Para modificar el estado del pedido
	List<Pedido> getPedidos(); //Listar pedidos y obtener su estado
}
