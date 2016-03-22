package com.cplsys.iacna.utils.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class FormulaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -8463933567802774279L;

	private Vector columnNames;
	private Object[][] data;

	    
	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public String getColumnName(int col) {
	      return columnNames.get(col).toString();
    }
	
	@Override
	public Object getValueAt(int row, int column) {
		return data[row][column];
	}
	
	public Class getColumnClass(int c) {
	      return getValueAt(0, c).getClass();
    }
	
	/*
	public void addColumn(String columnName, List columnData) {
		columnNames.add(columnName);
	    colCount = columnNames.size();
	    if (columnData != null) {
	        for (int r = 0; r < localCache.size(); r++) {
	            ((List)localCache.get(r)).add(columnData.get(r));
	        }
	    } else {
	        System.out.println("Null columnData passed");
	    }
	    fireTableStructureChanged();
	} 
	*/
	public boolean isCellEditable(int row, int col) {
      //Note that the data/cell address is constant,
      //no matter where the cell appears onscreen.
      if (col < 1) {
        return true;
      } else {
        return false;
      }
    }

}
