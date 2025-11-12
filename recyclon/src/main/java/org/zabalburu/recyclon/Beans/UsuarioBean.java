package org.zabalburu.recyclon.Beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.zabalburu.recyclon.dao.GestionDAO;
import org.zabalburu.recyclon.modelo.Usuario;
import org.zabalburu.recyclon.util.PasswordUtil;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@SessionScoped
@Named("usuario")
public class UsuarioBean implements Serializable {
    private String nombreUsuario;
    private Usuario usuarioActual;
    private boolean autenticado = false;
    
    private GestionDAO gestionDAO = new GestionDAOImpl();

    // Usuarios predefinidos
    private static final Map<String, String> usuarios = new HashMap<>();

    static {
        usuarios.put("usuario1", "password1");
        usuarios.put("usuario2", "password2");
    }

    public Usuario login(String email, String password) {
        Usuario u = gestionDAO.buscarUsuarioPorEmail(email);
        
        if (u != null && u.getPassword() != null) {
            // Verificar contraseña usando tu PasswordUtil 👇
            if (PasswordUtil.verifyPassword(password, u.getPassword())) {
                this.nombreUsuario = u.getNombre();
                this.usuarioActual = u;
                this.autenticado = true;
                return u;
            }
        }
        
        return null;
    }

    public void logout() {
        this.nombreUsuario = null;
        //this.usuarioActual = null;
        this.autenticado = false;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void nuevoUsuario(Usuario u, String password) {
        String passwordHash = PasswordUtil.hashPassword(password);
        u.setPassword(passwordHash);
        u.setIsAdmin(false);
        
        gestionDAO.nuevoUsuario(u);
    }
	
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
    public boolean isAdmin() {
        return autenticado && usuarioActual != null && 
               usuarioActual.getIsAdmin() != null && 
               usuarioActual.getIsAdmin();
    }
}
