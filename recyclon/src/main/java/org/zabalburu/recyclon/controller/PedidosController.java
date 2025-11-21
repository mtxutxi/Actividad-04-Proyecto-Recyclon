package org.zabalburu.recyclon.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.zabalburu.recyclon.modelo.Pedido;
import org.zabalburu.recyclon.modelo.Usuario;
import org.zabalburu.recyclon.service.GestionService;

	/**
	 * Servlet implementation class PedidosController
	 */
	@WebServlet("/pedidos")
	public class PedidosController extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		@Inject
		private GestionService service;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public PedidosController() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	    

        //quizas meterle una accion ver detalle(?)
	    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        // si no está logueado hacia login
        if (usuario == null) {
            response.sendRedirect("usuarios");
            return;
        }

        List<Pedido> pedidosUsuario;

        if (usuario.getIsAdmin() != null && usuario.getIsAdmin()) { //admin ve todos los pedidos de la app
            pedidosUsuario = service.getPedidos();

        } else {
            pedidosUsuario = verPedidosUsuario(usuario.getIdUsuario()); //user normal ve los suyos
        }

        request.setAttribute("pedidos", pedidosUsuario);
        request.getRequestDispatcher("pedidos.jsp").forward(request, response);
    }


    //solo pedidos usuario
    private List<Pedido> verPedidosUsuario(Integer idUsuario) {
        List<Pedido> listaPedidos = service.getPedidos();
        List<Pedido> pedidosUsuario = new ArrayList<>();

        for (Pedido p : listaPedidos) {
            if (p.getUsuario().getIdUsuario().equals(idUsuario)) {
                pedidosUsuario.add(p);
            }
        }

        return pedidosUsuario;
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
