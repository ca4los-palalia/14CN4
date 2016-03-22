package com.cplsys.iacna.services;

import org.springframework.stereotype.Service;

import com.cplsys.iacna.domain.Usuario;

@Service
public interface ISession {

	public Usuario getUsuario();

	public void setUsuario(final Usuario usuario);

}
