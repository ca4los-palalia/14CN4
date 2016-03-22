package com.cplsys.iacna.utils;

import org.springframework.stereotype.Repository;

import com.cplsys.iacna.domain.Usuario;
import com.cplsys.iacna.services.ISession;

@Repository
public class Session implements ISession {

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario currentUser) {
		usuario = currentUser;
	}


}
