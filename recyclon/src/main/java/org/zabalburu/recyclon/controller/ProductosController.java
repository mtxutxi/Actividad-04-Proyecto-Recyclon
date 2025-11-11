package org.zabalburu.recyclon.controller;

import java.io.IOException;

import org.zabalburu.recyclon.cdi.MensajeCDI;
import org.zabalburu.recyclon.modelo.Producto;
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
		//Obtiene el parametro del formulario y lo guarda en una variable para manejar esos datos
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		String categoria = request.getParameter("categoria");
		String strPrecio = request.getParameter("precio");
		String strStock = request.getParameter("stock");
		
		//Ahora vamos a parsear los datos que hemos recibido de string a double e integer
		Double precio = 0.0;
		Integer stock = 0;
		try {
			precio = Double.parseDouble(strPrecio); //pasamos el string a double y lo guardamos en la variable prescio
			stock = Integer.parseInt(strStock);
		}catch(NumberFormatException e) {
			//Si meten mal el dato por que la gente es así.. les damos un mensajito simpatico
			mensajeCDI.setRole("alert alert-danger");
			mensajeCDI.setMessage("Introduzca un valor valido");
			return "producto.jsp"; //volvemos a la pagina para qu elo intetne de nuevo
		}
		//Comprobamos que el precio sea mayor que cero,si no, mandamos mensaje
		if(precio <= 0) {
			mensajeCDI.setRole("alert alert-danger");
			mensajeCDI.setMessage("Introduzca un importe mayor que cero");
			return "producto.jsp";
		}
		
		//HAcemos lo mismo con stock
		if(stock <= 0) {
			mensajeCDI.setRole("alert alert-danger");
			mensajeCDI.setMessage("Introduzca un numero superior a cero");
			return "producto.jsp";
		}
		//CREAMOS EL NUEVO PRODUCTO y le asignamos sus atributos conseguidos con el formulario
		Producto producto = new Producto();
		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setCategoria(null);
		producto.setPrecio(precio); //Aqui ya estan bien parseados
		producto.setStock(stock);
		
		//LLAMAMOS AL SERVICE y le pasamos el nuevo producto para que lo inserte en la BBDD
		service.nuevoProducto(producto);
		
		//mostramos mensaje de exito
		mensajeCDI.setRole("alert alert-success");
		mensajeCDI.setMessage("¡Producto creado con exito!");
		//volvemos a la pagina de inicio que nos mostrara la pagina con la lista actualizada
		return "productos.jsp";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
