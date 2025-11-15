package org.zabalburu.recyclon.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.zabalburu.recyclon.cdi.MensajeCDI;
import org.zabalburu.recyclon.modelo.Usuario;
import org.zabalburu.recyclon.service.GestionService;
import org.zabalburu.recyclon.util.PasswordUtil;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/usuarios")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@Inject
	private GestionService service;
	
	@Inject
	private MensajeCDI mensajeCDI;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
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
		} else if (accion.toLowerCase().equals("login")) {
			pagina = login(request, response);
		} else if (accion.toLowerCase().equals("registro")) {
			pagina = "registro.jsp";
		} else if (accion.toLowerCase().equals("guardar")) {
			pagina = guardarUsuario(request, response);
		}
		request.getRequestDispatcher(pagina).forward(request, response);
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String contraseña = request.getParameter("password");
		if (email.isBlank() || contraseña.isBlank()) {
			mensajeCDI.setMessage("Todos los campos son obligatorios.");
			mensajeCDI.setRole("alert-danger");
			return "index.jsp";
		}
		if (service.login(email, contraseña) == null) {
			mensajeCDI.setMessage("Usuario / Contraseña Erróneos");
			mensajeCDI.setRole("alert-danger");
			return "index.jsp";
		}
		return "controladorrecyclon";
	}
	
	public String guardarUsuario(HttpServletRequest request, HttpServletResponse response) {
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String tlfno = request.getParameter("telefono");
		Integer telefono = null;
		String fNacimiento = request.getParameter("fNacimiento");
		Date fechaNacimiento = null;
		String dni = request.getParameter("dni");
		String email = request.getParameter("email");
		String contrasena1 = request.getParameter("contrasena1");
		String contrasena2 = request.getParameter("contrasena2");
		
		if (nombre.isBlank() || apellidos.isBlank() || dni.isBlank() || email.isBlank() 
				|| contrasena1.isBlank() || contrasena2.isBlank()) {
			mensajeCDI.setMessage("Todos los campos salvo el nº de telefono y la fecha de nacimiento son obligatorios.");
			mensajeCDI.setRole("alert-danger");
			return "registro.jsp";
		}
		if (tlfno != null && !tlfno.isBlank()) {
			try {
				telefono = Integer.parseInt(tlfno);
			} catch (NumberFormatException e) {
				mensajeCDI.setMessage("El teléfono debe ser un número válido.");
				mensajeCDI.setRole("alert-danger");
				return "registro.jsp";
			}
		}
		if (fNacimiento != null && !fNacimiento.isBlank()) {
			try {
				fechaNacimiento = df.parse(fNacimiento);
			} catch (ParseException e) {
				mensajeCDI.setMessage("Introduce una fecha válida (AAAA-MM-DD)");
				mensajeCDI.setRole("alert-danger");
				return "registro.jsp";
			}
		}
		if (!contrasena1.equals(contrasena2)) {
			mensajeCDI.setMessage("Las contraseñas no coinciden.");
			mensajeCDI.setRole("alert.danger");
			return "registro.jsp";
		}
		Usuario u = new Usuario();
		u.setNombre(nombre);
		u.setApellidos(apellidos);
		u.setTelefono(telefono);
		u.setFechaNacimiento(fechaNacimiento);
		u.setDocumentoIdentidad(dni);
		u.setEmail(email);
		u.setContrasenaHash(PasswordUtil.hashPassword(contrasena1));
		service.nuevoUsuario(u);
		return "controladorrecyclon";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
