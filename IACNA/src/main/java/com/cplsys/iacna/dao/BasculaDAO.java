package com.cplsys.iacna.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.cplsys.iacna.domain.Bascula;

public interface BasculaDAO {

	public void saveBascula(Bascula bascula) throws DataAccessException;
	
	public List<Bascula> getAll() throws DataAccessException;

	public List<Bascula> getBasculasLibres() throws DataAccessException;

	public void deleteBascula(Bascula bascula) throws DataAccessException;
	
	public Bascula getBasculaByName(String name);

}
