package org.zabalburu.recyclon.dao.usuario;

import org.zabalburu.recyclon.modelo.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class UsuarioDAOImpl implements UsuarioDAO {
	
	@PersistenceContext(unitName = "recyclonPU")
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
	public Usuario buscarUsuarioPorEmail(String email) {
		try {
			TypedQuery<Usuario> q = em.createQuery(
				"""
				SELECT u
				FROM Usuario u
				WHERE u.email = :email
				""", Usuario.class);
			q.setParameter("email", email);
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}