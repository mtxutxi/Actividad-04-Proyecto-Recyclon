package org.zabalburu.recyclon.dao.usuario;

import org.zabalburu.recyclon.modelo.Usuario;

public interface UsuarioDAO {
	Usuario nuevoUsuario(Usuario nuevo);
	Usuario modificarUsuario(Usuario modificado);
	Usuario buscarUsuarioPorEmail(String email);
}