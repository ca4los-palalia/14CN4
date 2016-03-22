package com.cplsys.iacna.utils.render;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.cplsys.iacna.domain.IACNADispatcher;

public class DispatcherListCellRender extends JLabel implements
		ListCellRenderer {
	private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);
	private static final long serialVersionUID = 1075995941173844052L;

	public DispatcherListCellRender() {
		setOpaque(true);
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		IACNADispatcher dispatcher = (IACNADispatcher)value;
		setText(dispatcher.getFormula().getDescripcion());
		setToolTipText("Batch a procesar [" +
				String.valueOf(dispatcher.getFormula().getTotalBatchAProcesar()) + "]" + 
				", Batch procesados [" + 
				String.valueOf(dispatcher.getFormula().getBatchProcesado()) + "]");
		if (isSelected) {
			setBackground(HIGHLIGHT_COLOR);
			setForeground(Color.white);
		} else {
			setBackground(Color.white);
			setForeground(Color.black);
		}
		return this;
	}

}
