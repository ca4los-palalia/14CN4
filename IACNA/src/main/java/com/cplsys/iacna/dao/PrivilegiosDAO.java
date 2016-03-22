package com.cplsys.iacna.dao;

import java.util.List;

import com.cplsys.iacna.domain.Privilegio;

public interface PrivilegiosDAO {

	public void save(final Privilegio privilegio);

	public List<Privilegio> getPrivilegio(int idUsuario);

}
