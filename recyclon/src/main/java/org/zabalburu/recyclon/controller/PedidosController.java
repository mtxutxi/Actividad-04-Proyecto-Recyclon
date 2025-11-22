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
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        // si no está logueado redirige al login
        if (usuario == null) {
            response.sendRedirect("usuarios");
            return;
        }
        
        // lista pedidos en el verdetalels
        String pagina = listarPedidos(request, usuario);
        request.getRequestDispatcher(pagina).forward(request, response);
    }

    //lkista todos los pedidos de la app en admin o solo los del usuario
    private String listarPedidos(HttpServletRequest request, Usuario usuario) {
        List<Pedido> pedidosUsuario;
        
        if (usuario.getIsAdmin() != null && usuario.getIsAdmin()) {
            // admin ve todos los pedidos de la app
            pedidosUsuario = service.getPedidos();
        } else {
            // usuario normal ve solo los suyos
            pedidosUsuario = verPedidosUsuario(usuario.getIdUsuario());
        }
        
        request.setAttribute("pedidos", pedidosUsuario);
        return "pedidos.jsp";
    }

    // filtra solo los pedidos del usuario
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
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    
	    HttpSession sesion = request.getSession();
	    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
	    
	    if (usuario == null) {
	        response.sendRedirect("usuarios");
	        return;
	    }
	    //verifica que sea administrador
	    if (usuario.getIsAdmin() == null || !usuario.getIsAdmin()) {
	        response.sendRedirect("pedidos");
	        return;
	    }
	    //llama a la acccion de cambiar estado
	    String accion = request.getParameter("accion");
	    
	    if ("cambiarEstado".equals(accion)) {
	        String idPedidoStr = request.getParameter("idPedido");
	        String nuevoEstado = request.getParameter("estado");
	        
	        if (idPedidoStr != null && nuevoEstado != null && !nuevoEstado.isEmpty()) {
	            try {
	                Integer idPedido = Integer.parseInt(idPedidoStr);
	                service.estadoPedido(idPedido, nuevoEstado);
	                sesion.setAttribute("mensaje", "Estado actualizado correctamente");
	            } catch (NumberFormatException e) {
	                sesion.setAttribute("error", "ID de pedido invalido");
	            }
	        }
	        
	        response.sendRedirect("pedidos");
	            return;
	        }
	        
	        doGet(request, response);
    }
}