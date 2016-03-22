package com.cplsys.iacna.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.imageio.spi.RegisterableService;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.domain.Formula;
import com.cplsys.iacna.domain.RegistroProduccion;
import com.cplsys.iacna.services.FormulaService;
import com.cplsys.iacna.services.RegistroProduccionService;
import com.cplsys.iacna.utils.ifaces.IACNAIface;
import com.cplsys.iacna.utils.model.RegistroProduccionModel;

@Repository
public class UpdatePLC extends JDialog implements IACNAIface  {

	private static final long serialVersionUID = 6551974689441066377L;
	
	private JPanel headPanel;
	private JPanel bodyPanel;
	private JPanel footerPanel;
	private JLabel loadFormulaLabel;
	private JLabel InformationFormula;
	private JTextField idFormulaField;
	private JTextField nameFormulaField;
	private JButton loadInfoButton;
	private JButton updateInfoButton;
	private JTable ingredientesTable;
	
	@Autowired
	private FormulaService formulaService;
	@Autowired
	private RegistroProduccionService produccionService;
	
	@PostConstruct
	public void init() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}
	
	@Override
	public void initObjects() {
		headPanel = new JPanel(new BorderLayout());
		
		bodyPanel = new JPanel();
		footerPanel = new JPanel();
		loadFormulaLabel = new JLabel("Cargar Formula");
		InformationFormula = new JLabel("Datos de la formula");
		loadInfoButton = new JButton("Cargar");
		updateInfoButton = new JButton("Actualizar");
		idFormulaField = new JTextField("",20);
		nameFormulaField = new JTextField("",20);
		ingredientesTable = new JTable();
	}
	
	@Override
	public void initProperties() {
		/*
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setSize(300, 137);
		setResizable(false);
		*/
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		this.setSize(500, 500);
		
		setModal(true);
	}

	@Override
	public void createUI() {
		headPanel.add(loadFormulaLabel);
		headPanel.add(loadInfoButton);
		
		bodyPanel.add(idFormulaField);
		bodyPanel.add(nameFormulaField);
		bodyPanel.add(ingredientesTable);
		
		
		this.add(headPanel);
		this.add(bodyPanel);
		
		
		
		
	}

	@Override
	public void initListeners() {
		loadInfoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Formula> formulaBuscada = formulaService.getFormulaOnLine();
				
				List<RegistroProduccion> registroProduccions = 
						produccionService.
						getRegistroProduccionByNombreFormulaDiario(
								formulaBuscada.get(0).getNombre(),
								formulaBuscada.get(0).getIdFormula().toString());
				
				idFormulaField.setText(formulaBuscada.get(0).getIdFormula().toString());
				nameFormulaField.setText(formulaBuscada.get(0).getNombre());
				
				//ingredientesTable.setModel(registroProduccions);
			}
		});
		
	}

	

}
