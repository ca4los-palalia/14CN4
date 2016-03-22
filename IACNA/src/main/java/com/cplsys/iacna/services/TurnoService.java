package com.cplsys.iacna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsys.iacna.dao.BasculaDAO;
import com.cplsys.iacna.dao.TurnoDAO;
import com.cplsys.iacna.domain.Bascula;
import com.cplsys.iacna.domain.Turno;

@Service
public class TurnoService {

	@Autowired
	private TurnoDAO turnoDAO;
	
	public void saveTurno(Turno turno) {
		turnoDAO.saveTurno(turno);
	}

	public List<Turno> getAll()  {
		return turnoDAO.getAll();
	}

	public void deleteTurno(Turno turno) {
		turnoDAO.deleteTurno(turno);
	}

}
