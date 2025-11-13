package org.zabalburu.recyclon.Beans;

import java.io.Serializable;
import org.zabalburu.recyclon.modelo.Usuario;
import org.zabalburu.recyclon.service.GestionService;
import org.zabalburu.recyclon.util.PasswordUtil;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@SessionScoped
@Named("usuarioBean")
public class UsuarioBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String nombreUsuario;
    private Usuario usuarioActual;
    private boolean autenticado = false;
    
    // ⬇️ CAMBIO 1: Inyectar GestionService en lugar de crear un DAO
    @Inject
    private GestionService service;
    
    /**
     * Login de usuario
     */
    public Usuario login(String email, String password) {
        Usuario u = service.buscarUsuarioPorEmail(email);
        
        if (u != null && u.getContrasenaHash() != null) {
            if (PasswordUtil.verifyPassword(password, u.getContrasenaHash())){
                this.nombreUsuario = u.getNombre();
                this.usuarioActual = u;
                this.autenticado = true;
                return u;
            }
        }
        
        return null;
    }
    
    /**
     * Logout de usuario
     */
    public void logout() {
        this.nombreUsuario = null;
        this.usuarioActual = null;
        this.autenticado = false;
    }
    
    /**
     * Registro de nuevo usuario
     */
    public void nuevoUsuario(Usuario u, String password) {
        String passwordHash = PasswordUtil.hashPassword(password);
        u.setPassword(passwordHash);
        u.setIsAdmin(false);
        
        service.nuevoUsuario(u);
        
        this.nombreUsuario = u.getNombre();
        this.usuarioActual = u;
        this.autenticado = true;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public boolean isAutenticado() {
        return autenticado;
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