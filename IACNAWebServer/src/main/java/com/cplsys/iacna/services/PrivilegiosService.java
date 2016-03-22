package com.cplsys.iacna.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsys.iacna.dao.PrivilegiosDAO;
import com.cplsys.iacna.domain.Privilegio;

@Service
public class PrivilegiosService {

	@Autowired
	private PrivilegiosDAO privilegiosDAO;

	public void save(final Privilegio privilegio) {
		privilegiosDAO.save(privilegio);
	}

}
