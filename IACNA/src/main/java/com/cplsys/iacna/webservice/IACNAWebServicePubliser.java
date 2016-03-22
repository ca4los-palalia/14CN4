package com.cplsys.iacna.webservice;

import javax.annotation.PostConstruct;
import javax.xml.ws.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.utils.IACNAConstants;

@Repository
public class IACNAWebServicePubliser {
	
	@Autowired
	private IACNAWebServiceImpl iacnaWebServiceImpl; 

	@PostConstruct
	public void init() {
		Endpoint.publish(IACNAConstants.IACNA_WS_ADDRESS, 
				iacnaWebServiceImpl);
	}
}
