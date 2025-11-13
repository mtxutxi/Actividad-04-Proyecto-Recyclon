package org.zabalburu.recyclon.controller;

import org.zabalburu.recyclon.Beans.UsuarioBean;
import org.zabalburu.recyclon.cdi.MensajeCDI;
import org.zabalburu.recyclon.modelo.Usuario;

import java.io.File;
import java.io.IOException;


import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/users")
@MultipartConfig
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;      

	@Inject
	private UsuarioBean usuarioBean;
	
	@Inject
	private MensajeCDI mensajeBean;
	
	
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String pagina = "";
        if (accion == null) {
            pagina = "index.jsp";
        } else if (accion.equalsIgnoreCase("login")) {
            pagina = login(request, response);
        } else if (accion.equalsIgnoreCase("register")) {
            pagina = "register.jsp";
        } else if (accion.equalsIgnoreCase("saveuser")) {
            pagina = saveUser(request, response);
        } else if (accion.equalsIgnoreCase("logout")) {
            pagina = logout(request, response);
        }
		request.getRequestDispatcher(pagina).forward(request, response);
	}

	private String saveUser(HttpServletRequest request, HttpServletResponse response) {
		
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		if (nombre.isBlank() || apellidos.isBlank() || email.isBlank() || password.isBlank()
				|| password2.isBlank()) {
			mensajeBean.setTexto("Todos los campos SON OBLIGATORIOS!");
			mensajeBean.setCssClass("alert-danger");
			return "register.jsp";
		}
		if (!password.equals(password2)){
			mensajeBean.setTexto("Las contraseñas NO COINCIDEN!");
			mensajeBean.setCssClass("alert-danger");
			return "register.jsp";
		}
		
		Usuario u = new Usuario();
		u.setNombre(nombre);
		u.setApellidos(apellidos);
		u.setEmail(email);
		
		
		usuarioBean.nuevoUsuario(u, password);
	    return "productos";
		}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String error = null;
		if (usuario.isBlank() || password.isBlank()) {
			error = "Debe especificar todos los campos";
		} else {
			Usuario u = usuarioBean.login(usuario, password);
			if (u == null){
				error = "Usuario / password erróneos";
			} else {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuario", u);
			}
		}
		if (error != null) {
			request.setAttribute("error", error);
			return "index.jsp";
		} else {
			return "usuario.jsp";
		}
	}

	
    private String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        mensajeBean.setTexto("Sesion cerrada correctamente");
        mensajeBean.setCssClass("alert-success");
        return "index.jsp";
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
