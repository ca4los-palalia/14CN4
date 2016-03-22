package com.cplsys.iacna.utils.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import com.cplsys.iacna.domain.RegistroProduccion;


public class RegistroProduccionModel extends AbstractTableModel  {

	private static final long serialVersionUID = 6653134635809474782L;
	
	private List<RegistroProduccion> list;
	private Vector<String> columnNames = new Vector<String>();
	
	public RegistroProduccionModel(List<RegistroProduccion> list) {
		this.list = list != null ? list : new ArrayList<RegistroProduccion>();
		fillColumnNames();
	}
	
	private void fillColumnNames() {
		columnNames.clear();
		int idx = getMaxColums();
		
		columnNames.add("Bascula");
		columnNames.add("MP");
		columnNames.add("Descripcion");
		columnNames.add("Especificacion");
		columnNames.add("Min");
		columnNames.add("Max");
		
		for (int i = 0; i < idx; i++) {
			columnNames.add("B" + (i + 1));
		}
	}
	
	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return list.get(rowIndex);
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames.elementAt(col);
	}
	
	private int getMaxColums() {
		int batchs = 0;
		int maxNumberOfBatchs = 0;
		if (list != null) {
			for (RegistroProduccion registroProduccion : list) {
				batchs = registroProduccion.getBatchProduccion().size();
				if(batchs > maxNumberOfBatchs) {
					maxNumberOfBatchs = batchs;
				}
			}
		}
		return maxNumberOfBatchs;
	}
	
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		list.set(row, (RegistroProduccion)value);
		fireTableCellUpdated(row, col);
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	public void insertData(List<RegistroProduccion> registroProduccion) {
		list = registroProduccion;
		fireTableDataChanged();
	}
	
	public void removeRow(int row) {
		list.remove(row);
		fireTableDataChanged();
	}
	
	public List<RegistroProduccion> getAllData() {
		return list; 
	}

}
