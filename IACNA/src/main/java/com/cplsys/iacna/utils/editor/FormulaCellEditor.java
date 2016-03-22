package com.cplsys.iacna.utils.editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;


public class FormulaCellEditor implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		
		Color currColor=getColor(row, 0);
		Font font = new Font("Serif", Font.PLAIN, 10);
		
		switch (column) {
			case 0:
					JTextField fieldProduccionBascula = new JTextField(value.toString());
					fieldProduccionBascula.setBackground(currColor);
					fieldProduccionBascula.setOpaque(true);
					fieldProduccionBascula.setHorizontalAlignment(SwingConstants.CENTER );
					
					//SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					//fieldProduccionBascula.setToolTipText(format.format(produccion.getFechaRegistro().getTime()));
					
					return fieldProduccionBascula;
			case 1: 
					JLabel labelMp = new JLabel(value.toString());
					labelMp.setFont(font);
					labelMp.setBackground(currColor);
					labelMp.setOpaque(true);
					return labelMp;
			case 2:
					JLabel labelDescripcion = new JLabel(value.toString());
					labelDescripcion.setFont(font);
					labelDescripcion.setBackground(currColor);
					labelDescripcion.setOpaque(true);
					return labelDescripcion;
			case 3:
					JLabel labelEspecificacion = new JLabel(value.toString());
					labelEspecificacion.setFont(font);
					labelEspecificacion.setBackground(currColor);
					labelEspecificacion.setOpaque(true);
					return labelEspecificacion;
			case 4:
					JLabel labelMinima = new JLabel(value.toString());
					labelMinima.setFont(font);
					labelMinima.setBackground(currColor);
					labelMinima.setOpaque(true);
					return labelMinima;		
			case 5:
					JLabel labelMaxima = new JLabel(value.toString());
					labelMaxima.setFont(font);
					labelMaxima.setBackground(currColor);
					labelMaxima.setOpaque(true);
					return labelMaxima;					
			default:
					JLabel labelPesoObtenido = new JLabel();
					labelPesoObtenido.setFont(font);
					currColor=getColor(row, 1);
					labelPesoObtenido.setBackground(currColor);
					labelPesoObtenido.setOpaque(true);
					
					 labelPesoObtenido.setText(value.toString());
					 return labelPesoObtenido;
		}
	}
	
	public Color getColor(int row, int type) {
		Color currColor=Color.white;
		
		switch(type){
		case 0:
			if (row % 2 != 0)
				currColor=Color.white;
			else
				currColor=new Color(222,222,255);
			 break;			 
		case 1:
			if (row % 2 != 0)
				currColor = new Color(204,255,255);
			else
				currColor = new Color(153,204,255);
		}
		return currColor;
	}
}
