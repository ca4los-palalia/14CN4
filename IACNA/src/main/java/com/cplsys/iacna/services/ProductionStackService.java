package com.cplsys.iacna.services;

import java.util.Stack;

import org.springframework.stereotype.Service;

import com.cplsys.iacna.domain.IACNADispatcher;

/**Objeto que contiene una pila con los procesos
 * esperando a ser producidos*/
@Service
public interface ProductionStackService {

	public Stack <IACNADispatcher> getProductionStack();
	
	public void addProccessToStack(IACNADispatcher iacnaDispatcher);
	
	public void removeProcessFromStack(IACNADispatcher iacnaDispatcher);
	
	public void clearProductionStack();
	
	public IACNADispatcher getTopProcessFromStack();
	
}
