package com.cplsys.iacna.utils.render;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.cplsys.iacna.domain.Formula;
import com.cplsys.iacna.utils.IACNAConstants;

public class ListaFormulaRender extends JLabel implements ListCellRenderer{

	private static final long serialVersionUID = 3945556825494863499L;
	private List<Formula> formulaBuscada;
	private DefaultListCellRenderer render = new DefaultListCellRenderer();

    
    public ListaFormulaRender(List<Formula> formulaBuscada) {
    	this.formulaBuscada = formulaBuscada;
    }
   
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		JLabel component = (JLabel)render.getListCellRendererComponent(
				list, value, index, isSelected, cellHasFocus);
		if (isSelected) {
			component.setFont(new Font("calibri", Font.BOLD, 11));
		} else {
			component.setFont(new Font("calibri", Font.PLAIN, 10));	
		}
		if (formulaBuscada.get(index).isCancelada()) {
			component.setBackground(IACNAConstants.CELL_CANCELED_FORMULA);
			component.setForeground(new Color(0, 0, 0));
		} else {
			component.setBackground(IACNAConstants.CELL_ENABLED_FORMULA);
			component.setForeground(new Color(0, 0, 0));
		}
        return component;
    }

	public void setFormulaBuscada(List<Formula> formulaBuscada) {
		this.formulaBuscada = formulaBuscada;
	}

}