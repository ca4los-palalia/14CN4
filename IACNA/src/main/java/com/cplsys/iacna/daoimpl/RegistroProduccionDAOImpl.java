package com.cplsys.iacna.daoimpl;

import java.text.SimpleDateFormat;
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
import com.cplsys.iacna.domain.RegistroProduccion;
import com.cplsys.iacna.utils.IACNAConstants;

@Repository
public class RegistroProduccionDAOImpl extends HibernateDaoSupport implements RegistroProduccionDAO {

	@Autowired
	public void init(final SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void saveRegistroProduccion(RegistroProduccion produccion) {
		getHibernateTemplate().save(produccion);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<RegistroProduccion> getAll(int type) {
		
		Calendar hoy = Calendar.getInstance();		
		hoy.set(Calendar.YEAR, hoy.get(Calendar.YEAR));
		hoy.set(Calendar.MONTH, hoy.get(Calendar.MONTH));
		hoy.set(Calendar.DAY_OF_MONTH, hoy.get(Calendar.DAY_OF_MONTH));
		SimpleDateFormat format = new SimpleDateFormat(
				IACNAConstants.FORMATO_FECHA_BUSQUEDA_DIA);
		Calendar fechaInicial = Calendar.getInstance();
		fechaInicial.set(Calendar.YEAR, hoy.get(Calendar.YEAR));
		fechaInicial.set(Calendar.MONTH, hoy.get(Calendar.MONTH));
		fechaInicial.set(Calendar.DAY_OF_MONTH, 1);
		fechaInicial.getTime();
		
		List<RegistroProduccion> list = null; 
		getHibernateTemplate().setCacheQueries(true);
		switch (type) {
			case RegistroProduccion.CONSULTA_GENERAL_MES:
				list = getHibernateTemplate().find(
						 "from RegistroProduccion as r where r.fechaRegistroDia BETWEEN ? and ?",
						 format.format(fechaInicial.getTime()), format.format(hoy.getTime()));				
				break;
				
			case RegistroProduccion.CONSULTA_GENERAL_DIA:
				list = getHibernateTemplate().find(
						 "from RegistroProduccion as r where r.fechaRegistroDia = ? order by fechaRegistro",
						 format.format(hoy.getTime()));
				break;
				
			case RegistroProduccion.CONSULTA_GENERAL_TODO:
				list = getHibernateTemplate().find("from RegistroProduccion order by fechaRegistro");
				break;
		}
		return list != null && !list.isEmpty() ? list : new ArrayList<RegistroProduccion>();		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<RegistroProduccion> getRegistrosProduccionFromTo(Calendar from, Calendar to) {
		List<RegistroProduccion> list = getHibernateTemplate().find(
				"from RegistroProduccion as d where d.fechaRegistro >= ? and fecharegistro <= ?", from, to);
		return list != null && !list.isEmpty() ? list : new ArrayList<RegistroProduccion>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroProduccion> getRegistrosProduccionIntervalo(Calendar from, Calendar to) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(IACNAConstants.FORMATO_FECHA_BUSQUEDA_DIA);				
		List<RegistroProduccion> list = getHibernateTemplate().find(
				"from RegistroProduccion as d where d.fechaRegistroDia >= ? and d.fechaRegistroDia <= ?", 
				simpleDateFormat.format(from.getTime()), simpleDateFormat.format(to.getTime()));
		return list != null && !list.isEmpty() ? list : new ArrayList<RegistroProduccion>();
	}

	@Override
	@Transactional
	public void deleteRegistroProduccion(RegistroProduccion registroProduccion)
			throws DataAccessException {
		getHibernateTemplate().delete(registroProduccion);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroProduccion> getRegistroProduccionByTurno(int type, String turno) {
		Calendar hoy = Calendar.getInstance();		
		hoy.set(Calendar.YEAR, hoy.get(Calendar.YEAR));
		hoy.set(Calendar.MONTH, hoy.get(Calendar.MONTH));
		hoy.set(Calendar.DAY_OF_MONTH, hoy.get(Calendar.DAY_OF_MONTH));
		SimpleDateFormat format = new SimpleDateFormat(
				IACNAConstants.FORMATO_FECHA_BUSQUEDA_DIA);
		
		Calendar fechaInicial = Calendar.getInstance();
		fechaInicial.set(Calendar.YEAR, hoy.get(Calendar.YEAR));
		fechaInicial.set(Calendar.MONTH, hoy.get(Calendar.MONTH));
		fechaInicial.set(Calendar.DAY_OF_MONTH, 1);
		fechaInicial.getTime();
		
		List<RegistroProduccion> list = null;		
		switch (type) {
			case 0:
				list = getHibernateTemplate().find(
						 "from RegistroProduccion as r " +
						 "where r.turno = ? and (r.fechaRegistroDia BETWEEN ? and ?)",
						 turno, format.format(fechaInicial.getTime()), format.format(hoy.getTime()));
				break;
				
			case 1:
				list = getHibernateTemplate().find(
						 "from RegistroProduccion as r " +
						 "where r.turno = ? and r.fechaRegistroDia = ? ",
						 turno, format.format(hoy.getTime()));
				break;
		}
		return list != null && !list.isEmpty() ? list : new ArrayList<RegistroProduccion>();
	}
	
	@SuppressWarnings("unchecked")
	public List<RegistroProduccion> getRegistroProduccionByNombreFormula(String nombreFormula, String idFormula) {
		return getHibernateTemplate().find("from RegistroProduccion as r " +
				"where r.formula = ? and r.idFormula = ?",
				nombreFormula, idFormula);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroProduccion> getRegistroProduccionByNombreFormulaDiario(String nombreFormula, String idFormula) {
		Calendar hoy = Calendar.getInstance();		
		hoy.set(Calendar.YEAR, hoy.get(Calendar.YEAR));
		hoy.set(Calendar.MONTH, hoy.get(Calendar.MONTH));
		hoy.set(Calendar.DAY_OF_MONTH, hoy.get(Calendar.DAY_OF_MONTH));
		SimpleDateFormat format = new SimpleDateFormat(
				IACNAConstants.FORMATO_FECHA_BUSQUEDA_DIA);
		
		return getHibernateTemplate().find("from RegistroProduccion as r " +
				"where r.formula = ? and r.idFormula = ? and fechaRegistroDia = ?",
				nombreFormula, idFormula, format.format(hoy.getTime()));
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroProduccion> getRegistroProducionMensual(Calendar hoy)
			throws DataAccessException {
		Calendar fechaInicial = Calendar.getInstance();
		fechaInicial.set(Calendar.YEAR, hoy.get(Calendar.YEAR));
		fechaInicial.set(Calendar.MONTH, hoy.get(Calendar.MONTH));
		fechaInicial.set(Calendar.DAY_OF_MONTH, hoy.get(Calendar.DAY_OF_MONTH));
		fechaInicial.getTime();
		 List<RegistroProduccion> registrosMensuales = getHibernateTemplate().find(
				 "from RegistroProduccion as r where r.fechaRegistro >= ? " +
				"and r.fechaRegistro <= ?", fechaInicial, hoy);
		return registrosMensuales != null && !registrosMensuales.isEmpty() ? 
				registrosMensuales : null;
	}
	

}

