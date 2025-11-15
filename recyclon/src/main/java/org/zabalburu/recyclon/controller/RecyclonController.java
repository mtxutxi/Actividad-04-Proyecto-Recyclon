package org.zabalburu.recyclon.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.zabalburu.recyclon.cdi.MensajeCDI;
import org.zabalburu.recyclon.modelo.Usuario;
import org.zabalburu.recyclon.service.GestionService;

/**
 * Servlet implementation class ControladorRecyclon
 */
@WebServlet(
		urlPatterns = { "/controladorrecyclon" }, 
		initParams = { 
				@WebInitParam(name = "acion", value = "")
		})
public class RecyclonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	private GestionService service;
	
	@Inject
	private MensajeCDI mensajeCDI;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecyclonController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagina = "usuario.jsp";
		String accion = request.getParameter("accion");
		HttpSession sesion = request.getSession();
		Usuario usuario = (Usuario)sesion.getAttribute("usuario");
		if (usuario == null) {
			response.sendRedirect("usuarios");
			return;
		}
		request.setAttribute("usuario", usuario);
		request.getRequestDispatcher(pagina).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
