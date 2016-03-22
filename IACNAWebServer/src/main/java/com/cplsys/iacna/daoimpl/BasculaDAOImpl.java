package com.cplsys.iacna.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsys.iacna.dao.BasculaDAO;
import com.cplsys.iacna.domain.Bascula;

@Repository
public class BasculaDAOImpl extends HibernateDaoSupport implements BasculaDAO {

	@Autowired
	public void init(final SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void saveBascula(Bascula bascula) {
		getHibernateTemplate().saveOrUpdate(bascula);
	}

	@Override
	@Transactional
	public List<Bascula> getAll() {
		List<Bascula> basculas = getHibernateTemplate().find("from Bascula");
		return !basculas.isEmpty() ? basculas : new ArrayList<Bascula>();
	}

	@Override
	@Transactional
	public List<Bascula> getBasculasLibres() {
		return null;
	}

	@Override
	@Transactional
	public void deleteBascula(Bascula bascula) {
		getHibernateTemplate().delete(bascula);
	}

	@Override
	public Bascula getBasculaByName(String name) {
		List<Bascula> bascula = getHibernateTemplate().find("from Bascula as b where nombre = ?", name);
		return !bascula.isEmpty() ? bascula.get(0): null;
	}

}
