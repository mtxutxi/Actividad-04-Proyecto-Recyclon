package org.zabalburu.recyclon.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zabalburu.recyclon.cdi.MensajeCDI;
import org.zabalburu.recyclon.modelo.Cesta;
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
	
	@Inject
	private Cesta cesta;
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
        HttpSession sesion = request.getSession();
        String pagina = null;
        String accion = request.getParameter("accion");
        
        if(accion == null) {
            request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
            pagina = "productos.jsp";
        } else {
            System.out.println("ACCION: " + accion);
            
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
                case "anadiralcarrito":
                    pagina = añadirAlCarrito(request, response);
                    break;
                case "vercarrito":
                    pagina = verCarrito(request, response);
                    break;
                case "eliminardelcarrito":
                    pagina = eliminarDelCarrito(request, response);
                    break;
            }
        }
        
        System.out.println("PAGINA FINAL: " + pagina); 
        request.getRequestDispatcher(pagina).forward(request, response);
    }

	private String eliminarDelCarrito(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        Integer idProducto = Integer.parseInt(request.getParameter("id"));
	        cesta.eliminarProducto(idProducto);
	        
	        mensajeCDI.setMessage("Producto eliminado del carrito");
	        mensajeCDI.setRole("alert-success");
	        
	    } catch (NumberFormatException e) {
	        mensajeCDI.setMessage("Error al eliminar producto");
	        mensajeCDI.setRole("alert-danger");
	    }
	    
	    return "cesta.jsp";
	}
	
	private String verCarrito(HttpServletRequest request, HttpServletResponse response) {
	    // crea lista para enviar al JSP con producto + cantidad
	    List<Map<String, Object>> productosCarrito = new ArrayList<>();  
	    
	    // por cada producto en la cesta, obtener los datos completos
	    for (Map.Entry<Integer, Integer> entry : cesta.getProductos().entrySet()) {
	        Integer idProducto = entry.getKey();
	        Integer cantidad = entry.getValue();
	        
	        Producto producto = service.getProducto(idProducto);
	        
	        if (producto != null) {
	            Map<String, Object> item = new HashMap<>();
	            item.put("producto", producto);
	            item.put("cantidad", cantidad);
	            productosCarrito.add(item);
	        }
	    }
	    
	    request.setAttribute("productosCarrito", productosCarrito);
	    return "cesta.jsp";
	}
	
	private String añadirAlCarrito(HttpServletRequest request, HttpServletResponse response) {
	    System.out.println("AÑADIR CARRITO");
	    HttpSession sesion = request.getSession();

	    
	    try {

	        String idParam = request.getParameter("id");
	        String cantParam = request.getParameter("cantidad");
	       
	        
	        Integer idProducto = Integer.parseInt(idParam);
	        Integer cantidad = Integer.parseInt(cantParam);
	        

	        Producto producto = service.getProducto(idProducto);
	        
	        if (producto == null) {
	            mensajeCDI.setMessage("Producto no encontrado");
	            mensajeCDI.setRole("alert-danger");
	            request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
	            return "productos.jsp";
	        }
	        
	        
	        if (producto.getStock() < cantidad) {
	            System.out.println("   - Stock insuficiente");
	            mensajeCDI.setMessage("No hay suficiente stock disponible");
	            mensajeCDI.setRole("alert-danger");
	            request.setAttribute("producto", producto);
	            return "detalleproducto.jsp";
	        }
	        
	        System.out.println("Verificando cesta...");
	        if (cesta == null) {
	            System.out.println("ERROR-Cesta NULL");
	            mensajeCDI.setMessage("Error: Carrito no disponible");
	            mensajeCDI.setRole("alert-danger");
	            request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
	            return "productos.jsp";
	        }
	        

	        cesta.añadirProducto(idProducto, cantidad);
	        
	        System.out.println("añadido exitosamente");
	        
	        sesion.setAttribute("mensaje", "Producto añadido al carrito");
	        
	        mensajeCDI.setMessage("Producto añadido al carrito");
	        mensajeCDI.setRole("alert-success");
	        request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
	        
	        System.out.println("FIN AÑADIR CARRITO");
	        return "productos.jsp";
	        
	    } catch (Exception e) {
	        System.out.println("ERR AÑADIR CARRITO");
	        System.out.println("Tipo de error: " + e.getClass().getName());
	        System.out.println("Mensaje: " + e.getMessage());
	        e.printStackTrace();
	        
	        mensajeCDI.setMessage("Error: " + e.getMessage());
	        mensajeCDI.setRole("alert-danger");
	        request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
	        return "productos.jsp";
	    }
	}

	private String buscarProducto(HttpServletRequest request, HttpServletResponse response) {
		String producto = request.getParameter("producto"); //Aqui le llegua lo que escribe el ususario en el recuadro de buscar
		if(producto == null || producto.trim().isEmpty()) {//si producto es null, o quitando los espacios del principio y final esta vacio
			mensajeCDI.setMessage("Debes introducir un termino de busqueda valido");
			mensajeCDI.setRole("alert-danger");
			request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
			return "productos.jsp";
		}
		List<Producto> listaProducto = service.buscarProducto(producto);
		if(listaProducto.isEmpty()) {
			mensajeCDI.setMessage("No hay ninguna coincidencia con su busqueda");
			mensajeCDI.setRole("alert-danger");
			request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
		}else {
			mensajeCDI.setMessage("Se han encontrado" + listaProducto.size() + " resultados");
			mensajeCDI.setRole("alert-danger");
			request.setAttribute("productosporcategoria", listaProducto);
		}
		return "productos.jsp";
	}

	private String getCategoriaProducto(HttpServletRequest request, HttpServletResponse response) {
		String strIdCategoria = request.getParameter("id");
		Integer idCategoria = Integer.parseInt(strIdCategoria);
		List<Producto> listaProductos = service.getCategoriaProducto(idCategoria);
		request.setAttribute("productosporcategoria", listaProductos);
		return "productos.jsp";
	}

	private String getProducto(HttpServletRequest request, HttpServletResponse response) {
		String strIdProducto = request.getParameter("id");
		Integer idProducto = Integer.parseInt(strIdProducto);
		Producto producto = service.getProducto(idProducto);
		request.setAttribute("producto", producto);//Ojo! que sin esto el jsp no lo ve!!!!!
		return "detalleproducto.jsp";
	}

	private String eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
		//Verificamos sesion
		HttpSession sesion = request.getSession();
	    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
	    //Si no hay sesion iniciada  o esta iniciada pero no es Admin, le llevamos a la pagina de registro
	    if (usuario == null || !usuario.getIsAdmin()) {
	    	mensajeCDI.setMessage("La operación requiere permisos de administrador.");
	    	mensajeCDI.setRole("alert-danger");
	    	return "index.jsp"; //en el index esta el logueo
	    }
	    Integer id = Integer.parseInt(request.getParameter("id"));//Nos pasan el id del producto
		service.eliminarProducto(id); //Eliminamos el producto
		mensajeCDI.setMessage("Se ha eliminado el producto correctamente");
		mensajeCDI.setRole("alert-success");
		request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
		return "producto.jsp"; //retornamos a la pagina
	}

	private String modificarProducto(HttpServletRequest request, HttpServletResponse response) {
		//Verificamos la sesionsesion
		HttpSession sesion = request.getSession();
	    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
	    //Si no hay sesion iniciada  o esta iniciada pero no es Admin, le llevamos a la pagina de registro
	    if (usuario == null || !usuario.getIsAdmin()) {
	    	mensajeCDI.setMessage("La operación requiere permisos de administrador.");
	    	mensajeCDI.setRole("alert-danger");
	    	return "index.jsp"; //en el index esta el logueo
	    }
	    
	    //Primero obtenemos los datos del formulario y validamos que exista el producto por su id
	    String strIdProducto = request.getParameter("id");
	    Integer idProducto = Integer.parseInt(strIdProducto);
	    Producto producto = service.getProducto(idProducto);
	    if(producto == null) { //Si es null
	    	mensajeCDI.setMessage("Introduzca un id que exista");
			mensajeCDI.setRole("alert-danger");
			return "producto.jsp";
	    }
	    //Si existe, te da el producto y continuamos
	    String nombre = request.getParameter("nombre");
	    String descripcion = request.getParameter("descripcion");
	    String strPrecio = request.getParameter("precio");
	    String strStock = request.getParameter("stock");
	    Double precio = Double.parseDouble(strPrecio);
	    Integer stock = Integer.parseInt(strStock);
	    
	    //Ahora le pasamos los datos del formulario al producto
	    producto.setNombre(nombre);
	    producto.setDescripcion(descripcion);
	    producto.setPrecio(precio);
	    producto.setStock(stock);
	    
	    //Lo guardamos en la BBDD
	    service.modificarProducto(producto);
	    
	    
	    //Si tenemos exito
	    if(producto != null) {
	        mensajeCDI.setMessage("Producto modificado correctamente");
	        mensajeCDI.setRole("alert-success");
	    } else {
	        mensajeCDI.setMessage("Error al modificar el producto");
	        mensajeCDI.setRole("alert-danger");
	    }
	    request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
	    return "productos.jsp";
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
		request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
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
