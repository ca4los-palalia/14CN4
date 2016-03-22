package com.cplsys.iacna.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.dao.ProductoDAO;
import com.cplsys.iacna.domain.Producto;

@Repository
public class ProductoDAOImpl extends HibernateDaoSupport implements ProductoDAO {

	@Autowired
	public void init(final SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void saveProduct(Producto producto) {
		getHibernateTemplate().save(producto);
	}

	@Override
	public List<Producto> getAllProducts() {
		return null;
	}

	@Override
	public void deleteProduct(Producto producto) {
		getHibernateTemplate().delete(producto);
	}

}
