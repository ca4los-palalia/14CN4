package com.cplsys.iacna.daoimpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsys.iacna.dao.FormulaDAO;
import com.cplsys.iacna.domain.Componente;
import com.cplsys.iacna.domain.Formula;
import com.cplsys.iacna.domain.RegistroProduccion;
import com.cplsys.iacna.utils.IACNAConstants;

@Repository
public class FormulaDAOImpl extends HibernateDaoSupport implements FormulaDAO {

	@Autowired
	public void seSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void save(final Formula formula) {
		getHibernateTemplate().saveOrUpdate(formula);
	}

	@Override
	@Transactional
	public void delete(final Formula formula) {
		getHibernateTemplate().delete(formula);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<RegistroProduccion> getFormulasProduccion(int type, String nombreFormula, String idFormula) {		
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
		
		List<RegistroProduccion> registroProduccion = null;
		List<Formula> formulas = null; 
		
		switch (type) {
			case RegistroProduccion.CONSULTA_GENERAL_MES:
				registroProduccion = getHibernateTemplate().find(
						 "from RegistroProduccion as r where " +
						 "r.formula = ? and r.idFormula = ? and r.fechaRegistroDia BETWEEN ? and ?",
						 nombreFormula, idFormula, format.format(fechaInicial.getTime()), 
						 format.format(hoy.getTime()));
				break;
				
			case RegistroProduccion.CONSULTA_GENERAL_DIA:
				formulas = getHibernateTemplate().find(
						 "from RegistroProduccion as r where r.fechaRegistroDia = ? ",
						 format.format(hoy.getTime()));
				break;
				
			case RegistroProduccion.CONSULTA_GENERAL_TODO:
				formulas = getHibernateTemplate().find("from RegistroProduccion");
				break;			
		}		
		return registroProduccion != null && !registroProduccion.isEmpty() ? registroProduccion : null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<RegistroProduccion> getAllFormulas(int type, Calendar to, Calendar end) {
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
		
		List<RegistroProduccion> formulas = null; 			
		
		switch(type) {
			case 0:
				formulas = getHibernateTemplate().find(
						 "from RegistroProduccion as r where " +
						 "r.fechaRegistroDia BETWEEN ? and ?",
						 format.format(fechaInicial.getTime()), format.format(hoy.getTime()));
			break;
			case 1:
				formulas = getHibernateTemplate().find(
						 "from RegistroProduccion as r where " +
						 "r.fechaRegistroDia = ?",
						 format.format(hoy.getTime()));
				break;
				
			case 2:
				to.set(Calendar.YEAR, to.get(Calendar.YEAR));
				to.set(Calendar.MONTH, to.get(Calendar.MONTH));
				to.set(Calendar.DAY_OF_MONTH, to.get(Calendar.DAY_OF_MONTH));
				to.getTime();
				
				end.set(Calendar.YEAR, end.get(Calendar.YEAR));
				end.set(Calendar.MONTH, end.get(Calendar.MONTH));
				end.set(Calendar.DAY_OF_MONTH, end.get(Calendar.DAY_OF_MONTH));
				end.getTime();
				
				formulas = getHibernateTemplate().find(
						 "from RegistroProduccion as r where " +
						 "r.fechaRegistroDia BETWEEN ? and ? order by r.idFormula",
						 format.format(to.getTime()), format.format(end.getTime()));
				break;
		}
		return formulas != null && !formulas.isEmpty() ? formulas : null;
	}

	@Override
	@Transactional
	public Formula getFormula(Componente componente) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Formula getFormulaById(Integer idFormula) {
		List<Formula> formulas = getHibernateTemplate().find(
				"from Formula as f where f.idFormula = ?", idFormula);
		return formulas != null && !formulas.isEmpty() ? formulas.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Formula> getByIdAndName(Integer idFormula, String nombreFormula) {
	//public Formula getByIdAndName(Integer idFormula, String nombreFormula) {
		/*
		List<Formula> formulas = getHibernateTemplate().find(
				"from Formula as f where f.idFormula = ? and f.nombre = ?",
				idFormula, nombreFormula);
		return formulas != null && !formulas.isEmpty() ? formulas.get(0) : null;
		*/
		List<Formula> formulas = null; 
		
		formulas = getHibernateTemplate().find(
				"from Formula as a where a.idFormula = ? and a.nombre = ?",
				idFormula, nombreFormula);
		return formulas != null && !formulas.isEmpty() ? formulas : null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Formula> getFormulaOnLine() {
		List<Formula> formulas = null; 
		
		formulas = getHibernateTemplate().find(
				"from Formula as a where a.cancelada = false and a.despachada = false");
		return formulas != null && !formulas.isEmpty() ? formulas : null;
	}

}
