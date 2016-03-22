package com.cplsys.iacna.webservice;

import java.util.Stack;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.domain.Bascula;
import com.cplsys.iacna.domain.IACNADispatcher;
import com.cplsys.iacna.domain.Ingrediente;
import com.cplsys.iacna.services.BasculaService;
import com.cplsys.iacna.services.BatchProduccionService;
import com.cplsys.iacna.services.IIACNAWebService;
import com.cplsys.iacna.services.ProductionStackService;

@WebService(endpointInterface = "com.cplsys.iacna.services.IIACNAWebService")
@Repository
public class IACNAWebServiceImpl implements IIACNAWebService {
	@Autowired
	private ProductionStackService productionStackService;
	private IACNADispatcher iacnaDispatcher;
	private Stack<Ingrediente> ingredientesStack;
	@Autowired
	private BasculaService basculaService;
	@Autowired
	private BatchProduccionService batchProduccionService;
	
	
	@PostConstruct
	public void init() {
		ingredientesStack = new Stack<Ingrediente>();
	}
	
	@Override
	public void habilitarBascula(int bascula) {
		Bascula basculaFromDB = 
				basculaService.getBasculaByName(String.valueOf(bascula));
		basculaFromDB.setBasculaAsignada(true);
		basculaService.saveBascula(basculaFromDB);
	}

	@Override
	public void deshabilitarBascula(int bascula) {
		Bascula basculaFromDB = 
				basculaService.getBasculaByName(String.valueOf(bascula));
		basculaFromDB.setBasculaAsignada(false);
		basculaService.saveBascula(basculaFromDB);
	}

	@Override
	public int getTotalIngredientesFormula() {
		return iacnaDispatcher.getFormula().getIngrediente().size();
	}

	@Override
	public String getBasculaNombre(int bascula) {
		Bascula basculaFromDB =
				basculaService.getBasculaByName(String.valueOf(bascula));
		return basculaFromDB.getNombre();
	}

	@Override
	public boolean isBasculaPrepesado(int bascula) {
		Bascula basculaFromDB =
				basculaService.getBasculaByName(String.valueOf(bascula));
		return basculaFromDB.isPrepesado();
	}

	@Override
	public boolean isBasculaModoManual(int bascula) {
		Bascula basculaFromDB =
				basculaService.getBasculaByName(String.valueOf(bascula));
		return basculaFromDB.isModoManual();
	}

	@Override
	public String getTurnoFormula() {
		return iacnaDispatcher.getFormula().getTurno();
	}

	@Override
	public boolean isFormulaCancelada() {
		return iacnaDispatcher.getFormula().isCancelada();
	}

	@Override
	public int getBatchsFormula() {
		return iacnaDispatcher.getFormula().getTotalBatchAProcesar();
	}

	@Override
	public String getNombreIngrediente(int ingrediente) {
		return null;
	}

	@Override
	public int getBasculaIngrediente(int ingrediente) {
		return 0;
	}

	@Override
	public int getEspecificacionIngrediente(int ingrediente) {
		return 0;
	}

	@Override
	public int getToleranciaMinima(int ingrediente) {
		return 0;
	}

	@Override
	public boolean isIngredienteAsignadoAPrepesado(int ingrediente) {
		return false;
	}

	@Override
	public String getNombreFormula() {
		return iacnaDispatcher.getFormula().getNombre();
	}

	@Override
	public boolean isFormulaDespachada() {
		iacnaDispatcher.getFormula().isDespachada();
		return false;
	}

	private void fillIngredientes() {
		for (Ingrediente ingrediente : 
			iacnaDispatcher.getFormula().getIngrediente()) {
			
		}
	}

}
