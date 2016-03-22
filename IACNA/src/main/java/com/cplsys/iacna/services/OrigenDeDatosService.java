package com.cplsys.iacna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsys.iacna.dao.OrigenDeDatosDAO;
import com.cplsys.iacna.dao.TurnoDAO;
import com.cplsys.iacna.domain.OrigenDeDatos;
import com.cplsys.iacna.domain.Turno;

@Service
public class OrigenDeDatosService {

	@Autowired
	private OrigenDeDatosDAO origenDeDatosDAO;
	
	public void saveOrigenDeDatos(OrigenDeDatos origenDeDatos) {
		origenDeDatosDAO.saveOrigenDeDatos(origenDeDatos);
	}

	public List<OrigenDeDatos> getAll()  {
		return origenDeDatosDAO.getAll();
	}

	public void deleteTurno(OrigenDeDatos origenDeDatos) {
		origenDeDatosDAO.deleteOrigenDeDatos(origenDeDatos);
	}

}
