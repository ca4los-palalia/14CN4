package com.cplsys.iacna.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.springframework.stereotype.Service;

@WebService
@SOAPBinding(style = Style.RPC)
@Service
public interface IIACNAWebService {

	@WebMethod(operationName = "habilitarBascula")
	public void habilitarBascula(
			@WebParam(name = "bascula", partName = "bascula")  int bascula);
	
	@WebMethod(operationName = "deshabilitarBascula")
	public void deshabilitarBascula(
			@WebParam(name = "bascula", partName = "bascula") int bascula);
	
	@WebMethod(operationName = "getTotalIngredientesFormula")
	@WebResult(partName = "return")
	public int getTotalIngredientesFormula();
	
	@WebMethod(operationName = "getBasculaNombre")
	@WebResult(partName = "return")
	public String getBasculaNombre(
			@WebParam(name = "bascula", partName = "bascula") int bascula);

	@WebMethod(operationName = "isBasculaPrepesado")
	@WebResult(partName = "return")
	public boolean isBasculaPrepesado(
			@WebParam(name = "bascula", partName = "bascula") int bascula);
	
	@WebMethod(operationName = "isBasculaModoManual")
	@WebResult(partName = "return")
	public boolean isBasculaModoManual(
			@WebParam(name = "bascula", partName = "bascula") int bascula);

	@WebMethod(operationName = "getTurnoFormula")
	@WebResult(partName = "return")
	public String getTurnoFormula();
	
	@WebMethod(operationName = "isFormulaCancelada")
	@WebResult(partName = "return")
	public boolean isFormulaCancelada();
	
	@WebMethod(operationName = "getBatchsFormula")
	@WebResult(partName = "return")
	public int getBatchsFormula();
	
	@WebMethod(operationName = "getNombreIngrediente")
	@WebResult(partName = "return")
	public String getNombreIngrediente(
			@WebParam(name = "ingrediente", partName = "ingrediente") int ingrediente);
	
	@WebMethod(operationName = "getBasculaIngrediente")
	@WebResult(partName = "return")
	public int getBasculaIngrediente(
			@WebParam(name = "ingrediente", partName = "ingrediente") int ingrediente);
	
	@WebMethod(operationName = "getEspecificacionIngrediente")
	@WebResult(partName = "return")
	public int getEspecificacionIngrediente(
			@WebParam(name = "ingrediente", partName = "ingrediente") int ingrediente);
	
	@WebMethod(operationName = "getToleranciaMinima")
	@WebResult(partName = "return")
	public int getToleranciaMinima(
			@WebParam(name = "ingrediente", partName = "ingrediente") int ingrediente);
	
	@WebMethod(operationName = "isIngredienteAsignadoAPrepesado")
	@WebResult(partName = "return")
	public boolean isIngredienteAsignadoAPrepesado(
			@WebParam(name = "ingrediente", partName = "ingrediente") int ingrediente);
	
	@WebMethod(operationName = "getNombreFormula")
	@WebResult(partName = "return")
	public String getNombreFormula();
	
	@WebMethod(operationName = "isFormulaDespachada")
	@WebResult(partName = "return")
	public boolean isFormulaDespachada();
	
}