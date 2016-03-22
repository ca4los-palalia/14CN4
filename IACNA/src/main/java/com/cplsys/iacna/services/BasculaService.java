package com.cplsys.iacna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsys.iacna.dao.BasculaDAO;
import com.cplsys.iacna.domain.Bascula;

@Service
public class BasculaService {

	@Autowired
	private BasculaDAO basculaDAO;

	public void saveBascula(final Bascula bascula) {
		basculaDAO.saveBascula(bascula);
	}

	public List<Bascula> getAll() {
		return basculaDAO.getAll();
	}

	public List<Bascula> getBasculasLibres() {
		return basculaDAO.getBasculasLibres();
	}

	public void deleteBascula(final Bascula bascula) {
		basculaDAO.deleteBascula(bascula);
	}
	
	public Bascula getBasculaByName(String nombre) {
		return basculaDAO.getBasculaByName(nombre);
	}
	
	public void saveBasculaServer(final Bascula bascula) {
		basculaDAO.saveBascula(bascula);
	}

	public List<Bascula> getAllServer() {
		return basculaDAO.getAll();
	}

	public List<Bascula> getBasculasLibresServer() {
		return basculaDAO.getBasculasLibres();
	}

	public void deleteBasculaServer(final Bascula bascula) {
		basculaDAO.deleteBascula(bascula);
	}
	
	public Bascula getBasculaByNameServer(String nombre) {
		return basculaDAO.getBasculaByName(nombre);
	}	
	

}
