package org.zabalburu.recyclon.dao.usuario;

import org.zabalburu.recyclon.modelo.Usuario;

public interface UsuarioDAO {
	Usuario nuevoUsuario(Usuario nuevo); //Registro de usuario
	Usuario modificarUsuario(Usuario modificado);
	Usuario getUsuario(Integer id);
	Usuario login(String email, String contrasenaHash);
}
