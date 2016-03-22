package com.cplsys.iacna.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsys.iacna.dao.IACNADispatcherDAO;
import com.cplsys.iacna.domain.IACNADispatcher;
@Service
public class IACNADispatcherService {

	@Autowired
	IACNADispatcherDAO iacnaDispatcherDAO;
	
	public void saveIACNADispatcher(IACNADispatcher iacnaDispatcher) {
		iacnaDispatcherDAO.saveIACNADispatcher(iacnaDispatcher);
	}

	public void deleteIACNADispatcher(IACNADispatcher iacnaDispatcher) {
		iacnaDispatcherDAO.deleteIACNADispatcher(iacnaDispatcher);
	}

	public void stopFromProduction(IACNADispatcher iacnaDispatcher) {
		iacnaDispatcherDAO.stopFromProduction(iacnaDispatcher);
	}

	public void markAsDispatched(IACNADispatcher iacnaDispatcher) {
		iacnaDispatcherDAO.markAsDispatched(iacnaDispatcher);
	}
	
	public List<IACNADispatcher> getAll() {
		return iacnaDispatcherDAO.getAll();
	}
	
	public List<IACNADispatcher> getIACNADispatcherBetweenDates(Calendar from, Calendar to) {
		return iacnaDispatcherDAO.getIACNADispatcherBetweenDates(from, to);
	}
	
	public List<IACNADispatcher> getAbortedProccess() {
		return iacnaDispatcherDAO.getAbortedProccess();
	}
	
	public List<IACNADispatcher> getCompletedProccess() {
		return iacnaDispatcherDAO.getCompletedProccess();
	}
	
	public List<IACNADispatcher> getTaskInProgress(){
		return iacnaDispatcherDAO.getTaskInProgress();
	}

}
