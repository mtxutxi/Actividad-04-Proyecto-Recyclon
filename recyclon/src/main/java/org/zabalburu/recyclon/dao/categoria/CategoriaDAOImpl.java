package org.zabalburu.recyclon.dao.categoria;

import org.zabalburu.recyclon.modelo.Categoria;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
}
