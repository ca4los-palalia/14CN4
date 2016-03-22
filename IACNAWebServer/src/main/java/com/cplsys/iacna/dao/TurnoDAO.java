package com.cplsys.iacna.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.cplsys.iacna.domain.Turno;

public interface TurnoDAO {

	public void saveTurno(Turno turno) throws DataAccessException;

	public List<Turno> getAll() throws DataAccessException;

	public void deleteTurno(Turno turno) throws DataAccessException;	

}
