package com.cplsys.iacna.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsys.iacna.dao.RegistroProduccionDAO;
import com.cplsys.iacna.domain.RegistroProduccion;

@Service
public class RegistroProduccionService {

	@Autowired
	private RegistroProduccionDAO produccionDAO;

	public void saveRegistroProduccion(RegistroProduccion produccion) {
		produccionDAO.saveRegistroProduccion(produccion);
	}

	public List<RegistroProduccion> getAll(int type) {
		return produccionDAO.getAll(type);
	}

	public List<RegistroProduccion> getRegistrosProduccionFromTo(Calendar from, Calendar to) {
		return produccionDAO.getRegistrosProduccionFromTo(from, to);
	}
	
	public List<RegistroProduccion> getRegistrosProduccionIntervalo(Calendar from, Calendar to) {
		return produccionDAO.getRegistrosProduccionIntervalo(from, to);
	}

	public void deleteRegistroProduccion(RegistroProduccion registroProduccion) {
		produccionDAO.deleteRegistroProduccion(registroProduccion);
	}
	
	public List<RegistroProduccion> getRegistroProduccionByTurno(int type, String turno) {
		return produccionDAO.getRegistroProduccionByTurno(type, turno);
	}

	public List<RegistroProduccion> getRegistroProduccionByNombreFormula(String nombreFormula, String idFormula) {
		return produccionDAO.getRegistroProduccionByNombreFormula(nombreFormula, idFormula);
	}
	
	public List<RegistroProduccion> getRegistroProduccionByNombreFormulaDiario(String nombreFormula, String idFormula) {
		return produccionDAO.getRegistroProduccionByNombreFormulaDiario(nombreFormula, idFormula);
	}
	
	
	public List<RegistroProduccion> getRegistroProducionMensual(Calendar hoy) {
		return produccionDAO.getRegistroProducionMensual(hoy);
	}
	
}
