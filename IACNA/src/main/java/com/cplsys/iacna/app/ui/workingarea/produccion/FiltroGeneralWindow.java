package com.cplsys.iacna.app.ui.workingarea.produccion;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.swing.ButtonGroup;
import javax.swing.DefaultButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import net.miginfocom.swing.MigLayout;
import com.cplsys.iacna.domain.Formula;
import com.cplsys.iacna.domain.RegistroProduccion;
import com.cplsys.iacna.domain.Turno;
import com.cplsys.iacna.services.FormulaService;
import com.cplsys.iacna.services.RegistroProduccionService;
import com.cplsys.iacna.ui.Logged;
import com.cplsys.iacna.utils.ifaces.IACNAIface;
import com.cplsys.iacna.utils.ifaces.LogginListener;
import com.cplsys.iacna.utils.model.RegistroProduccionModel;

@Repository
public class FiltroGeneralWindow extends JDialog implements IACNAIface, LogginListener {

	private static final long serialVersionUID = -7376883526459902124L;
	
	private JPanel componentsPanel;
	private JLabel titleLabel;
	private JRadioButton mesRadioButton;
	private JRadioButton diaRadioButton;
	private ButtonGroup periodoButtonGroup;
	private JButton aceptarButton;
	private JButton cancelButton;
	
	private List<RegistroProduccion> list;
	
	
	@Autowired
	private RegistroProduccionService registroProduccionService;
	
	@PostConstruct
	public void init() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}
	
	@Override
	public void initProperties() {
		//this.setLayout(new MigLayout("insets 0 0 0 0"));
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setTitle("Formulas producidas");
		
		this.setMinimumSize(new Dimension(185, 80));
		this.setMaximumSize(new Dimension(185, 80));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        this.getContentPane().setLayout(new CardLayout());
        
        aceptarButton.setFont(new Font("calibri", Font.PLAIN, 11));
        cancelButton.setFont(new Font("calibri", Font.PLAIN, 11));
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("calibri", Font.BOLD, 14));
        mesRadioButton.setText("Mes");
        mesRadioButton.setOpaque(false);
        diaRadioButton.setText("Dia");
        diaRadioButton.setOpaque(false);
        periodoButtonGroup.add(diaRadioButton);
        periodoButtonGroup.add(mesRadioButton);
        
        componentsPanel.setBorder(new javax.swing.border.MatteBorder(new javax.swing.ImageIcon(getClass().getResource("/media/panelBackGroudGeneralFilter.png"))));
        
	}

	@Override
	public void initObjects() {
		componentsPanel = new JPanel();
		titleLabel = new JLabel("REPORTE GENERAL [filtro]");
		periodoButtonGroup = new ButtonGroup();        
		mesRadioButton = new JRadioButton();
		diaRadioButton = new JRadioButton();
		aceptarButton = new JButton("Aceptar");
        cancelButton = new JButton("Cancelar");
	}

	@Override
	public void initListeners() {
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				setList(null);
			}
		});
		
		aceptarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				performAceptarButton();
			}
		});
		
		aceptarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				performAceptarButton();
				
			}
		});
		
	}

	@Override
	public void createUI() {		
		
		componentsPanel.setLayout(null);
        componentsPanel.add(aceptarButton);
        aceptarButton.setBounds(10, 50, 73, 23);

        componentsPanel.add(cancelButton);
        cancelButton.setBounds(95, 50, 78, 23);

        componentsPanel.add(mesRadioButton);
        mesRadioButton.setBounds(10, 20, 70, 23);

        componentsPanel.add(diaRadioButton);
        diaRadioButton.setBounds(100, 20, 70, 23);

        componentsPanel.add(titleLabel);
        titleLabel.setBounds(0, 0, 180, 14);

        getContentPane().add(componentsPanel, "card2");

        pack();
		 
	}
	
	@Override
	public void sessionActivated() {
		
	}
	
	@Override
	public void sessionDeactivated() {
		
	}
	
	public List<RegistroProduccion> getList() {
		return list;
	}

	public void setList(List<RegistroProduccion> list) {
		this.list = list;
	}	

	private void performAceptarButton() {

		
		List<RegistroProduccion> registros;
		
		if (mesRadioButton.isSelected()) {
			registros = registroProduccionService.getAll(
					RegistroProduccion.CONSULTA_GENERAL_MES);
			if(registros.size() == 0) {
				setList(null);
			}
			else {
				setList(registros);
			}
		}
		else {
			registros = registroProduccionService.getAll(
					RegistroProduccion.CONSULTA_GENERAL_DIA);
			if(registros.size() == 0) {
				setList(null);
			}
			else {
				setList(registros);
			}
		}
		dispose();
		
	
	}
	
}
