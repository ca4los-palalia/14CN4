package com.cplsys.iacna.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsys.iacna.dao.BatchProduccionDAO;
import com.cplsys.iacna.domain.BatchProduccion;

@Service
public class BatchProduccionService {

	@Autowired
	private BatchProduccionDAO batchProduccionDAO;

	public void saveBascula(final BatchProduccion batchProduccion) {
		batchProduccionDAO.saveBatchProduccion(batchProduccion);
	}

	public List<BatchProduccion> getAll() {
		return batchProduccionDAO.getAll();
	}

	public List<BatchProduccion> getBatchProduccionFromTo(Calendar from, Calendar to)  {
		return batchProduccionDAO.getBatchProduccionFromTo(from, to);
	}

	public void deleteBatchProduccion(BatchProduccion batchProduccion)  {
		batchProduccionDAO.deleteBatchProduccion(batchProduccion);
	}
	
	public List<BatchProduccion> getBatchProduccion(Integer idRegistro) throws DataAccessException {
		return batchProduccionDAO.getBatchProduccion(idRegistro);
	}

}
