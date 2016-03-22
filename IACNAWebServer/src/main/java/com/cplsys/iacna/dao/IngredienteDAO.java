package com.cplsys.iacna.dao;

import java.util.List;

import com.cplsys.iacna.domain.Ingrediente;

public interface IngredienteDAO {

	public void saveIngrediente(Ingrediente ingrediente);

	public List<Ingrediente> getAllIngredientes();

	public Ingrediente getIngrediente(Ingrediente ingrediente);

	public void deleteIngrediente(Ingrediente ingrediente);
}
