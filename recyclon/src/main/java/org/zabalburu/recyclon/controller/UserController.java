package org.zabalburu.recyclon.controller;

import java.io.File;
import java.io.IOException;

import org.zabalburu.recyclon.modelo.Usuario;
import org.zabalburu.recyclon.CDI.MensajeCDI;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
			pagina = "index.jsp";  // login
		} else if (accion.toLowerCase().equals("login")){
			pagina = login(request,response);
		} else if (accion.toLowerCase().equals("register")) {
			pagina = "register.jsp";
		} else if (accion.toLowerCase().equals("saveuser")) {
			
			pagina = saveUser(request,response);
		}
		request.getRequestDispatcher(pagina).forward(request, response);
	}

	private String saveUser(HttpServletRequest request, HttpServletResponse response) {
		String uploadPath = getServletContext().getRealPath("") + File.separator + "fotos";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();
		
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		if (nombre.isBlank() || apellidos.isBlank() || email.isBlank() || password.isBlank()
				|| password2.isBlank()) {
			mensajeBean.setTexto("Todos los campos excepto la foto SON OBLIGATORIOS!");
			mensajeBean.setCssClass("alert-danger");
			return "register.jsp";
		}
		if (!password.equals(password2)){
			mensajeBean.setTexto("Las contraseñas NO COINCIDEN!");
			mensajeBean.setCssClass("alert-danger");
			return "register.jsp";
		}
		String fileName = "";
		Part part;
		try {
			part = request.getPart("foto");
			fileName = part.getSubmittedFileName();
			if (fileName != null) {
		    	part.write(uploadPath + File.separator + fileName);
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Usuario u = new Usuario();
		u.setNombre(nombre);
		u.setApellidos(apellidos);
		u.setEmail(email);
		if (fileName != null) {
			u.setFoto(fileName);
		}
		usuarioBean.nuevoUsuario(u, password);
	    return "events";
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (email.isBlank() || password.isBlank()) {
			mensajeBean.setTexto("Todos los campos sonb OBLIGATORIOS!");
			mensajeBean.setCssClass("alert-danger");
			return "index.jsp";
		} 
		if (usuarioBean.login(email, password) == null) {
			mensajeBean.setTexto("Usuario / Password ERRÓNEOS");
			mensajeBean.setCssClass("alert-danger");
			return "index.jsp";
		} 
		return "events";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
