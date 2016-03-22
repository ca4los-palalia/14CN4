package com.cplsys.iacna.app.ui.statusbar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.PostConstruct;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.jdesktop.swingx.JXHyperlink;
import org.jdesktop.swingx.auth.LoginEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.app.ui.workingarea.formula.ProccessStack;
import com.cplsys.iacna.utils.Session;
import com.cplsys.iacna.utils.ifaces.LogginListener;

@Repository
public class IACNAStatusBar extends JPanel implements LogginListener {

	private static final long serialVersionUID = 1L;
	private JLabel statusBarText;
	private JProgressBar progressBar;
	private JXHyperlink stopProccess;
	private JXHyperlink refreshProccess;
	@Autowired
	private ProccessStack stack;
	@Autowired
	private Session session;

	@PostConstruct
	public void init() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}
	
	private void initObjects() {
		statusBarText = new JLabel("Bienvenido al sistema de pesaje IACNA");
		progressBar = new JProgressBar();
		stopProccess = new JXHyperlink();
		refreshProccess = new JXHyperlink();
	}
	
	private void initProperties() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		stopProccess.setText("Procesos en produccion");
		stopProccess.setToolTipText("Click para detener un proceso en curso");
		stopProccess.setVisible(false);
		updateEmergencyStopManager();
		stopProccess.setClickedColor(Color.RED);
		stopProccess.setUnclickedColor(Color.RED);
		refreshProccess.setVisible(false);
		refreshProccess.setText("Actualizar procesos");
		refreshProccess.setToolTipText("Click para actualizar lista de procesos en produccion");
		refreshProccess.setClickedColor(Color.BLUE);
		refreshProccess.setUnclickedColor(Color.BLUE);
		statusBarText.setHorizontalAlignment(10);
	}
	
	private void initListeners() {
		stopProccess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stack.setVisible(true);
			}
		});
		
		refreshProccess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stack.refreshData();
				
			}
		});
	}
	
	private void createUI() {
		this.add(statusBarText, "width 600:600:600");
		this.add(stopProccess, "width 30:30:30");
		this.add(refreshProccess, "width 30:30:30");
	}

	
	public void publishMessageOnStatusBar(String message) {
		statusBarText.setText(message);
	}
	
	public void updateEmergencyStopManager() {
		if (session != null) {
			if (session.getUsuario() != null) {
				if (stack != null) {
					if (stack.getStackedProccess() > 0) {
						stopProccess.setVisible(true);
						//refreshProccess.setVisible(true);
					} else {
						stopProccess.setVisible(false);
						//refreshProccess.setVisible(false);
					}
				}
			}
		}
	}

	@Override
	public void sessionActivated() {
		updateEmergencyStopManager();
		refreshProccess.setVisible(true);
	}

	@Override
	public void sessionDeactivated() {
		stopProccess.setVisible(false);
		refreshProccess.setVisible(false);
	}

		
}
