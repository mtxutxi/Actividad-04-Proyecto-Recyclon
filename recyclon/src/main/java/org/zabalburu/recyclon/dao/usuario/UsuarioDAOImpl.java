package org.zabalburu.recyclon.dao.usuario;

import org.zabalburu.recyclon.modelo.Usuario;
import org.zabalburu.recyclon.util.PasswordUtil;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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
	
	@Override
	public Usuario login(String email, String password) {
		try {
			TypedQuery<Usuario> q = em.createQuery( //Con typedQuery se le indica al compilador el tipo de lista que va a devolver (Usuario)
			"""
			SELECT u
				FROM Usuario u
				WHERE email = :email
			""", Usuario.class);
			q.setParameter("email", email); //Aqui se asigna el email que se usa en la consulta
			Usuario u = null;
			u = q.getSingleResult(); //Devuelve el resultado de la consulta
			String hash = u.getContrasenaHash(); //Almacena la contraseña con hash de la BBDD
			if (PasswordUtil.verifyPassword(password, hash)) { //Compara la contraseña que ha introducido el user con la de la BBDD
					return u; //Si coincide devuelve el resultado de la consulta
			}
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		return null;
	}
}
