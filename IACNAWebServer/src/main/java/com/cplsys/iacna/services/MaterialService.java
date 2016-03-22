package com.cplsys.iacna.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cplsys.iacna.dao.MaterialDAO;
import com.cplsys.iacna.domain.Material;

public class MaterialService {

	@Autowired
	MaterialDAO materialDAO;

	public void saveMaterial(final Material material) {
		materialDAO.saveMaterial(material);
	}

	public void deleteMaterial(final Material material) {
		materialDAO.deleteMaterial(material);
	}

	public List<Material> getAllMateriales() {
		return materialDAO.getAllMateriales();
	}

	public Material getMaterial(final Material material) {
		return materialDAO.getMaterial(material);
	}

}
