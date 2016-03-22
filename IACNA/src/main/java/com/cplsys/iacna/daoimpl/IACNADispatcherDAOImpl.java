package com.cplsys.iacna.daoimpl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsys.iacna.dao.IACNADispatcherDAO;
import com.cplsys.iacna.domain.IACNADispatcher;

@Repository
public class IACNADispatcherDAOImpl extends HibernateDaoSupport implements IACNADispatcherDAO {

	@Autowired
	public void seSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional
	public void saveIACNADispatcher(IACNADispatcher iacnaDispatcher) {
		getHibernateTemplate().saveOrUpdate(iacnaDispatcher);
	}

	@Override
	@Transactional
	public void deleteIACNADispatcher(IACNADispatcher iacnaDispatcher) {
		getHibernateTemplate().delete(iacnaDispatcher);
	}

	@Override
	@Transactional
	public void stopFromProduction(IACNADispatcher iacnaDispatcher) {
		getHibernateTemplate().update(iacnaDispatcher);
	}

	@Override
	@Transactional
	public void markAsDispatched(IACNADispatcher iacnaDispatcher) {
		getHibernateTemplate().update(iacnaDispatcher);
	}

	@Override
	public List<IACNADispatcher> getAll() {
		return getHibernateTemplate().find("from IACNADispatcher");
	}

	@Override
	public List<IACNADispatcher> getIACNADispatcherBetweenDates(Calendar from,
			Calendar to) {
		return null;
	}

	@Override
	public List<IACNADispatcher> getAbortedProccess() {
		return null;
	}

	@Override
	public List<IACNADispatcher> getCompletedProccess() {
		return null;
	}

	@Override
	public List<IACNADispatcher> getTaskInProgress() {
		return getHibernateTemplate().find("from IACNADispatcher as i where i.formula.cancelada = ? and " +
				"i.formula.despachada = ?", false, false);
	}

}
