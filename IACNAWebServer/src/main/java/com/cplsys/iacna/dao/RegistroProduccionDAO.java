package com.cplsys.iacna.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.cplsys.iacna.domain.RegistroProduccion;

public interface RegistroProduccionDAO {

	public void saveRegistroProduccion(RegistroProduccion produccion) throws DataAccessException;

	public List<RegistroProduccion> getAll() throws DataAccessException;

	public List<RegistroProduccion> 
	getRegistrosProduccionFromTo(Calendar from, Calendar to) throws DataAccessException;

	public void deleteRegistroProduccion(RegistroProduccion registroProduccion) throws DataAccessException;

	public List<RegistroProduccion> getRegistroProduccionByTurno(String turno) throws DataAccessException;
	
	public List<RegistroProduccion> getRegistroProduccionByNombreFormula(String nombreFormula) throws DataAccessException;
}
