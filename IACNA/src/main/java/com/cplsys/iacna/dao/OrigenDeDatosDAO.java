package com.cplsys.iacna.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.cplsys.iacna.domain.OrigenDeDatos;

public interface OrigenDeDatosDAO {

	public void saveOrigenDeDatos(OrigenDeDatos origenDeDatos) throws DataAccessException;

	public List<OrigenDeDatos> getAll() throws DataAccessException;

	public void deleteOrigenDeDatos(OrigenDeDatos origenDeDatos) throws DataAccessException;

}
