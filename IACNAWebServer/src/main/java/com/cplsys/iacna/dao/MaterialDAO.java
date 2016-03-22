package com.cplsys.iacna.dao;

import java.util.List;

import com.cplsys.iacna.domain.Material;

public interface MaterialDAO {

	public void saveMaterial(Material material);

	public void deleteMaterial(Material material);

	public List<Material> getAllMateriales();

	public Material getMaterial(Material material);
}
