package com.cplsys.iacna.services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.cplsys.iacna.domain.Formula;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IIACNAWebService {
	
	@WebMethod
	public Formula getFormula(Formula formula, boolean centinela, int indice);
	
	/*
	@WebMethod
	@WebResult(partName = "return")
	public Formula getFormula(@WebParam(name = "formula", partName = "formula")
							 Formula formula,
							 @WebParam(name = "proccessSuccessfuly",
							 partName = "proccessSuccessfuly")
	                         boolean proccessSuccessfuly);*/

}