package com.cplsys.iacna.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsys.iacna.dao.IngredienteDAO;
import com.cplsys.iacna.domain.Ingrediente;

@Repository
public class IngredienteDAOImpl extends HibernateDaoSupport implements
		IngredienteDAO {

	@Autowired
	public void seSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	@Transactional
	public void saveIngrediente(Ingrediente ingrediente) {
		getHibernateTemplate().saveOrUpdate(ingrediente);

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Ingrediente> getAllIngredientes() {
		return getHibernateTemplate().find("from Ingrediente");
	}

	@Override
	@Transactional
	public Ingrediente getIngrediente(Ingrediente ingrediente) {
		return null;
	}

	@Override
	@Transactional
	public void deleteIngrediente(Ingrediente ingrediente) {
		getHibernateTemplate().delete(ingrediente);
	}

}
