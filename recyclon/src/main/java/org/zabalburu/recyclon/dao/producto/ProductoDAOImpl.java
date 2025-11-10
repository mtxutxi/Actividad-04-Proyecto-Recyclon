package org.zabalburu.recyclon.dao.producto;

import java.util.List;

import org.zabalburu.recyclon.modelo.Producto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped //Para que haya una única instancia del DAOImpl
@Transactional //Indica que los metodos se hacen dentro de una transacción, si ocurre un error se hace rollback
public class ProductoDAOImpl implements ProductoDAO {

	//Sirve para inyectar una instancia de EntityManager (EntityManager tiene metodos que ejecuta querys sobre la BBDD)
	/*Métodos de EntityManager*/
	// em.find() -> Buscar
	// em.persist() -> Guardar
	// em.merge() -> Actualizar
	// em.remove() -> Eliminar
	// em.createQuery() -> Ejecutar querys
	@PersistenceContext(unitName = "recyclonPU") //Se le indica el nombre de la unidad de persistencia (persistence.xml)
	private EntityManager em;
	
	@Override
	public Producto nuevoProducto(Producto nuevo) {
		em.persist(nuevo);
		return nuevo;
	}

	@Override
	public Producto modificarProducto(Producto modificado) {
		return em.merge(modificado);
	}

	@Override
	public void eliminarProducto(Integer id) {
		Producto eliminar = em.find(Producto.class, id); //EntityManager busca dentro de la clase Producto el id que se quiere eliminar
		if (eliminar != null) { //Comprueba que existe el id
			em.remove(eliminar); //EntityManager elimina ese registro
		}
	}

	@Override
	public Producto getProducto(Integer id) {
		return em.find(Producto.class, id); //EntityManager busca dentro de la clase Producto el id que se quiere devolver
	}

	@Override
	public List<Producto> getProductosPorCategoria() { //Devuelve los productos ordenados por categoria y dentro de cada categoria por precio
		//No es SQL es JPQL, trabaja con entidades @Entity
				//Al escribir la consulta hay que escribir el nombre de la clase, no el de la tabla
//				Query q = em.createQuery(
				TypedQuery<Producto> q = em.createQuery( //Con typedQuery se le indica al compilador el tipo de lista que va a devolver (Producto)
				"""
				SELECT p
					FROM Producto p
					ORDER BY p.categoria.tipo, p.precio
				""", Producto.class);
				return q.getResultList();
	}

	@Override
	public List<Producto> getCategoriaProducto(Integer idCategoria) { //Devuelve una lista de los productos de una categoria
		//No es SQL es JPQL, trabaja con entidades @Entity
		//Al escribir la consulta hay que escribir el nombre de la clase, no el de la tabla
//		Query q = em.createQuery(
		TypedQuery<Producto> q = em.createQuery( //Con typedQuery se le indica al compilador el tipo de lista que va a devolver (Producto)
		"""
		SELECT p
			FROM Producto p
			WHERE p.categoria.idCategoria = :idCategoria
			ORDER BY p.precio
		""", Producto.class);
		q.setParameter("idCategoria", idCategoria); //Aqui se asigna el id de la categoria que se usa en la consulta
		return q.getResultList();
	}
	
	@Override
	public List<Producto> buscarProducto(String busqueda) { //Devuelve una lista de los productos con un LIKE
		//No es SQL es JPQL, trabaja con entidades @Entity
				//Al escribir la consulta hay que escribir el nombre de la clase, no el de la tabla
//				Query q = em.createQuery(
				TypedQuery<Producto> q = em.createQuery( //Con typedQuery se le indica al compilador el tipo de lista que va a devolver (Producto)
				"""
				SELECT p
					FROM Producto p
					WHERE LOWER(p.nombre) LIKE :busqueda
					ORDER BY p.precio
				""", Producto.class);
				q.setParameter("busqueda", "%%%s%%".formatted(busqueda.toLowerCase())); // %busqueda%
				return q.getResultList();
	}
}
