package com.cplsys.iacna.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsys.iacna.dao.FormulaDAO;
import com.cplsys.iacna.domain.Componente;
import com.cplsys.iacna.domain.Formula;
import com.cplsys.iacna.domain.RegistroProduccion;
@Service
public class FormulaService {

	@Autowired
	FormulaDAO formulaDAO;

	public void saveFormula(final Formula formula) {
		formulaDAO.save(formula);
	}

	public void deleteFormula(final Formula formula) {
		formulaDAO.delete(formula);
	}

	public List<RegistroProduccion> getFormulasProduccion(int type, String nombreFormula, String idFormula) {
		return formulaDAO.getFormulasProduccion(type, nombreFormula, idFormula);
	}

	public List<RegistroProduccion> getAllFormulas(int type, Calendar to, Calendar end) {
		return formulaDAO.getAllFormulas(type, to, end);
	}
	
	public List<Formula> getByIdAndName(final Integer idFormula, final String nombreFormula) {
		return formulaDAO.getByIdAndName(idFormula, nombreFormula);
	}
	
	public Formula getFormula(Componente componente) {
		return formulaDAO.getFormula(componente);
	}
	
	public Formula getFormulaById(Integer idFormula) {
		return formulaDAO.getFormulaById(idFormula);
	}
	
	public List<Formula> getFormulaOnLine() {
		return formulaDAO.getFormulaOnLine();
	}
}
