package com.cplsys.iacna.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class CellExcel{
	
	private Vector celda;
	private Vector informacion; 
	
	public CellExcel() {
		celda=new Vector();
	}

	public int getRowExcel(int indice) {
		int ret=0;
		for (int i = 0; i < celda.size(); i++) {
			if(indice==i){
				ret=Integer.parseInt(celda.get(i).toString());
			}
		}
		return ret;
	}
	public Vector getCelda() {
		return celda;
	}

	public void setValueAtCelda(Object cel) {
		celda.add(cel);
	}

	public Vector getInformacion() {
		return informacion;
	}

	public void setValueAtInformacion(Object inf) {
		informacion.add(inf);
	}
	
}
