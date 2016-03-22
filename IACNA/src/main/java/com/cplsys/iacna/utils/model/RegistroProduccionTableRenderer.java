package com.cplsys.iacna.utils.model;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RegistroProduccionTableRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -8996880471040290795L;
    protected int interactiveColumn;

    public RegistroProduccionTableRenderer(int interactiveColumn) {
        this.interactiveColumn = interactiveColumn;
    }

    public Component getTableCellRendererComponent(JTable table,
       Object value, boolean isSelected, boolean hasFocus, int row,
       int column) {
        Component c = super.getTableCellRendererComponent(
        		table, value, isSelected, hasFocus, row, column);
        
        
        return c;
    }
}
