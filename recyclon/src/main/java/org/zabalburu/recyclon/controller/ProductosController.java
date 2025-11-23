package org.zabalburu.recyclon.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zabalburu.recyclon.cdi.MensajeCDI;
import org.zabalburu.recyclon.modelo.Categoria;
import org.zabalburu.recyclon.modelo.Cesta;
import org.zabalburu.recyclon.modelo.Producto;
import org.zabalburu.recyclon.modelo.Usuario;
import org.zabalburu.recyclon.service.GestionService;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
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
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // 5MB max
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
		// TODO Auto-generated method stub
				HttpSession sesion = request.getSession();
				String pagina = null; // declaramos e inicializamos la variable que almaceanara la ruta del jsp al que se redirigira
				String accion = request.getParameter("accion"); //obtiene el parametro accion de la URL y lo vuelca en la variable accion
				/*Los datos que se cargan automaticamente en la pagina y no hacen operaciones van en el if, si hacen algo o necesitan 
				  interaccion del ususario van al switch por que tenemos sus metodos fuera*/
				if(accion == null) {//esto seria que no hacen operaciones, solo muestran los datos sin más
					request.setAttribute("categorias", service.getCategorias());
					request.setAttribute("productosporcategoria", service.getProductosPorCategoria());// obtiene los productosPorCat usando el metodo del service y los guarda en pedidos para que esten disponibles en el jsp
					pagina = "productos.jsp";
				}else {/*Ahora vamos a meter las acciones que requieren intervención del usuario, que no son de mostrar y listo.*/
					switch (accion.toLowerCase()) {
						case "nuevoproducto": 
							request.setAttribute("categorias", service.getCategorias());
							pagina = "formularioproducto.jsp";
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
		                case "vercarrito":
		                    pagina = verCarrito(request, response);
		                    break;
						case "stockbajo":
							pagina = stockBajo(request, response);
							break;
						case "getprecioasc":
							pagina = getprecioasc(request, response);
							break;
						case "getpreciodesc":
							pagina = getpreciodesc(request, response);
							break;
					}
				}
				request.getRequestDispatcher(pagina).forward(request, response);
	}

	private String getpreciodesc(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("categorias", service.getCategorias());
		request.setAttribute("productosporcategoria", service.getPrecioDesc());
		return "productos.jsp";
	}

	private String getprecioasc(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("categorias", service.getCategorias());
		request.setAttribute("productosporcategoria", service.getPrecioAsc());
		return "productos.jsp";
	}

	private String stockBajo(HttpServletRequest request, HttpServletResponse response) {
		Integer stock = 5;
		request.setAttribute("categorias", service.getCategorias());
		request.setAttribute("productosporcategoria", service.getProductosStockBajo(stock));
		return "productos.jsp";
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
		request.setAttribute("categorias", service.getCategorias());
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
		return "productos.jsp"; //retornamos a la pagina
	}

	private String modificarProducto(HttpServletRequest request, HttpServletResponse response) {
		String strIdProducto = request.getParameter("id");
		Integer idProducto = Integer.parseInt(strIdProducto);
	    Producto producto = service.getProducto(idProducto);
	    request.setAttribute("producto", producto);
	    request.setAttribute("categorias", service.getCategorias());
	    return "formularioproducto.jsp";
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pagina = null;
		String accion = request.getParameter("accion");
		if(accion == null) {
			request.setAttribute("categorias", service.getCategorias());
			pagina = "formularioproducto.jsp";
		}else {
			switch (accion.toLowerCase()) {
				case "productoformulario":
					pagina = productoFormulario(request); //Método addToCesta
					break;
				case "productonuevo":
					pagina = crearProducto(request);
					break;
				case "anadiralcarrito":
					pagina = añadirCarrito(request, response);
					break;
				case "eliminardelcarrito":
					pagina = eliminarCarrito(request, response);
					break;
			}
		}
		request.getRequestDispatcher(pagina).forward(request, response);			
	}

	
	private String eliminarCarrito(HttpServletRequest request, HttpServletResponse response) {
		try {
			Integer idProducto = Integer.parseInt(request.getParameter("id"));
			cesta.eliminarProducto(idProducto);
			
			mensajeCDI.setMessage("Producto eliminado del carrito");
			mensajeCDI.setRole("alert-success");
			
		} catch (NumberFormatException e) {
			mensajeCDI.setMessage("Error al eliminar producto");
			mensajeCDI.setRole("alert-danger");
		}
		
		// Recargar carrito
		return verCarrito(request, response);
	}

	private String añadirCarrito(HttpServletRequest request, HttpServletResponse response) {
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
				mensajeCDI.setMessage("No hay suficiente stock disponible");
				mensajeCDI.setRole("alert-danger");
				request.setAttribute("producto", producto);
				return "detalleproducto.jsp";
			}
			
			if (cesta == null) {
				mensajeCDI.setMessage("Error: Carrito no disponible");
				mensajeCDI.setRole("alert-danger");
				request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
				return "productos.jsp";
			}
			
			cesta.añadirProducto(idProducto, cantidad);
			
			mensajeCDI.setMessage("Producto añadido al carrito");
			mensajeCDI.setRole("alert-success");
			request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
			
			return "productos.jsp";
			
		} catch (Exception e) {
			mensajeCDI.setMessage("Error: " + e.getMessage());
			mensajeCDI.setRole("alert-danger");
			request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
			return "productos.jsp";
		}
	}

	private String crearProducto(HttpServletRequest request) {
		HttpSession sesion = request.getSession();
	    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
	    
	    if (usuario == null || !usuario.getIsAdmin()) {
	    	mensajeCDI.setMessage("La operación requiere permisos de administrador.");
	    	mensajeCDI.setRole("alert-danger");
	    	return "index.jsp";
	    }
		
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		String categoriaId = request.getParameter("categoria");
		String strPrecio = request.getParameter("precio");
		String strStock = request.getParameter("stock");
		
		Double precio = 0.0;
		Integer stock = 0;
		try {
			precio = Double.parseDouble(strPrecio);
			stock = Integer.parseInt(strStock);
		} catch(NumberFormatException e) {
			mensajeCDI.setRole("alert-danger");
			mensajeCDI.setMessage("Introduzca valores numéricos válidos");
			request.setAttribute("categorias", service.getCategorias());
			return "formularioproducto.jsp";
		}
		
		if(precio <= 0) {
			mensajeCDI.setRole("alert-danger");
			mensajeCDI.setMessage("Introduzca un precio mayor que cero");
			request.setAttribute("categorias", service.getCategorias());
			return "formularioproducto.jsp";
		}
		
		if(stock < 0) {
			mensajeCDI.setRole("alert-danger");
			mensajeCDI.setMessage("El stock no puede ser negativo");
			request.setAttribute("categorias", service.getCategorias());
			return "formularioproducto.jsp";
		}
		
		// Procesar imagen
		String fileName = procesarImagen(request);
		
		// Crear producto
		Categoria categoria = service.getCategoria(Integer.parseInt(categoriaId));
		Producto producto = new Producto();
		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setCategoria(categoria);
		producto.setPrecio(precio);
		producto.setStock(stock);
		if (!fileName.isBlank()) {
			producto.setImagen(fileName);
		}
		
		service.nuevoProducto(producto);
		
		mensajeCDI.setRole("alert-success");
		mensajeCDI.setMessage("¡Producto creado con éxito!");
		request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
		return "productos.jsp";
	}

	private String productoFormulario(HttpServletRequest request) {
		HttpSession sesion = request.getSession();
	    Usuario usuario = (Usuario) sesion.getAttribute("usuario");
	    
	    if (usuario == null || !usuario.getIsAdmin()) {
	    	mensajeCDI.setMessage("La operación requiere permisos de administrador.");
	    	mensajeCDI.setRole("alert-danger");
	    	return "index.jsp";
	    }
	    
	    try {
	    	// Obtener ID del producto
	    	String strIdProducto = request.getParameter("id");
	    	if (strIdProducto == null || strIdProducto.isEmpty()) {
	    		mensajeCDI.setMessage("ID de producto no especificado");
	    		mensajeCDI.setRole("alert-danger");
	    		request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
	    		return "productos.jsp";
	    	}
	    	
	    	Integer idProducto = Integer.parseInt(strIdProducto);
	    	Producto producto = service.getProducto(idProducto);
	    	
	    	if(producto == null) {
	    		mensajeCDI.setMessage("El producto no existe");
	    		mensajeCDI.setRole("alert-danger");
	    		request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
	    		return "productos.jsp";
	    	}
	    	
	    	// Obtener datos del formulario
	    	String nombre = request.getParameter("nombre");
	    	String descripcion = request.getParameter("descripcion");
	    	String strCategoriaId = request.getParameter("categoria");
	    	String strPrecio = request.getParameter("precio");
	    	String strStock = request.getParameter("stock");
	    	
	    	Double precio = Double.parseDouble(strPrecio);
	    	Integer stock = Integer.parseInt(strStock);
	    	Integer categoriaId = Integer.parseInt(strCategoriaId);
	    	
	    	if(precio <= 0) {
	    		mensajeCDI.setMessage("Introduzca un precio mayor que cero");
	    		mensajeCDI.setRole("alert-danger");
	    		request.setAttribute("producto", producto);
	    		request.setAttribute("categorias", service.getCategorias());
	    		return "formularioproducto.jsp";
	    	}
	    	
	    	if(stock < 0) {
	    		mensajeCDI.setMessage("El stock no puede ser negativo");
	    		mensajeCDI.setRole("alert-danger");
	    		request.setAttribute("producto", producto);
	    		request.setAttribute("categorias", service.getCategorias());
	    		return "formularioproducto.jsp";
	    	}
	    	
	    	// Actualizar producto
	    	producto.setNombre(nombre);
	    	producto.setDescripcion(descripcion);
	    	producto.setPrecio(precio);
	    	producto.setStock(stock);
	    	
	    	// Actualizar categoría
	    	Categoria categoria = service.getCategoria(categoriaId);
	    	producto.setCategoria(categoria);
	    	
	    	// Procesar nueva imagen si existe
	    	String fileName = procesarImagen(request);
	    	if (!fileName.isBlank()) {
	    		producto.setImagen(fileName);
	    	}
	    	
	    	// Guardar cambios
	    	service.modificarProducto(producto);
	    	
	    	mensajeCDI.setMessage("Producto modificado correctamente");
	    	mensajeCDI.setRole("alert-success");
	    	request.setAttribute("productosporcategoria", service.getProductosPorCategoria());
	    	return "productos.jsp";
	    	
	    } catch (NumberFormatException e) {
	    	mensajeCDI.setMessage("Valores numéricos inválidos");
	    	mensajeCDI.setRole("alert-danger");
	    	request.setAttribute("categorias", service.getCategorias());
	    	return "formularioproducto.jsp";
	    }
	}
	
	private String procesarImagen(HttpServletRequest request) {
		String uploadPath = getServletContext().getRealPath("") + File.separator + "imagenes";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		String fileName = "";
		try {
			Part part = request.getPart("imagen");
			if (part != null && part.getSize() > 0) {
				fileName = part.getSubmittedFileName();
				if (fileName != null && !fileName.isEmpty()) {
					part.write(uploadPath + File.separator + fileName);
				}
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}
}
