package com.cplsys.iacna.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.cplsys.iacna.domain.RegistroProduccion;

public interface RegistroProduccionDAO {

	public void saveRegistroProduccion(RegistroProduccion produccion) throws DataAccessException;

	public List<RegistroProduccion> getAll(int type) throws DataAccessException;

	public List<RegistroProduccion> 
	getRegistrosProduccionFromTo(Calendar from, Calendar to) throws DataAccessException;
	
	public List<RegistroProduccion> getRegistrosProduccionIntervalo(Calendar from, Calendar to) throws DataAccessException ;

	public void deleteRegistroProduccion(RegistroProduccion registroProduccion) throws DataAccessException;

	public List<RegistroProduccion> getRegistroProduccionByTurno(int type, String turno) throws DataAccessException;
	
	public List<RegistroProduccion> getRegistroProduccionByNombreFormula(String nombreFormula, String idFormula) throws DataAccessException;

	public List<RegistroProduccion> getRegistroProduccionByNombreFormulaDiario(String nombreFormula, String idFormula) throws DataAccessException;

	public List<RegistroProduccion> getRegistroProducionMensual(Calendar hoy) throws DataAccessException;
	
}
