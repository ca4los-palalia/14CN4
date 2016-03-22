package com.cplsys.iacna.dao;

import java.util.List;

import com.cplsys.iacna.domain.Componente;
import com.cplsys.iacna.domain.Formula;

public interface FormulaDAO {

	public void saveFormula(Formula formula);

	public void deleteFormula(Formula formula);

	public List<Formula> getAllFormulas();

	public Formula getFormula(Componente componente);

}
