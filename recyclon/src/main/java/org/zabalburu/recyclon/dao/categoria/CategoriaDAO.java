package org.zabalburu.recyclon.dao.categoria;

import org.zabalburu.recyclon.modelo.Categoria;

public interface CategoriaDAO {
	//Metodos para el Admnistrador
	Categoria nuevaCategoria(Categoria nueva);
	Categoria modificarCategoria(Categoria modificada);
	void eliminarCategoria(Integer id);
}
