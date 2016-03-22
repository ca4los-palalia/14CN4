package com.cplsys.iacna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsys.iacna.dao.FormulaDAO;
import com.cplsys.iacna.domain.Componente;
import com.cplsys.iacna.domain.Formula;
@Service
public class FormulaService {

	@Autowired
	FormulaDAO formulaDAO;

	public void saveFormula(final Formula formula) {
		formulaDAO.saveFormula(formula);
	}

	public void deleteFormula(final Formula formula) {
		formulaDAO.deleteFormula(formula);
	}

	public List<Formula> getAllFormulas() {
		return formulaDAO.getAllFormulas();
	}

	public Formula getFormula(Componente componente) {
		return formulaDAO.getFormula(componente);
	}
}
