package com.cplsys.iacna.utils;

import java.util.Stack;

import javax.annotation.PostConstruct;
import javax.jws.soap.InitParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.domain.IACNADispatcher;
import com.cplsys.iacna.services.IACNADispatcherService;
import com.cplsys.iacna.services.ProductionStackService;

@Repository
public class ProductionStack implements ProductionStackService {

	private Stack<IACNADispatcher> productionStack;
	@Autowired
	private IACNADispatcherService iacnaDispatcherService;
	
	
	@PostConstruct
	public void init(){
		initObjects();
	}
	
	private void initObjects() {
		productionStack = new Stack<IACNADispatcher>();
	}
	
	@Override
	public Stack<IACNADispatcher> getProductionStack() {
		return productionStack;
	}

	@Override
	public void addProccessToStack(IACNADispatcher iacnaDispatcher) {
		if (iacnaDispatcher != null) {
			productionStack.push(iacnaDispatcher);	
		}
	}

	@Override
	public void removeProcessFromStack(IACNADispatcher iacnaDispatcher) {
		if (iacnaDispatcher != null) {
			productionStack.remove(iacnaDispatcher);
		}
	}

	@Override
	public void clearProductionStack() {
		productionStack.clear();
	}

	@Override
	public IACNADispatcher getTopProcessFromStack() {
		return productionStack.firstElement();
	}

}
