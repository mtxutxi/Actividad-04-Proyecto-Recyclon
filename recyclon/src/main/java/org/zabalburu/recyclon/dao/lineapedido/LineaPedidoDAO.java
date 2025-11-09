package org.zabalburu.recyclon.dao.lineapedido;

import org.zabalburu.recyclon.modelo.LineaPedido;

public interface LineaPedidoDAO {
	//Metodos para el Admnistrador
	LineaPedido nuevaLineaPedido(LineaPedido nueva);
	LineaPedido modificarLineaPedido(LineaPedido modificada);
	void eliminarLineaPedido(Integer id);
	LineaPedido getLineaPedido(Integer id); //Consultar una LineaPedido
}
