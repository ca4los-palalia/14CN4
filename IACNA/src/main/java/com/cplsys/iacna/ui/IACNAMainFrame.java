package com.cplsys.iacna.ui;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.app.ui.IACNAFrame;
import com.cplsys.iacna.webservice.IACNAWebServicePubliser;

@Repository
public class IACNAMainFrame {

	private static final long serialVersionUID = 3879041067522897664L;
	@Autowired 
	private IACNAFrame iacnaFrame;
	@Autowired
	private IACNAWebServicePubliser iacnaWebServicePubliser;
	
	@PostConstruct
	public void init() {
		/*
		try {			
			URL url = new URL(IACNAConstants.IACNA_WS_WSDL_ADDRESS);
			QName qname = new QName("http://webservice.iacna.cplsys.com/",
					"IACNAWebServiceImplService");
			Service service = Service.create(url, qname);
			IIACNAWebService hello = service.getPort(IIACNAWebService.class);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		iacnaFrame.setVisible(true);
		
	}
	
	

}