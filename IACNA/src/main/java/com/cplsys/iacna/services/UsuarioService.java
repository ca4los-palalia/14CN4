package com.cplsys.iacna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsys.iacna.dao.UsuarioDAO;
import com.cplsys.iacna.domain.Usuario;

@Service
public class UsuarioService {

	@Autowired
	UsuarioDAO usuarioDAO;

	public Usuario getUsuario(final Usuario usuario) {
		return usuarioDAO.getUsuario(usuario);
	}

	public void saveUser(final Usuario usuario) {
		usuarioDAO.saveUser(usuario);
	}

	public Usuario getUsuario(String userName, String password) {
		return usuarioDAO.getUsuario(userName, password);
	}

	public List<Usuario> getAll() {
		return usuarioDAO.getAll();
	}
	
	public void newUser(Usuario usuario) {
		usuarioDAO.newUser(usuario);
	}

}
