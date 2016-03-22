package com.cplsys.iacna.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsys.iacna.dao.OrigenDeDatosDAO;
import com.cplsys.iacna.dao.TurnoDAO;
import com.cplsys.iacna.domain.OrigenDeDatos;
import com.cplsys.iacna.domain.Turno;

@Repository
public class OrigenDeDatosDAOImpl extends HibernateDaoSupport implements OrigenDeDatosDAO {

	@Autowired
	public void init(final SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void saveOrigenDeDatos(OrigenDeDatos origenDeDatos)
			throws DataAccessException {
		getHibernateTemplate().saveOrUpdate(origenDeDatos);
	}

	@Override
	public List<OrigenDeDatos> getAll() throws DataAccessException {
		return getHibernateTemplate().find("from OrigenDeDatos");
	}

	@Override
	@Transactional
	public void deleteOrigenDeDatos(OrigenDeDatos origenDeDatos)
			throws DataAccessException {
		getHibernateTemplate().delete(origenDeDatos);
	}

}