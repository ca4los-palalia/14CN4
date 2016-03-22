package com.cplsys.iacna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplsys.iacna.dao.ProductoDAO;
import com.cplsys.iacna.domain.Producto;

@Service
public class ProductoService {

	@Autowired
	private ProductoDAO productoDAO;

	@Transactional
	public void saveProduct(final Producto producto) {
		productoDAO.saveProduct(producto);
	}

	@Transactional
	public List<Producto> getAllProducts() {
		return productoDAO.getAllProducts();
	}

	@Transactional
	public void deleteProduct(final Producto producto) {
		productoDAO.deleteProduct(producto);
	}

}
