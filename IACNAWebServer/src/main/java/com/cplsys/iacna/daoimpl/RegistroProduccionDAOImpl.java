package com.cplsys.iacna.daoimpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsys.iacna.dao.RegistroProduccionDAO;
import com.cplsys.iacna.dao.UsuarioDAO;
import com.cplsys.iacna.domain.Bascula;
import com.cplsys.iacna.domain.RegistroProduccion;
import com.cplsys.iacna.domain.Usuario;

@Repository
public class RegistroProduccionDAOImpl extends HibernateDaoSupport implements RegistroProduccionDAO {

	@Autowired
	public void init(final SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void saveRegistroProduccion(RegistroProduccion produccion)
			throws DataAccessException {
		getHibernateTemplate().save(produccion);
	}

	@Override
	@Transactional
	public List<RegistroProduccion> getAll() throws DataAccessException {
		List<RegistroProduccion> list = getHibernateTemplate().find("from RegistroProduccion");
		return list != null && !list.isEmpty() ? list : new ArrayList<RegistroProduccion>();
	}

	@Override
	@Transactional
	public List<RegistroProduccion> getRegistrosProduccionFromTo(Calendar from, Calendar to) throws DataAccessException {
		List<RegistroProduccion> list = getHibernateTemplate().find(
				"from RegistroProduccion as d where d.fechaRegistro >= ? and fecharegistro <= ?", from, to);
		return list != null && !list.isEmpty() ? list : new ArrayList<RegistroProduccion>();
	}

	@Override
	@Transactional
	public void deleteRegistroProduccion(RegistroProduccion registroProduccion)
			throws DataAccessException {
		getHibernateTemplate().delete(registroProduccion);
	}

	@Override
	public List<RegistroProduccion> getRegistroProduccionByTurno(String turno)
			throws DataAccessException {
		return getHibernateTemplate().find("from RegistroProduccion as r where turno = ?", turno);
	}
	
	public List<RegistroProduccion> getRegistroProduccionByNombreFormula(String nombreFormula)
			throws DataAccessException {
		return getHibernateTemplate().find("from RegistroProduccion as r where formula = ?", nombreFormula);
	}
	

}
