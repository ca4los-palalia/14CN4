package com.cplsys.iacna.app.ui.workingarea.produccion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXDatePicker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.domain.Formula;
import com.cplsys.iacna.domain.RegistroProduccion;
import com.cplsys.iacna.services.FormulaService;
import com.cplsys.iacna.services.RegistroProduccionService;
import com.cplsys.iacna.utils.ifaces.IACNAIface;
import com.cplsys.iacna.utils.render.ListaFormulaRender;


@Repository
public class FiltroPeriodoWindow extends JDialog implements IACNAIface {

	private static final long serialVersionUID = 7723781360902756335L;

	private JPanel contenedorPanel;
	private JLabel inicioLabel;
	private JLabel finalLabel;
	private JXDatePicker fechaInicial;
	private JXDatePicker fechaFinal;
	private JLabel listaFormulasLabel;
	private JList formulasList;
	private DefaultListModel listaFormulasModel;
	private JScrollPane formulasScroll;
	private JButton buscarButton;
	private JButton aceptarButton;
	private JButton cancelarButton;
	private DefaultComboBoxModel modelCB;
	private Vector nombres;
	private int tipoBusqueda;
	
	private List<RegistroProduccion> registroProduccions;
	
	@Autowired
	private RegistroProduccionService registroProduccionService;
	@Autowired
	private FiltroFormulaWindow  filtroFormulaWindow;
	@Autowired
	private FiltroTurnosWindow filtroTurnosWindow;
	@Autowired
	private FormulaService formulaService;
	
	
	@PostConstruct
	public void init(){
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}
	
	@Override
	public void initProperties() {
		this.setSize(315, 145);
		
		this.setTitle("Reporte por periodo");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
		this.setModal(true);
		
		this.setSize(320, 300);
		
		buscarButton.setIcon(new ImageIcon(getClass().getResource(
				"/media/search.gif")));
		buscarButton.setToolTipText("Buscar Formulas");
		formulasScroll.add(formulasList);
		formulasScroll.setViewportView(formulasList);
        formulasList.setFont(new Font("Calibri", Font.PLAIN, 11));
        listaFormulasLabel.setVisible(false);
        aceptarButton.setEnabled(false);
	}

	@Override
	public void initObjects() {
		contenedorPanel = new JPanel( new MigLayout("insets 0 0 0 0"));
		inicioLabel = new JLabel("De:");
		finalLabel = new JLabel("A:");
		fechaInicial = new JXDatePicker(new Date());
		fechaFinal = new JXDatePicker(new Date());
		listaFormulasLabel = new JLabel("Formulas:");
		formulasScroll = new JScrollPane();
		formulasList = new JList();
		listaFormulasModel = new DefaultListModel();
		buscarButton = new JButton();
		aceptarButton = new JButton("Aceptar");
		cancelarButton = new JButton("Cancelar");
		modelCB = new DefaultComboBoxModel();
		nombres = new Vector();
	}

