package org.zabalburu.recyclon.dao.pedido;

import java.util.List;

import org.zabalburu.recyclon.modelo.Pedido;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped //Para que haya una única instancia del DAOImpl
@Transactional //Indica que los metodos se hacen dentro de una transacción, si ocurre un error se hace rollback
public class PedidoDAOImpl implements PedidoDAO {

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
	public Pedido nuevoPedido(Pedido nuevo) {
		em.persist(nuevo);
		return nuevo;
	}

	@Override
	public Pedido modificarPedido(Pedido modificado) {
		return em.merge(modificado);
	}
	
	@Override
	public void eliminarPedido(Integer id) {
		Pedido eliminar = em.find(Pedido.class, id); //EntityManager busca dentro de la clase Pedido el id que se quiere eliminar
		if (eliminar != null) { //Comprueba que existe el id
			em.remove(eliminar); //EntityManager elimina ese registro
		}
	}
	
	@Override
	public Pedido getPedido(Integer id) {
//		return em.find(Pedido.class, id);
		Query q = em.createQuery(
				"""
				Select p From Pedido p
				Join Fetch p.lineasPedido
				Where p.id=:id		
				"""
			);
			q.setParameter("id", id); //Aqui se asigna el id de la categoria que se usa en la consulta
			Pedido p = null;
			try {
				p = (Pedido) q.getSingleResult();
			} catch (NoResultException ex) {}
			return p;
	}

	@Override
	public List<Pedido> getPedidos() {
		//No es SQL es JPQL, trabaja con entidades @Entity
		//Al escribir la consulta hay que escribir el nombre de la clase, no el de la tabla
//		Query q = em.createQuery(
		TypedQuery<Pedido> q = em.createQuery( //Con typedQuery se le indica al compilador el tipo de lista que va a devolver (Pedido)
		"""
		SELECT p
			FROM Pedido p
			ORDER BY p.idPedido
		""", Pedido.class);
		return q.getResultList();
	}
}
