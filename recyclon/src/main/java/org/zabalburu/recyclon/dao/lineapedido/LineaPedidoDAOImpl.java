package org.zabalburu.recyclon.dao.lineapedido;

import org.zabalburu.recyclon.modelo.LineaPedido;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped //Para que haya una única instancia del DAOImpl
@Transactional //Indica que los metodos se hacen dentro de una transacción, si ocurre un error se hace rollback
public class LineaPedidoDAOImpl implements LineaPedidoDAO {

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
	public LineaPedido nuevaLineaPedido(LineaPedido nueva) {
		em.persist(nueva);
		return nueva;
	}

	@Override
	public LineaPedido modificarLineaPedido(LineaPedido modificada) {
		return em.merge(modificada);
	}

	@Override
	public void eliminarLineaPedido(Integer id) {
		LineaPedido eliminar = em.find(LineaPedido.class, id); //EntityManager busca dentro de la clase LineaPedido el id que se quiere eliminar
		if (eliminar != null) { //Comprueba que existe el id
			em.remove(eliminar); //EntityManager elimina ese registro
		}
	}

	@Override
	public LineaPedido getLineaPedido(Integer id) {
		return em.find(LineaPedido.class, id); //EntityManager busca dentro de la clase LineaPedido el id que se quiere devolver
	}
}
