package com.cplsys.iacna.services;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.cplsys.iacna.utils.IACNAConstants;
import com.cplsys.iacna.webservice.IACNAWebServicePubliser;

public class IACNAWSProvider {

	private IIACNAWebService iacnaService = null;

	public void init() {
		new IACNAWebServicePubliser();
		try {
			URL url = new URL(IACNAConstants.IACNA_WS_WSDL_ADDRESS);
			QName qname = new QName("http://webservice.iacna.cplsys.com/",
					"IACNAWebServiceImplService");
			Service service = Service.create(url, qname);
			iacnaService = service.getPort(IIACNAWebService.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IIACNAWebService getIacnaService() {
		return iacnaService;
	}

	public void setIacnaService(IIACNAWebService iacnaService) {
		this.iacnaService = iacnaService;
	}

}
