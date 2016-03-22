package com.cplsys.iacna.daoimpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsys.iacna.dao.BatchProduccionDAO;
import com.cplsys.iacna.domain.BatchProduccion;

@Repository
public class BatchProduccionDAOImpl extends HibernateDaoSupport implements BatchProduccionDAO {

	@Autowired
	public void init(final SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void saveBatchProduccion(BatchProduccion produccion) {
		getHibernateTemplate().saveOrUpdate(produccion);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<BatchProduccion> getAll() {
		List<BatchProduccion> list = getHibernateTemplate().find("from BatchProduccion");
		return (List<BatchProduccion>) (list != null && !list.isEmpty() ? list : 
			new ArrayList<BatchProduccion>());
	}

	@Override
	@Transactional
	public List<BatchProduccion> getBatchProduccionFromTo(Calendar from, Calendar to) {
		return null;
	}

	@Override
	@Transactional
	public void deleteBatchProduccion(BatchProduccion produccion) {
		getHibernateTemplate().delete(produccion);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<BatchProduccion> getBatchProduccion(Integer idRegistro) {
		List<BatchProduccion> list = getHibernateTemplate().find("from BatchProduccion");
		return (List<BatchProduccion>) (list != null && !list.isEmpty() ? list : 
			new ArrayList<BatchProduccion>());
	}
	

}
