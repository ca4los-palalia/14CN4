package com.cplsys.iacna.dao;

import java.util.Calendar;
import java.util.List;

import com.cplsys.iacna.domain.Componente;
import com.cplsys.iacna.domain.Formula;
import com.cplsys.iacna.domain.RegistroProduccion;

public interface FormulaDAO {

	public void save(Formula formula);

	public void delete(Formula formula);

	public List<RegistroProduccion> getFormulasProduccion(int type, String nombreFormula, String idFormula);

	public List<RegistroProduccion> getAllFormulas(int type, Calendar to, Calendar end);

	public Formula getFormula(Componente componente);
	
	public Formula getFormulaById(Integer idFormula);

	public List<Formula> getByIdAndName(Integer idFormula, String nombreFormula);
	
	public List<Formula> getFormulaOnLine();
}
