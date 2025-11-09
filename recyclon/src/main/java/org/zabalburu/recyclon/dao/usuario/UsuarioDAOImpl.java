package org.zabalburu.recyclon.dao.usuario;

import org.zabalburu.recyclon.modelo.Usuario;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped //Para que haya una única instancia del DAOImpl
@Transactional //Indica que los metodos se hacen dentro de una transacción, si ocurre un error se hace rollback
public class UsuarioDAOImpl implements UsuarioDAO {

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
	public Usuario nuevoUsuario(Usuario nuevo) {
		em.persist(nuevo);
		return nuevo;
	}

	@Override
	public Usuario modificarUsuario(Usuario modificado) {
		return em.merge(modificado);
	}
}
