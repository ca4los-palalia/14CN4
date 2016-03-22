package com.cplsys.iacna.app.ui.workingarea.produccion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.domain.Formula;
import com.cplsys.iacna.domain.RegistroProduccion;
import com.cplsys.iacna.services.FormulaService;
import com.cplsys.iacna.services.RegistroProduccionService;
import com.cplsys.iacna.ui.Logged;
import com.cplsys.iacna.utils.ifaces.IACNAIface;
import com.cplsys.iacna.utils.ifaces.LogginListener;
import com.cplsys.iacna.utils.render.ListaFormulaRender;


@Repository
public class FiltroFormulaWindow extends JDialog implements IACNAIface, LogginListener {

	private static final long serialVersionUID = -7376883526459902124L;

	private JRadioButton mesRadioButton;
	private JRadioButton diaRadioButton;
	private ButtonGroup periodoButtonGroup; 
	private JLabel formulaLabel;
	private JButton aceptarButton;
	private JButton cancelButton;
	private JScrollPane formulasScroll;
	private JList formulasList;
	private DefaultListModel listaFormulasModel;
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
	private Logged logged;
	
	@PostConstruct
	public void init() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}
	
	@Override
	public void initProperties() {
		this.setTitle("Formulas producidas");
		this.setSize(272, 120);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        periodoButtonGroup.add(diaRadioButton);
        periodoButtonGroup.add(mesRadioButton);
        formulasScroll.setViewportView(formulasList);
        formulasList.setFont(new Font("Calibri", Font.PLAIN, 11));
        aceptarButton.setFont(new Font("Calibri", Font.BOLD, 11));
        cancelButton.setFont(new Font("Calibri", Font.BOLD, 11));
        cleanComponents();
	}

	@Override
	public void initObjects() {
		mesRadioButton = new JRadioButton("Mes");
		diaRadioButton = new JRadioButton("Dia");
		periodoButtonGroup = new ButtonGroup();
		formulaLabel = new JLabel("Formulas:");
        aceptarButton = new JButton("Aceptar");
        cancelButton = new JButton("Cancelar");
        formulasScroll = new JScrollPane();
        formulasList = new JList();
        listaFormulasModel = new DefaultListModel();
        nombres = new Vector();
		tooltip = new HashMap<String, String>();
		formulasList.setModel(new DefaultListModel());
	}

	@Override
	public void initListeners() {
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setRegistroProduccions(null);
				cleanComponents();
				setRegistroProduccions(null);
				dispose();
			}
		});
		
		aceptarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mesRadioButton.getModel().isSelected()) {
					setNameAndIdFormula(formulasList.getSelectedIndex());
					cleanComponents();
					dispose();
				}
				else if(diaRadioButton.getModel().isSelected()) {
					setNameAndIdFormula(formulasList.getSelectedIndex());
					cleanComponents();
					dispose();
				}
				else if (formulasList.getModel().getElementAt(formulasList.getSelectedIndex()) == null) {
					JOptionPane.showMessageDialog(null,"Seleccione una formula de la lista");
				}
				else JOptionPane.showMessageDialog(null,"Seleccione un tipo de busqueda");
			}
		});
		
		mesRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addFormulasList(0);
			}
		});
		
		diaRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addFormulasList(1);
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
		cleanComponents();
	}

	@Override
	public void sessionDeactivated() {
		cleanComponents();
	}

	private void setNameAndIdFormula(int index) {
		String nombreFormula = "";
		String idFormula = "";
		
		for (int j = 0; j < nombres.size(); j++) {
			if(j == index) {
				
				boolean centinel=true;
				String campo="";
				
				for (int i = 0; i < nombres.get(j).toString().length(); i++) {
		            String character = nombres.get(j).toString().substring(i,(i+1));
					campo+=character;
		            
		            if(centinel && character.equals(" ")) {
		            	String extraccionId = campo.substring(0, (campo.length()-1));
		            	String id = "";
		            	for (int x=0; x < extraccionId.length(); x++) {
		            		  if (extraccionId.charAt(x) != ' ')
		            		    id += extraccionId.charAt(x);
		            		}
		            	idFormula = id;
		                campo="";
		                centinel = false;
		            }
		        }
				nombreFormula = campo;
				break;
			}
		}
		
		List<RegistroProduccion> registros = null;
		
		if(getTipoBusqueda() == 0) {
			registros = registroProduccionService.
					getRegistroProduccionByNombreFormula(
					nombreFormula, idFormula);
		}
		else if( getTipoBusqueda() == 1) {
			registros = registroProduccionService.
					getRegistroProduccionByNombreFormulaDiario(
					nombreFormula, idFormula);
		}
		setRegistroProduccions(registros);
	}
	
	public void addFormulasList(int type) {
		try {
			setTipoBusqueda(type);
	        listaFormulasModel.removeAllElements();	        
	        List<Formula> formulasToRender = new ArrayList<Formula>(); 
			List<RegistroProduccion> registros = formulaService.getAllFormulas(type, null, null);
			
			if(registros != null && !registros.isEmpty()) {
				int i=0;
				String id="";
				String nombre="";
				Calendar fecha = null;
				DefaultListModel model = new DefaultListModel();
				model.clear();
				for (RegistroProduccion formula: registros) {
					if(!id.equals(registros.get(i).getIdFormula())) {
						/*
						Formula formulaBuscada = 
								formulaService.getByIdAndName(Integer.parseInt(registros.get(i).getIdFormula()),
								registros.get(i).getFormula());
						if (formulaBuscada != null) {
							model.addElement(formulaBuscada.getNombre()+" "+formulaBuscada.getDescripcion());
							nombres.add(formulaBuscada.getIdFormula() +" "+formulaBuscada.getNombre());
							formulasToRender.add(formulaBuscada);							
						}
						*/
					}					
					id = registros.get(i).getIdFormula();
					fecha = registros.get(i).getFechaRegistro();
					i++;
				}				
				ListCellRenderer render = new ListaFormulaRender(formulasToRender);
				formulasList.setCellRenderer(render);
				formulasList.setModel(model);
				aceptarButton.setEnabled(true);
				formulasList.repaint();
			}
			else {
				if(registros == null) {
					DefaultListModel model = new DefaultListModel();
					model.clear();
					formulasList.setModel(model);
					aceptarButton.setEnabled(false);
					setRegistroProduccions(null);
				}
			}
			
		} catch (NullPointerException e) {
			aceptarButton.setEnabled(false);
			setRegistroProduccions(null);
		}
	}
	
	public void cleanComponents() {
		aceptarButton.setEnabled(false);
		cancelButton.setEnabled(true);
		nombres.removeAllElements();
		periodoButtonGroup.clearSelection();
		formulasList.setModel(new DefaultListModel());
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
