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
import java.util.Map;

import org.zabalburu.recyclon.modelo.Cesta;
import org.zabalburu.recyclon.modelo.LineaPedido;
import org.zabalburu.recyclon.modelo.Pedido;
import org.zabalburu.recyclon.modelo.Producto;
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
    
    @Inject
    private Cesta cesta;
       
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
        
        // lista pedidos
        String pagina = listarPedidos(request, usuario);
        request.getRequestDispatcher(pagina).forward(request, response);
    }

    // lista todos los pedidos de la app en admin o solo los del usuario
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
        
        String accion = request.getParameter("accion");
        
        //finaliza pedido desde carrito (cualkier usuario puede hacerlo)
        if ("finalizarpedido".equals(accion)) {
            finalizarPedido(request, response, sesion, usuario);
            return;
        }
        
        // verifica que sea administrador para cambiar estado
        if (usuario.getIsAdmin() == null || !usuario.getIsAdmin()) {
            response.sendRedirect("pedidos");
            return;
        }
        
        //solo admin
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
    
    //metodo para finaliza pedido desde carrito
    private void finalizarPedido(HttpServletRequest request, HttpServletResponse response,
                                 HttpSession sesion, Usuario usuario) throws IOException {
        
        //verificar que el carrito no esté vacío
        if (cesta.estaVacia()) {
            sesion.setAttribute("error", "El carrito está vacío");
            response.sendRedirect("productos?accion=vercarrito");
            return;
        }
        
        try {
            //crea pedido
            Pedido pedido = new Pedido();
            pedido.setUsuario(usuario);
            pedido.setFecha(new java.util.Date());
            pedido.setEstado("Pendiente");
            pedido = service.nuevoPedido(pedido);

         // MAP es mejor para cestas: acceso por id, evitas duplicados
         // actualiza cantidades sin buscar en la lista:
            //usado porque me daba un erroe
            
            // creaa lineas de pedido para cada producto del carrito
            for (Map.Entry<Integer, Integer> entry : cesta.getProductos().entrySet()) {
                Integer idProducto = entry.getKey();
                Integer cantidad = entry.getValue();
                
                Producto producto = service.getProducto(idProducto);
              
                // mira stock
                if (producto.getStock() < cantidad) {
                    sesion.setAttribute("error", "NO hay stock suficiente de " + producto.getNombre());
                    response.sendRedirect("productos?accion=vercarrito");
                    return;
                }
                //crea línea de pedido
                LineaPedido linea = new LineaPedido();
                linea.setPedido(pedido);
                linea.setProducto(producto);
                linea.setCantidad(cantidad);
                linea.setPrecio(producto.getPrecio());
                service.nuevaLineaPedido(linea);
                
                //actualizar stock
                producto.setStock(producto.getStock() - cantidad);
                service.modificarProducto(producto);
            }
            //vaciaa el carrito
            cesta.vaciar();
            
            sesion.setAttribute("mensaje", "¡Pedido realizado con éxito!");
            response.sendRedirect("pedidos");
            
        } catch (Exception e) {
            sesion.setAttribute("error", "Error al procesar el pedido");
            response.sendRedirect("productos?accion=vercarrito");
        }
    }
}