package com.cplsys.iacna.app.ui.workingarea.produccion;

/*
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.swing.DefaultButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
import com.cplsys.iacna.services.TurnoService;
import com.cplsys.iacna.ui.Logged;
import com.cplsys.iacna.utils.ifaces.IACNAIface;
import com.cplsys.iacna.utils.ifaces.LogginListener;



@Repository
public class FiltroTurnosWindow extends JDialog implements IACNAIface, LogginListener {

	private static final long serialVersionUID = -7376883526459902124L;

	private JLabel formulaLabel;
	private JButton aceptarButton;
	private JButton cancelButton;
	//private JButton refreshButton;
	private JComboBox turnosComboBox;
	private DefaultComboBoxModel cModel;
	
	private String data;
	private Vector nombres;
	
	@Autowired
	private RegistroProduccionService registroProduccionService;
	@Autowired
	private Logged logged;
	@Autowired
	private TurnoService turnoService;
	
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
		this.setTitle("Formulas producidas por turno");
		this.setSize(272, 120);
        this.setLocationRelativeTo(null);
        turnosComboBox.removeAll();
        //refreshButton.setToolTipText("Actualizar lista de formulas");
        //refreshButton.setIcon(new ImageIcon(getClass().getResource("/media/ListFormulaWindowRefresh.PNG")));
        
	}

	@Override
	public void initObjects() {
		formulaLabel = new JLabel("Formulas:");
        aceptarButton = new JButton("Aceptar");
        cancelButton = new JButton("Cancelar");
        //refreshButton = new JButton();
        
		turnosComboBox = new JComboBox();
		cModel = new DefaultComboBoxModel();
		
		nombres = new Vector();
	}

	@Override
	public void initListeners() {
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setData("");
				cleanComponents();
				dispose();
			}
		});
		
		aceptarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setData(turnosComboBox.getItemAt(turnosComboBox.getSelectedIndex()).toString());
				cleanComponents();
				dispose();
			}
		});
		

	}

	@Override
	public void createUI() {		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formulaLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(aceptarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton))
                    .addComponent(turnosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(turnosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formulaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarButton)
                    .addComponent(cancelButton))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
		 
	}
	
	@Override
	public void sessionActivated() {
		fillCB();
	}

	@Override
	public void sessionDeactivated() {
		cleanComponents();
		
	}

	private String getNameFormula(int index) {
		String salida="";
		
		for (int j = 0; j < nombres.size(); j++) {
			if(j == index) {
				salida = nombres.get(j).toString();
				break;
			}
		}
		return salida;
	}
	public void fillCB(){
		try {
			cModel.removeAllElements();
			List<Turno> turnos = turnoService.getAll();
			
			//List<RegistroProduccion> registros = registroProduccionService.getAll();
			if(turnos != null && !turnos.isEmpty()) {
				int i=0;
				
				for (Turno turno : turnos) {
					cModel.addElement(turno.getNombre());
				}
				turnosComboBox.setModel(cModel);
			}
			else {
				if(turnos == null) {
					aceptarButton.setEnabled(false);
					setData("");
				}
			}
			
		} catch (NullPointerException e) {
			aceptarButton.setEnabled(false);
			setData("");
			System.err.println(e.getMessage());
		}
	}
	
	public void cleanComponents(){
		turnosComboBox.removeAll();
		aceptarButton.setEnabled(true);
		cancelButton.setEnabled(true);
		nombres.removeAllElements();
		cModel.removeAllElements();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
 */

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.annotation.PostConstruct;
import javax.swing.ButtonGroup;
import javax.swing.DefaultButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
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
import com.cplsys.iacna.services.TurnoService;
import com.cplsys.iacna.ui.Logged;
import com.cplsys.iacna.utils.ifaces.IACNAIface;
import com.cplsys.iacna.utils.ifaces.LogginListener;
import com.cplsys.iacna.utils.render.ListaFormulaRender;

@Repository
public class FiltroTurnosWindow extends JDialog implements IACNAIface, LogginListener {

	private static final long serialVersionUID = -7376883526459902124L;

	private JRadioButton mesRadioButton;
	private JRadioButton diaRadioButton;
	private ButtonGroup periodoButtonGroup; 
	private JLabel formulaLabel;
	private JButton aceptarButton;
	private JButton cancelButton;
	private JScrollPane formulasScroll;
	private JList turnosList;
	private DefaultListModel listaTurnosModel;
	private HashMap<String, String> tooltip;
	private ListaFormulaRender listaRender;
	