	@Override
	public void initListeners() {
		aceptarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setNameAndIdFormula(formulasList.getSelectedIndex());
				cleanComponents();
				dispose();
			}
		});
		
		cancelarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setRegistroProduccions(null);
				cleanComponents();
				dispose();
			}
		});
		
		buscarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addFormulasList(2);
			}
		});
	}

	@Override
	public void createUI() {
		
		JPanel periodoBusqueda = new JPanel(new MigLayout("insets 10 5 0 0"));
		JPanel filtroBusqueda = new JPanel(new MigLayout("insets 10 5 0 0"));
		JPanel controlesBusqueda = new JPanel(new MigLayout("insets 10 55 0 0"));
		
		
		periodoBusqueda.add(inicioLabel, "width 20 : 20 : 20 ");
		periodoBusqueda.add(fechaInicial, "width 100 : 100 : 100 ");
		periodoBusqueda.add(finalLabel, "width 20 : 20 : 20 ");
		periodoBusqueda.add(fechaFinal, "width 100 : 100 : 100");
		periodoBusqueda.add(buscarButton, "height 25 : 25 : 25");
		
		
		contenedorPanel.add(periodoBusqueda, "span, wrap");
		
		//--------------
		filtroBusqueda.add(listaFormulasLabel, "width 100 : 100 : 100 ");
		filtroBusqueda.add(formulasScroll, "width 188 : 188 : 188");
		contenedorPanel.add(filtroBusqueda, "wrap");
		//--------------
		
		controlesBusqueda.add(aceptarButton, "width 100 : 100 : 100");
		controlesBusqueda.add(cancelarButton, "width 100 : 100 : 100");
		contenedorPanel.add(controlesBusqueda, "span");
		
		this.add(contenedorPanel);
	}
	
	private Calendar convertDate(Date dateIn){
		Calendar temp= Calendar.getInstance();
		temp.setTime(dateIn);
		return temp;
	}

	public List<RegistroProduccion> getRegistroProduccions() {
		return registroProduccions;
	}

	public void setRegistroProduccions(List<RegistroProduccion> registroProduccions) {
		this.registroProduccions = registroProduccions;
	}
	
	/*
	@SuppressWarnings("unchecked")
	public void addFormulasList(int type) {
		try {
			setTipoBusqueda(type);
	        listaFormulasModel.removeAllElements();	        
	        List<Formula> formulasToRender = new ArrayList<Formula>(); 
			List<RegistroProduccion> registros = formulaService.
					getAllFormulas(type, convertDate(fechaInicial.getDate()),convertDate(fechaFinal.getDate()));
			
			if(registros != null && !registros.isEmpty()) {
				String id="";
				String nombre="";
				Calendar fecha = null;
				DefaultListModel model = new DefaultListModel();
				model.clear();
				for (RegistroProduccion formula: registros) {					
					if(!id.equals(formula.getIdFormula())) {
						Formula formulaDB = formulaService.getByIdAndName(Integer.parseInt(formula.getIdFormula()), 
								formula.getFormula());
						if (formulaDB != null) {
							model.addElement(formulaDB.getNombre()+" "+ formulaDB.getDescripcion());
							nombres.add(formulaDB.getIdFormula() +" "+ formulaDB.getNombre());
							formulasToRender.add(formulaDB);	
						}
					}					
					id = formula.getIdFormula();
					fecha = formula.getFechaRegistro();
				}				
				ListCellRenderer render = new ListaFormulaRender(formulasToRender);
				formulasList.setCellRenderer(render);
				formulasList.setModel(model);
				aceptarButton.setEnabled(true);
				listaFormulasLabel.setVisible(true);
				formulasList.repaint();
			}
			else {
				if(registros == null) {
					DefaultListModel model = new DefaultListModel();
					model.clear();
					formulasList.setModel(model);
					aceptarButton.setEnabled(false);
					listaFormulasLabel.setVisible(false);
					setRegistroProduccions(null);
				}
			}
			
		} catch (NullPointerException e) {
			aceptarButton.setEnabled(false);
			setRegistroProduccions(null);
		}
	}
	*/
	@SuppressWarnings("unchecked")
	public void addFormulasList(int type) {
		try {
			setTipoBusqueda(type);
	        listaFormulasModel.removeAllElements();	        
	        List<Formula> formulasToRender = new ArrayList<Formula>(); 
			List<RegistroProduccion> registros = formulaService.
					getAllFormulas(type, convertDate(fechaInicial.getDate()),convertDate(fechaFinal.getDate()));
			
			if(registros != null && !registros.isEmpty()) {
				int i=0;
				String id="";
				String nombre="";
				Calendar fecha = null;
				DefaultListModel model = new DefaultListModel();
				model.clear();
				for (RegistroProduccion formula: registros) {
					if(!id.equals(registros.get(i).getIdFormula())) {
						
					
						
						
						List<Formula> formulaBuscada = formulaService.getByIdAndName(Integer.parseInt(registros.get(i).getIdFormula()), registros.get(i).getFormula());
						
						if (formulaBuscada!=null) {
						model.addElement(formulaBuscada.get(0).getNombre()+" "+formulaBuscada.get(0).getDescripcion());
						nombres.add(formulaBuscada.get(0).getIdFormula() +" "+formulaBuscada.get(0).getNombre());
						formulasToRender.add(formulaBuscada.get(0));
						}
					}					
					id = registros.get(i).getIdFormula();
					fecha = registros.get(i).getFechaRegistro();
					i++;
				}
				
				if (formulasToRender.size()!=0) {
					ListCellRenderer render = new ListaFormulaRender(formulasToRender);
					formulasList.setCellRenderer(render);
					formulasList.setModel(model);
					aceptarButton.setEnabled(true);
					listaFormulasLabel.setVisible(true);
					formulasList.repaint();
				}
				
			}
			else {
				if(registros == null) {
					DefaultListModel model = new DefaultListModel();
					model.clear();
					formulasList.setModel(model);
					aceptarButton.setEnabled(false);
					listaFormulasLabel.setVisible(false);
					setRegistroProduccions(null);
				}
			}
			
		} catch (NullPointerException e) {
			aceptarButton.setEnabled(false);
			setRegistroProduccions(null);
		}
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
		
		registros = registroProduccionService.
				getRegistroProduccionByNombreFormula(
				nombreFormula, idFormula);
		
		setRegistroProduccions(registros);
	}
	
	public void cleanComponents() {
		aceptarButton.setEnabled(false);
		cancelarButton.setEnabled(true);
		nombres.removeAllElements();
		formulasList.setModel(new DefaultListModel());
		listaFormulasLabel.setVisible(false);
		fechaInicial.setDate(new Date());
        fechaFinal.setDate(new Date());
	}
	
	public int getTipoBusqueda() {
		return tipoBusqueda;
	}

	public void setTipoBusqueda(int tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}
}

