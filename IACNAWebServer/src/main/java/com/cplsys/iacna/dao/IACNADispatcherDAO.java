package com.cplsys.iacna.dao;

import java.util.Calendar;
import java.util.List;

import com.cplsys.iacna.domain.IACNADispatcher;

public interface IACNADispatcherDAO {

	public void saveIACNADispatcher(IACNADispatcher iacnaDispatcher);

	public void deleteIACNADispatcher(IACNADispatcher iacnaDispatcher);

	public void stopFromProduction(IACNADispatcher iacnaDispatcher);

	public void markAsDispatched(IACNADispatcher iacnaDispatcher);
	
	public List<IACNADispatcher> getAll();
	
	public List<IACNADispatcher> getIACNADispatcherBetweenDates(Calendar from, Calendar to);
	
	public List<IACNADispatcher> getAbortedProccess();
	
	public List<IACNADispatcher> getCompletedProccess();
	
	public List<IACNADispatcher> getTaskInProgress();
}
