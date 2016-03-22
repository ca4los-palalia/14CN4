package com.cplsys.iacna.launcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.services.BasculaService;
import com.cplsys.iacna.ui.IACNAMainFrame;
import com.cplsys.iacna.utils.DefaultDBValues;

@Repository
public class IACNA implements Runnable {
	@Autowired
	private BasculaService usuario;
	@Autowired
	private DefaultDBValues defaultDBValues;

	@Autowired
	private IACNAMainFrame iacnaMainFrame;

	@Override
	public void run() {
			initApp();
	}

	private void initApp() {
		iacnaMainFrame.init();
	}
}
