package com.cplsys.iacna.services;

import org.springframework.stereotype.Service;

import com.cplsys.iacna.utils.Session;

@Service
public interface IACNASessionService {

	public void setSession(Session session);

	public Session getSession();

}
