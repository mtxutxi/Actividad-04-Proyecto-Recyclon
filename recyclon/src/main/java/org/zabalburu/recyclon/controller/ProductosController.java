package org.zabalburu.recyclon.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.zabalburu.recyclon.cdi.MensajeCDI;
import org.zabalburu.recyclon.modelo.Producto;
import org.zabalburu.recyclon.modelo.Usuario;
import org.zabalburu.recyclon.service.GestionService;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

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
				HttpSession sesion = request.getSession();
				String pagina = null; // declaramos e inicializamos la variable que almaceanara la ruta del jsp al que se redirigira
				String accion = request.getParameter("accion"); //obtiene el parametro accion de la URL y lo vuelca en la variable accion
				/*Los datos que se cargan automaticamente en la pagina y no hacen operaciones van en el if, si hacen algo o necesitan 
				  interaccion del ususario van al switch por que tenemos sus metodos fuera*/
				if(accion == null) {//esto seria que no hacen operaciones, solo muestran los datos sin más
					request.setAttribute("productosporcategoria", service.getProductosPorCategoria());// obtiene los productosPorCat usando el metodo del service y los guarda en pedidos para que esten disponibles en el jsp
					pagina = "productos.jsp";
				}else {/*Ahora vamos a meter las acciones que requieren intervención del usuario, que no son de mostrar y listo.*/
					switch (accion.toLowerCase()) {
						case "nuevoproducto": 
							pagina = nuevoProducto(request,response);//Necesita permisos de Admin
							break;
						case "modificarproducto":
							pagina = modificarProducto(request,response);//Necesita permisos de Admin
							break;
						case "eliminarproducto":
							pagina = eliminarProducto(request,response);//Necesita permisos de Admin
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
		String producto = request.getParameter("producto");
		if(producto == null || producto.trim().isEmpty()) {//si producto es null, o quitando los espacios del principio y final esta vacio
			mensajeCDI.setMessage("Debes introducir un termino de busqueda valido");
			mensajeCDI.setRole("alert-danger");
			return "productos.jsp";
		}
		List<Producto> listaProducto = service.buscarProducto(producto);
		if(listaProducto.isEmpty()) {
			mensajeCDI.setMessage("No hay ninguna coincidencia con su busqueda");
			mensajeCDI.setRole("alert-danger");
		}else {
			mensajeCDI.setMessage("Se han encontrado" + listaProducto.size() + " resultados");
			mensajeCDI.setRole("alert-danger");
		}
		return "productos.jsp";
	}

	private String getCategoriaProducto(HttpServletRequest request, HttpServletResponse response) {
		String strIdCategoria = request.getParameter("id");
		Integer idCategoria = Integer.parseInt(strIdCategoria);
		service.getCategoriaProducto(idCategoria);
		return "preoductos.jsp";
	}

	private String getProducto(HttpServletRequest request, HttpServletResponse response) {
		String strIdProducto = request.getParameter("id");
		Integer idProducto = Integer.parseInt(strIdProducto);
		service.getProducto(idProducto);
		return "productos.jsp";
	}

	private String eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
		//Verificamos sesion
		HttpSession sesion = request.getSession();
	    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
	    //Si no hay sesion iniciada  o esta iniciada pero no es Admin, le llevamos a la pagina de registro
	    if (usuario == null || !usuario.isAdmin()) {
	    	mensajeCDI.setMessage("La operación requiere permisos de administrador.");
	    	mensajeCDI.setRole("alert-danger");
	    	return "index.jsp"; //en el index esta el logueo
	    }
	    Integer id = Integer.parseInt(request.getParameter("id"));//Nos pasan el id del producto
		service.eliminarProducto(id); //Eliminamos el producto
		mensajeCDI.setMessage("Se ha eliminado el producto correctamente");
		mensajeCDI.setRole("alert-success");
		return "productos.jsp"; //retornamos a la pagina
	}

	private String modificarProducto(HttpServletRequest request, HttpServletResponse response) {
		//Verificamos sesion
		HttpSession sesion = request.getSession();
	    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
	    //Si no hay sesion iniciada  o esta iniciada pero no es Admin, le llevamos a la pagina de registro
	    if (usuario == null || !usuario.getIsAdmin()) {
	    	mensajeCDI.setMessage("La operación requiere permisos de administrador.");
	    	mensajeCDI.setRole("alert-danger");
	    	return "index.jsp"; //en el index esta el logueo
	    }
	    
	    String strIdProducto = request.getParameter("id");
	    Integer idProducto = Integer.parseInt(strIdProducto);
	    Producto producto = service.getProducto(idProducto);
	    if(producto == null) {
	    	mensajeCDI.setMessage("Introduzca un id que exista");
			mensajeCDI.setRole("alert-danger");
			return "productos.jsp";
	    }
	    
	}

	private String nuevoProducto(HttpServletRequest request, HttpServletResponse response) {
		//Verificamos sesion
		HttpSession sesion = request.getSession();
	    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
	    //Si no hay sesion iniciada  o esta iniciada pero no es Admin, le llevamos a la pagina de registro
	    if (usuario == null || !usuario.getIsAdmin()) {
	    	mensajeCDI.setMessage("La operación requiere permisos de administrador.");
	    	mensajeCDI.setRole("alert-danger");
	    	return "index.jsp"; //en el index esta el logueo
	    }
		
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
		//AÑadimos imagen del producto
		String uploadPath = getServletContext().getRealPath("") + File.separator + "imagenes";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();
		String fileName = "";
		Part part;
		try {
			part = request.getPart("imagen");
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
		if (! fileName.isBlank()) {
			producto.setImagen(fileName);
		}
		
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
