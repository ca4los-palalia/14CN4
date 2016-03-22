package com.cplsys.iacna.app.ui.workingarea.formula;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.app.ui.statusbar.IACNAStatusBar;
import com.cplsys.iacna.domain.Formula;
import com.cplsys.iacna.domain.IACNADispatcher;
import com.cplsys.iacna.services.FormulaService;
import com.cplsys.iacna.services.IACNADispatcherService;
import com.cplsys.iacna.utils.ifaces.IACNAIface;
import com.cplsys.iacna.utils.ifaces.LogginListener;
import com.cplsys.iacna.utils.render.DispatcherListCellRender;

@Repository
public class ProccessStack extends JDialog implements IACNAIface, LogginListener {

	private static final long serialVersionUID = 3080067626921476872L;
	private JScrollPane stackScroller;
	private JList proccess;
	private JPanel container;
	private JPanel controlContainer;
	private JLabel titleLabel;
	private int stackedProccess;
	
	private JButton stopProccess;
	

	private DefaultListModel dataModel;

	@Autowired
	private IACNADispatcherService iacnaDispatcherService;
	@Autowired
	private IACNAStatusBar statusBar;
	@Autowired
	private FormulaService formulaService;
	
	@PostConstruct
	private void init() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}

	@Override
	public void initObjects() {
		dataModel = new DefaultListModel();
		container = new JPanel(new MigLayout("insets 0 0 0 0"));
		controlContainer = new JPanel(new MigLayout("insets 0 0 0 0"));
		proccess = new JList(dataModel);
		stackScroller = new JScrollPane(proccess);
		stopProccess = new JButton("Detener Proceso");
		
		List<IACNADispatcher> list = iacnaDispatcherService.getTaskInProgress();
		if (list != null) {
			for (IACNADispatcher iacnaDispatcher : list) {
				dataModel.addElement(iacnaDispatcher);
			}
		}
		if (dataModel.getSize() > 0) {
			statusBar.updateEmergencyStopManager();
		}
	}

	@Override
	public void initProperties() {
		setSize(300, 200);
		setLocationRelativeTo(null);
		setModal(true);
		proccess.setCellRenderer(new DispatcherListCellRender());
	}
	
	@Override
	public void initListeners() {
		stopProccess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!dataModel.isEmpty()) {
					IACNADispatcher iacna = (IACNADispatcher)proccess.getSelectedValue();
					Formula formulaRetrived = formulaService.getFormulaById(iacna.getFormula().getIdFormula());
					if (formulaRetrived != null) {
						if (formulaRetrived.isDespachada()) {
							JOptionPane.showMessageDialog(null, "La formula seleccionada ya ha sido despachada\n" +
																"el sistema la removera de la lista de produccion. ", "", 
																JOptionPane.INFORMATION_MESSAGE);
							dataModel.removeElementAt(proccess.getSelectedIndex());
							statusBar.updateEmergencyStopManager();
						} else {
							int opc = JOptionPane.showConfirmDialog(null,
									"Esta seguro de detener este proceso",  "",
									JOptionPane.YES_NO_OPTION);
							if (opc == JOptionPane.OK_OPTION) {
								iacna.getFormula().setCancelada(true);
								formulaService.saveFormula(iacna.getFormula());
								dataModel.removeElementAt(proccess.getSelectedIndex());
								statusBar.updateEmergencyStopManager();
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null,  "No hay procesos en operacion", "", 
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	public void pushToProccessStack(IACNADispatcher procesos) {
		dataModel.addElement(procesos);
	}
	
	@Override
	public void createUI() {
		JPanel panel = new JPanel(new MigLayout("insets 0 0 0 0"));
		panel.add(stackScroller, "width 300:300:300, wrap");
		panel.add(stopProccess);
		this.getContentPane().add(panel);
	}

	@Override
	public void sessionActivated() {
		refreshData();
	}

	@Override
	public void sessionDeactivated() {
		
	}

	public int getStackedProccess() {
		return dataModel.getSize();
	}
	
	public void refreshData() {
		
		dataModel.clear();
		List<IACNADispatcher> list = iacnaDispatcherService.getTaskInProgress();
		if (list != null) {
			for (IACNADispatcher iacnaDispatcher : list) {
				dataModel.addElement(iacnaDispatcher);
			}
			stopProccess.setEnabled(false);
		}
		if (dataModel.getSize() > 0) {
			statusBar.updateEmergencyStopManager();
		}
		
		if(dataModel.size()==0)
			stopProccess.setEnabled(false);
		else
			stopProccess.setEnabled(true);
	}

}
