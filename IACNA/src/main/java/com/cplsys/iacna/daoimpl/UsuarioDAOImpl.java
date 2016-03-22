package com.cplsys.iacna.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsys.iacna.dao.UsuarioDAO;
import com.cplsys.iacna.domain.Usuario;

@Repository
public class UsuarioDAOImpl extends HibernateDaoSupport implements UsuarioDAO {

	@Autowired
	public void init(final SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public Usuario getUsuario(Usuario usuario) {
		return new Usuario();
	}

	@Override
	@Transactional
	public void saveUser(Usuario usuario) {
		getHibernateTemplate().saveOrUpdate(usuario);
	}

	@Override
	@Transactional
	public Usuario getUsuario(String userName, String password) {		
		List<Usuario> usuario = getHibernateTemplate().find("from Usuario as u where " +
				"u.userName = ? and u.password = ?", userName, password);
		return usuario != null && !usuario.isEmpty() ? usuario.get(0) : null;
	}

	@Override
	@Transactional
	public List<Usuario> getAll() {
		List<Usuario> usuarios = getHibernateTemplate().find("from Usuario");
		return usuarios != null ? usuarios : null;
	}

	@Override
	public void newUser(Usuario usuario) {
		getHibernateTemplate().save(usuario);		
	}

}
