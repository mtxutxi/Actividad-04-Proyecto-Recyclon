package org.zabalburu.recyclon.dao;

import org.zabalburu.recyclon.modelo.Usuario;

public interface UsuarioDAO {
	Usuario nuevoUsuario(Usuario nuevo); //Registro de usuario
	Usuario modificarUsuario(Usuario modificado);
}
