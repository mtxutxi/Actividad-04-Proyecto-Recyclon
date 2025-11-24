package org.zabalburu.recyclon.dao.categoria;

import java.util.List;

import org.zabalburu.recyclon.modelo.Categoria;
import org.zabalburu.recyclon.modelo.Pedido;
import org.zabalburu.recyclon.modelo.Producto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped //Para que haya una única instancia del DAOImpl
@Transactional //Indica que los metodos se hacen dentro de una transacción, si ocurre un error se hace rollback
public class CategoriaDAOImpl implements CategoriaDAO {

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
	public Categoria nuevaCategoria(Categoria nueva) {
		em.persist(nueva);
		return nueva;
	}

	@Override
	public Categoria modificarCategoria(Categoria modificada) {
		return em.merge(modificada);
	}

	@Override
	public void eliminarCategoria(Integer id) {
		//Podría llegar a fallar (Según Copilot)
//		Categoria eliminar = new Categoria(); //Se crea una nueva Categoria
//		eliminar.setIdCategoria(id); //Le asignamos el id que se quiere eliminar
//		em.remove(eliminar); //EntityManager elimina la Categoria
		/*Método correcto según Copilot*/
		Categoria eliminar = em.find(Categoria.class, id); //EntityManager busca dentro de la clase Categoria el id que se quiere eliminar
		if (eliminar != null) { //Comprueba que existe el id
			em.remove(eliminar); //EntityManager elimina ese registro
		}
	}
	
	@Override
	public List<Categoria> getCategorias() {
		//No es SQL es JPQL, trabaja con entidades @Entity
		//Al escribir la consulta hay que escribir el nombre de la clase, no el de la tabla
		TypedQuery<Categoria> q = em.createQuery( //Con typedQuery se le indica al compilador el tipo de lista que va a devolver (Categoria)
		"""
		SELECT c
			FROM Categoria c
		""", Categoria.class);
		return q.getResultList();
	}
	
	@Override
	public Categoria getCategoria(Integer id) {
		return em.find(Categoria.class, id);
	}
}
