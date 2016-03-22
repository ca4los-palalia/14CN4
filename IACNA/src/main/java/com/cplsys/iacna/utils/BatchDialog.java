package com.cplsys.iacna.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import net.miginfocom.swing.MigLayout;

import com.cplsys.iacna.utils.ifaces.IACNAIface;

public class BatchDialog extends JDialog implements IACNAIface {

	private static final long serialVersionUID = 4415702886985862695L;

	private int batchToProccess;
	private JLabel legend;
	private JSpinner spinnerBatch;
	private JLabel titlePanel;
	private JPanel globalContainer;
	private JButton accept;
	private JButton cancel;
	private boolean commitChanges;
	
	public BatchDialog() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}
	
	@Override
	public void initProperties() {
		setSize(350, 170);
		setModal(true);
		setLocationRelativeTo(null);
	}

	@Override
	public void initObjects() {
		spinnerBatch = new JSpinner();
		legend = new JLabel("Batch a procesar: ");
		titlePanel = new JLabel("Configuracion de Batchs");
		globalContainer = new JPanel(new MigLayout("insets 0 15 0 0"));
		accept = new JButton("Aceptar");
		cancel = new JButton("Cancelar");
	}

	@Override
	public void initListeners() {
		accept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commitChanges = true;
				batchToProccess = new Integer(spinnerBatch.getValue().toString());
				dispose();
			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commitChanges = false;
				dispose();
			}
		});
	}

	@Override
	public void createUI() {
		JPanel tPanel = new JPanel(new MigLayout("insets 0 150 0 0"));
		tPanel.add(titlePanel);
		globalContainer.add(tPanel, "wrap");
		globalContainer.add(legend);
		globalContainer.add(spinnerBatch, "wrap");
		JPanel controlPanel = new JPanel(new MigLayout("insets 0 100 0 0"));
		controlPanel.add(accept);
		controlPanel.add(cancel);
		globalContainer.add(controlPanel);
		this.add(globalContainer);
		setVisible(true);
	}

	public int getBatchToProccess() {
		return batchToProccess;
	}

	public void setBatchToProccess(int batchToProccess) {
		this.batchToProccess = batchToProccess;
	}

	public boolean isCommitChanges() {
		return commitChanges;
	}

	public void setCommitChanges(boolean commitChanges) {
		this.commitChanges = commitChanges;
	}

}
