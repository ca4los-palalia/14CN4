package com.cplsys.iacna.utils.model;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class RegistroProduccionTableModelListener implements TableModelListener {

	@Override
	public void tableChanged(TableModelEvent evt) {
        if (evt.getType() == TableModelEvent.UPDATE) {
            int column = evt.getColumn();
            int row = evt.getFirstRow();
            System.out.println("row: " + row + " column: " + column);
            //table.setColumnSelectionInterval(column + 1, column + 1);
            //table.setRowSelectionInterval(row, row);
        }
    

	}

}
