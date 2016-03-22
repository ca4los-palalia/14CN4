package com.cplsys.iacna.daoimpl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsys.iacna.dao.BatchProduccionDAO;
import com.cplsys.iacna.dao.RegistroProduccionDAO;
import com.cplsys.iacna.dao.UsuarioDAO;
import com.cplsys.iacna.domain.BatchProduccion;
import com.cplsys.iacna.domain.RegistroProduccion;
import com.cplsys.iacna.domain.Usuario;

@Repository
public class BatchProduccionDAOImpl extends HibernateDaoSupport implements BatchProduccionDAO {

	@Autowired
	public void init(final SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void saveBatchProduccion(BatchProduccion produccion)
			throws DataAccessException {
		getHibernateTemplate().saveOrUpdate(produccion);
	}

	@Override
	@Transactional
	public List<BatchProduccion> getAll() throws DataAccessException {
		return null;
	}

	@Override
	@Transactional
	public List<BatchProduccion> getBatchProduccionFromTo(Calendar from,
			Calendar to) throws DataAccessException {
		return null;
	}

	@Override
	@Transactional
	public void deleteBatchProduccion(BatchProduccion produccion)
			throws DataAccessException {
		getHibernateTemplate().delete(produccion);
	}

}
