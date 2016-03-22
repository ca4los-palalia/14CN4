package com.cplsys.iacna.utils.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import org.springframework.beans.factory.annotation.Autowired;

import com.cplsys.iacna.domain.BatchProduccion;
import com.cplsys.iacna.domain.RegistroProduccion;
import com.cplsys.iacna.services.BatchProduccionService;
import com.cplsys.iacna.utils.IACNAConstants;

public class RegistroProduccionCellEditor extends JLabel implements TableCellRenderer {

	@Autowired
	private BatchProduccionService batchProduccionService;
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		RegistroProduccion produccion = (RegistroProduccion)value;		
		Color currColor=getColor(row, 0);
		Font font = new Font("Serif", Font.PLAIN, 10);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		this.setHorizontalAlignment(SwingConstants.CENTER);
		if (isSelected) {
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		} else {
			this.setBorder(BorderFactory.createEmptyBorder());
		}
		switch (column) {
			case 0:	this.setText(produccion.getBascula());					
					this.setBackground(currColor);					
					this.setToolTipText(format.format(produccion.getFechaRegistro().getTime()));
					this.setOpaque(true);
					return this;
			case 1: this.setText(produccion.getMp());
					this.setFont(font);
					this.setBackground(currColor);
					this.setOpaque(true);
					return this;
			case 2:	this.setText(produccion.getDescripcion());
					this.setHorizontalAlignment(SwingConstants.LEFT);
					this.setFont(font);
					this.setBackground(currColor);
					this.setOpaque(true);
					return this;
			case 3:	this.setText(String.valueOf(produccion.getEspecificacion()));
					this.setFont(font);
					this.setBackground(currColor);
					this.setOpaque(true);
					return this;
			case 4:	this.setText(String.valueOf(produccion.getToleranciaMinima()));
					this.setFont(font);
					this.setBackground(currColor);
					this.setOpaque(true);
					return this;
			case 5:	this.setText(String.valueOf(produccion.getToleranciaMaxima()));
					this.setFont(font);
					this.setBackground(currColor);
					this.setOpaque(true);
					return this;
			default:
					int idx = column - 6;
					
					Object[] bProductionArray = produccion.getBatchProduccion().toArray();
					this.setFont(font);
					currColor=getColor(row, 1);
					this.setBackground(currColor);
					this.setOpaque(true);
					
					if (idx < bProductionArray.length) {
						//-------
						bProductionArray = ordenarArray(bProductionArray).toArray();
						//--------
						BatchProduccion bProduction = (BatchProduccion)bProductionArray[idx];
						
						DecimalFormat formateador = new DecimalFormat("####.##");
						this.setText(
								formateador.format(
										Double.parseDouble(String.valueOf(bProduction.getPesoObtenido()))));
						return this;
					} else {
						this.setText("");
						return this;
					}			
		}
	}
	

	
	public Color getColor(int row, int type) {
		Color currColor=Color.white;
		
		switch(type){
		case 0:
			if (row % 2 != 0)
				currColor = Color.white;
			else
				currColor = IACNAConstants.PAIR_ROW_REPORT_TABLE;
			 break;			 
		case 1:
			if (row % 2 != 0)
				currColor = new Color(204,255,255);
			else
				currColor = new Color(153,204,255);
		}
		return currColor;
	}
	
	private List<BatchProduccion> ordenarArray (Object[] bProductionArray) {
		BatchProduccion extraccionBatchProduccion;
		List<BatchProduccion> lista = new ArrayList<BatchProduccion>();
		int i = 0;
		if (bProductionArray.length != 0) {
			Vector llegaId = new Vector();
			for (int h = 0; h < bProductionArray.length; h++) {
				BatchProduccion bProduction = (BatchProduccion)bProductionArray[h];
					llegaId.addElement(bProduction.getIdBatchProduccion());
			}
			Collections.sort(llegaId);
			while (i < llegaId.size()) {
				int j = 0;
				while(j < bProductionArray.length) {
					BatchProduccion bProduction = (BatchProduccion)bProductionArray[j];
					if(Integer.parseInt(String.valueOf(llegaId.get(i))) == bProduction.getIdBatchProduccion()) {
						extraccionBatchProduccion = new BatchProduccion();
						extraccionBatchProduccion.setIdBatchProduccion(bProduction.getIdBatchProduccion());
						extraccionBatchProduccion.setFechaRegistro(bProduction.getFechaRegistro());
						extraccionBatchProduccion.setPesoObtenido(bProduction.getPesoObtenido());
						extraccionBatchProduccion.setRegistroProduccion(bProduction.getRegistroProduccion());
						lista.add(i, extraccionBatchProduccion);
						break;
					}
					j++;
				}
				i++;
			}
			return lista;
		}
		return null;
	}
	
}
