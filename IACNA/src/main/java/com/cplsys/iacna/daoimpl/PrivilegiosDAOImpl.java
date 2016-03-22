package com.cplsys.iacna.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsys.iacna.dao.PrivilegiosDAO;
import com.cplsys.iacna.domain.Privilegio;
import com.cplsys.iacna.domain.Usuario;

@Repository
public class PrivilegiosDAOImpl extends HibernateDaoSupport implements
		PrivilegiosDAO {

	@Autowired
	public void init(final SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void save(Privilegio privilegio) {
		getHibernateTemplate().save(privilegio);
	}
	@Override
	@Transactional
	public List<Privilegio> getPrivilegio(int idUsuario) {
		List<Privilegio> Privilegios = getHibernateTemplate().find("from Privilegio as a where a.idUsuario = ?", idUsuario);
		return Privilegios != null ? Privilegios : null;
	}

}
