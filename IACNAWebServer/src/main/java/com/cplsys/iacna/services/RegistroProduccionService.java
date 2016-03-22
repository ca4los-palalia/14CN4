package com.cplsys.iacna.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<RegistroProduccion> getAll() {
		return produccionDAO.getAll();
	}

	public List<RegistroProduccion> getRegistroProduccionFromTo(Calendar from, Calendar to) {
		return produccionDAO.getRegistrosProduccionFromTo(from, to);
	}

	public void deleteRegistroProduccion(RegistroProduccion registroProduccion) {
		produccionDAO.deleteRegistroProduccion(registroProduccion);
	}
	
	public List<RegistroProduccion> getRegistroProduccionByTurno(String turno) {
		return produccionDAO.getRegistroProduccionByTurno(turno);
	}

	public List<RegistroProduccion> getRegistroProduccionByNombreFormula(String nombreFormula) {
		return produccionDAO.getRegistroProduccionByNombreFormula(nombreFormula);
	}	
	
}
