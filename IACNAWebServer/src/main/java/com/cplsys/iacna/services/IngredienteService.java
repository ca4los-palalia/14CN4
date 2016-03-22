package com.cplsys.iacna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsys.iacna.dao.IngredienteDAO;
import com.cplsys.iacna.domain.Ingrediente;

@Service
public class IngredienteService {

	@Autowired
	IngredienteDAO ingredienteDAO;

	public void saveIngrediente(final Ingrediente ingrediente) {
		ingredienteDAO.saveIngrediente(ingrediente);
	}

	public List<Ingrediente> getAllIngredientes() {
		return ingredienteDAO.getAllIngredientes();
	}

	public Ingrediente getIngrediente(final Ingrediente ingrediente) {
		return ingredienteDAO.getIngrediente(ingrediente);
	}

	public void deleteIngrediente(final Ingrediente ingrediente) {
		ingredienteDAO.deleteIngrediente(ingrediente);
	}
}
