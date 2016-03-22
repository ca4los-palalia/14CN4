package com.cplsys.iacna.daoimpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.cplsys.iacna.dao.MaterialDAO;
import com.cplsys.iacna.domain.Material;

public class MaterialDAOImpl extends HibernateDaoSupport implements MaterialDAO {

	@Override
	@Transactional
	public void saveMaterial(Material material) {

	}

	@Override
	@Transactional
	public void deleteMaterial(Material material) {

	}

	@Override
	@Transactional
	public List<Material> getAllMateriales() {
		return null;
	}

	@Override
	@Transactional
	public Material getMaterial(Material material) {
		return null;
	}

}
