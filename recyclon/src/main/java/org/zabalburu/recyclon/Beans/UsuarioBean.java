package org.zabalburu.recyclon.Beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.zabalburu.recyclon.modelo.Usuario;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@SessionScoped
@Named("usuario")
public class UsuarioBean implements Serializable {
    private String nombreUsuario;
    private boolean autenticado = false;

    // Usuarios predefinidos
    private static final Map<String, String> usuarios = new HashMap<>();

    static {
        usuarios.put("usuario1", "password1");
        usuarios.put("usuario2", "password2");
    }

    public boolean login(String nombreUsuario, String password) {
        if (usuarios.containsKey(nombreUsuario) && usuarios.get(nombreUsuario).equals(password)) {
            this.nombreUsuario = nombreUsuario;
            this.autenticado = true;
            return true;
        }
        return false;
    }

    public void logout() {
        this.nombreUsuario = null;
        this.autenticado = false;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

	public void nuevoUsuario(Usuario u, String password) {
		// TODO Auto-generated method stub
		
	}
}
