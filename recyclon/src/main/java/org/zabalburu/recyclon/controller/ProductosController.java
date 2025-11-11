package org.zabalburu.recyclon.controller;

import java.io.IOException;

import org.zabalburu.recyclon.cdi.MensajeCDI;
import org.zabalburu.recyclon.service.GestionService;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductosController
 */
@WebServlet("/productos")
public class ProductosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	private GestionService service;
	
	@Inject
	private MensajeCDI mensajeCDI;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductosController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				String pagina = null; // declaramos e inicializamos la variable que almaceanara la ruta del jsp al que se redirigira
				String accion = request.getParameter("accion"); //obtiene el parametro accion de la URL y lo vuelca en la variable accion
				/*Los datos que se cargan automaticamente en la pagina y no hacen operaciones van en el if, si hacen algo o necesitan 
				  interaccion del ususario van al switch por que tenemos sus metodos fuera*/
				if(accion == null) {//esto seria que no hacen operaciones, solo muestran los datos sin más
					request.setAttribute("productosporcategoria", service.getProductosPorCategoria());// obtiene los productosPorCat usando el metodo del service y los guarda en pedidos para que esten disponibles en el jsp
					pagina = "index.jsp";
				}else {/*Ahora vamos a meter las acciones que requieren intervención del usuario, que no son de mostrar y listo.*/
					switch (accion.toLowerCase()) {
						case "nuevoproducto": 
							pagina = nuevoProducto(request,response);
							break;
						case "modificarproducto":
							pagina = modificarProducto(request,response);
							break;
						case "eliminarproducto":
							pagina = eliminarProducto(request,response);
							break;
						case "getproducto":
							pagina = getProducto(request,response);
							break;
						case "getcategoriaproducto":
							pagina = getCategoriaProducto(request,response);
							break;
						case "buscarproducto":
							pagina = buscarProducto(request,response);
							break;
						
					}
				}
				request.getRequestDispatcher(pagina).forward(request, response);
	}

	private String buscarProducto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getCategoriaProducto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getProducto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private String eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private String modificarProducto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	private String nuevoProducto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
