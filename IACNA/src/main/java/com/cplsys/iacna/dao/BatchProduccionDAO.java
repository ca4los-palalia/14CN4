package com.cplsys.iacna.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.cplsys.iacna.domain.BatchProduccion;

public interface BatchProduccionDAO {

	public void saveBatchProduccion(BatchProduccion produccion) throws DataAccessException;

	public List<BatchProduccion> getAll() throws DataAccessException;

	public List<BatchProduccion> 
	getBatchProduccionFromTo(Calendar from, Calendar to) throws DataAccessException;

	public void deleteBatchProduccion(BatchProduccion bascula) throws DataAccessException;

	public List<BatchProduccion> getBatchProduccion(Integer idRegistro) throws DataAccessException;
	
}
