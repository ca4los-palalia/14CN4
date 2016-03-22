package com.cplsys.iacna.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsys.iacna.dao.FormulaDAO;
import com.cplsys.iacna.domain.Componente;
import com.cplsys.iacna.domain.Formula;

@Repository
public class FormulaDAOImpl extends HibernateDaoSupport implements FormulaDAO {

	@Autowired
	@Qualifier("sessionFactory")
	public void seSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void saveFormula(Formula formula) {
		getHibernateTemplate().saveOrUpdate(formula);
	}

	@Override
	@Transactional
	public void deleteFormula(Formula formula) {
		getHibernateTemplate().delete(formula);
	}

	@Override
	@Transactional
	public List<Formula> getAllFormulas() {
		List<Formula> formulas = getHibernateTemplate().find("select * from Formula");
		return formulas != null && !formulas.isEmpty() ? formulas : null;
	}

	@Override
	@Transactional
	public Formula getFormula(Componente componente) {
		// TODO Auto-generated method stub
		return null;
	}

}
