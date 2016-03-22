package com.cplsys.iacna.utils;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.cplsys.iacna.domain.IACNADispatcher;

public class DispatcherComboBoxModel extends AbstractListModel implements ComboBoxModel  {

	private static final long serialVersionUID = -1572404999734318157L;
	private List<IACNADispatcher> formulas;
	private IACNADispatcher selectedItem;
	
	public DispatcherComboBoxModel() {

	}
	
	public DispatcherComboBoxModel(List<IACNADispatcher> formulas) {
		this.formulas = formulas;
	}
	
	@Override
	public Object getElementAt(int idx) {
		return formulas.get(idx);
	}

	@Override
	public int getSize() {
		return formulas.size();
	}

	@Override
	public Object getSelectedItem() {
		return selectedItem;
	}

	@Override
	public void setSelectedItem(Object item) {
		selectedItem = (IACNADispatcher)item;
	}
	
	public void setListData(List<IACNADispatcher> formulas) {
		this.formulas = formulas;
	}
	
	public void removeElement(IACNADispatcher item) {
		formulas.remove(item);
	}
	
	public void removeElementAt(int idx) {
		formulas.remove(idx);
	}
	
	public void addElement(IACNADispatcher item) {
		formulas.add(item);
	}

}
