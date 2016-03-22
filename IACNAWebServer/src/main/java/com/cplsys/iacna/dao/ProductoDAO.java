package com.cplsys.iacna.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.cplsys.iacna.domain.Producto;

public interface ProductoDAO {

	public void saveProduct(Producto producto) throws DataAccessException;

	public List<Producto> getAllProducts() throws DataAccessException;

	public void deleteProduct(Producto producto) throws DataAccessException;

}
