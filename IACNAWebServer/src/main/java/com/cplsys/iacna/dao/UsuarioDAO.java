package com.cplsys.iacna.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cplsys.iacna.domain.Usuario;

@Service
public interface UsuarioDAO {

	public Usuario getUsuario(Usuario usuario);

	public void saveUser(Usuario usuario);

	public Usuario getUsuario(String userName, String password);

	public List<Usuario> getAll();
}