	private int tipoBusqueda;
	private Vector nombres;
	private List<RegistroProduccion> registroProduccions;
	
	@Autowired
	private RegistroProduccionService registroProduccionService;
	@Autowired
	private FormulaService formulaService;
	@Autowired
	private TurnoService turnoService;
	
	@PostConstruct
	public void init() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}
	
	@Override
	public void initProperties() {
		this.setTitle("Produccion por turno");
		this.setSize(272, 120);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        periodoButtonGroup.add(diaRadioButton);
        periodoButtonGroup.add(mesRadioButton);
        formulasScroll.setViewportView(turnosList);
        turnosList.setFont(new Font("Calibri", Font.PLAIN, 11));
        aceptarButton.setFont(new Font("Calibri", Font.BOLD, 11));
        cancelButton.setFont(new Font("Calibri", Font.BOLD, 11));
	}

	@Override
	public void initObjects() {
		mesRadioButton = new JRadioButton("Mes");
		diaRadioButton = new JRadioButton("Dia");
		periodoButtonGroup = new ButtonGroup();
		formulaLabel = new JLabel("Turnos:");
        aceptarButton = new JButton("Aceptar");
        cancelButton = new JButton("Cancelar");
        formulasScroll = new JScrollPane();
        turnosList = new JList();
        listaTurnosModel = new DefaultListModel();
        nombres = new Vector();
		tooltip = new HashMap<String, String>();		
	}

	@Override
	public void initListeners() {
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setRegistroProduccions(null);
				cleanComponents();
				dispose();
			}
		});
		
		aceptarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mesRadioButton.getModel().isSelected()) {
					processQueryTurnos();
					dispose();
				}
				else if(diaRadioButton.getModel().isSelected()) {
					processQueryTurnos();
					dispose();
				}
				else if (turnosList.getModel().getElementAt(turnosList.getSelectedIndex()) == null) {
					JOptionPane.showMessageDialog(null,"Seleccione una formula de la lista");
				}
				else JOptionPane.showMessageDialog(null,"Seleccione un tipo de busqueda");
			}
		});
		
		mesRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTurnosList(0);
			}
		});
		
		diaRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTurnosList(1);
			}
		});
	}

	@Override
	public void createUI() {		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mesRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(diaRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(formulaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(formulasScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(aceptarButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mesRadioButton)
                    .addComponent(diaRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formulasScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(formulaLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
		 
	}
	
	@Override
	public void sessionActivated() {
		
	}

	@Override
	public void sessionDeactivated() {
		cleanComponents();
		
	}

	public void processQueryTurnos() {

		List<RegistroProduccion> registros = null;
		
		if(getTipoBusqueda() == 0) {
			registros = registroProduccionService.getRegistroProduccionByTurno(0, turnosList.getSelectedValue().toString());
		}
		else if( getTipoBusqueda() == 1) {
			registros = registroProduccionService.getRegistroProduccionByTurno(1, turnosList.getSelectedValue().toString());
		}
		if (registros.isEmpty()) {
			setRegistroProduccions(null);
		}
		else {
			setRegistroProduccions(registros);
		}
		
	}
	
	public void addTurnosList(int type) {
		
		try {
			setTipoBusqueda(type);
			listaTurnosModel.removeAllElements();
			List<Turno> turnos = turnoService.getAll();
			if(turnos != null && !turnos.isEmpty()) {
				int i=0;
				
				for (Turno turno : turnos) {
					listaTurnosModel.addElement(turno.getNombre());
				}
				turnosList.setModel(listaTurnosModel);
				aceptarButton.setEnabled(true);
			}
			else {
				if(turnos == null) {
					aceptarButton.setEnabled(false);
				}
			}
		} catch (NullPointerException e) {
			aceptarButton.setEnabled(false);
			System.err.println(e.getMessage());
		}
	}
	
	public void cleanComponents(){
		listaTurnosModel.removeAllElements();
		aceptarButton.setEnabled(true);
		cancelButton.setEnabled(true);
		nombres.removeAllElements();
		mesRadioButton.getModel().setPressed(false);
		diaRadioButton.getModel().setPressed(false);
		setRegistroProduccions(null);
	}

	public List<RegistroProduccion> getRegistroProduccions() {
		return registroProduccions;
	}

	public void setRegistroProduccions(List<RegistroProduccion> registroProduccions) {
		this.registroProduccions = registroProduccions;
	}

	public int getTipoBusqueda() {
		return tipoBusqueda;
	}

	public void setTipoBusqueda(int tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}

	
	
}
