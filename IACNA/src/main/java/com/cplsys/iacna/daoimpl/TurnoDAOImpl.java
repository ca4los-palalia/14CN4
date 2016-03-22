package com.cplsys.iacna.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsys.iacna.dao.TurnoDAO;
import com.cplsys.iacna.domain.Turno;

@Repository
public class TurnoDAOImpl extends HibernateDaoSupport implements TurnoDAO {

	@Autowired
	public void init(final SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void saveTurno(Turno turno) {
		getHibernateTemplate().saveOrUpdate(turno);
		
	}

	@Override
	public List<Turno> getAll() {
		return getHibernateTemplate().find("from Turno");
	}

	@Override
	@Transactional
	public void deleteTurno(Turno turno) {
		getHibernateTemplate().delete(turno);
	}	

}
