package com.cplsys.iacna.app.ui.workingarea.configuration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import net.miginfocom.swing.MigLayout;

import com.cplsys.iacna.domain.OrigenDeDatos;
import com.cplsys.iacna.services.OrigenDeDatosService;
import com.cplsys.iacna.utils.ifaces.IACNAIface;

@Repository
public class ConfiguracionPathPlcDienste extends JDialog implements IACNAIface {

	private static final long serialVersionUID = 3553795816202445766L;

	private JLabel rutaLabel;
	private JTextField rutaTextField;
	private JButton abrirFileChooserButton;
	private JButton aceptarButton;
	private JPanel titleContainer;
	
	private boolean centinela;
	private String ruta;
	
	@Autowired
	private OrigenDeDatosService origenDeDatosService;
	private OrigenDeDatos origenDeDatosSession;
	
	@PostConstruct
	public void init() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}
	@Override
	public void initProperties() {
		rutaTextField.setEditable(false);
		setModal(true);
		setLocationRelativeTo(null);
		setSize(600, 100);
		setResizable(false);
		aceptarButton.setEnabled(false);
		if(!rutaTextField.equals("")) {
			aceptarButton.setEnabled(true);
		}
	}

	@Override
	public void initObjects() {
		rutaLabel = new JLabel("Ruta del servicio:");
		rutaTextField = new JTextField(100);
		abrirFileChooserButton = new JButton("Buscar");
		aceptarButton = new JButton("Aceptar");
		titleContainer = new JPanel(new MigLayout("insets 10 10 10 10"));
		
	}

	@Override
	public void initListeners() {
		abrirFileChooserButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooserProducts = new JFileChooser();
				chooserProducts.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int opc = chooserProducts.showOpenDialog(null);
				if (opc == JOptionPane.OK_OPTION) {
					if (chooserProducts.getSelectedFile().isDirectory()) {
						ruta = chooserProducts.getSelectedFile().getPath();
						rutaTextField.setText(chooserProducts.getSelectedFile().getPath() + "\\plcDienste.exe");
						aceptarButton.setEnabled(true);
					}
				}				
			}
		});
		
		aceptarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!rutaTextField.getText().equals("")) {
					
					origenDeDatosSession = new OrigenDeDatos();
					
					origenDeDatosSession.setDescripcion("Origen del Servicio PLC");
					origenDeDatosSession.setNombre("plcDienste");
					origenDeDatosSession.setLocationProductos(ruta);
					origenDeDatosSession.setLocationProductosPrepesados(ruta);
					origenDeDatosSession.setHostName(ruta);
					origenDeDatosSession.setHostIP(ruta);
					origenDeDatosService.saveOrigenDeDatos(origenDeDatosSession);
					setCentinela(true);
					dispose();
				}
			}
		});
		
	}

	@Override
	public void createUI() {
		titleContainer.add(rutaLabel);
		titleContainer.add(rutaTextField);
		titleContainer.add(abrirFileChooserButton);
		titleContainer.add(aceptarButton, "wrap");
		
		this.add(titleContainer);
		
	}
	public boolean isCentinela() {
		return centinela;
	}
	public void setCentinela(boolean centinela) {
		this.centinela = centinela;
	}
	
	

}
