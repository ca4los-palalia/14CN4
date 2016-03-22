package com.cplsys.iacna.app.ui.workingarea.usuario;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.PostConstruct;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.domain.Privilegio;
import com.cplsys.iacna.domain.Usuario;
import com.cplsys.iacna.services.ISession;
import com.cplsys.iacna.services.OrigenDeDatosService;
import com.cplsys.iacna.services.PrivilegiosService;
import com.cplsys.iacna.services.UsuarioService;
import com.cplsys.iacna.utils.ifaces.IACNAIface;
import com.cplsys.iacna.utils.ifaces.LogginListener;

@Repository
public class UsuarioWorkArea extends JPanel implements IACNAIface, LogginListener {

	private static final long serialVersionUID = -1661765526152204659L;
	
	private JTextField nombreUsuarioField;
	private JTextField userNameField;
	private JTextField apellidoPaternoField;
	private JTextField apellidoMaternoField;
	private JPasswordField password;
	private JPasswordField repeatPassword;
	private JComboBox privilegiosComboBox;	
	private JButton accept;
	private JButton cancel;	
	private JLabel titleLabel;	
	private JLabel nombrePersonalLabel;
	private JLabel userNameLabel;
	private JLabel apellidoPaternoLabel;
	private JLabel apellidoMaternoLabel;
	private JLabel passwordLabel;
	private JLabel repeatPasswordLabel;
	private JLabel privilegiosLabel;	
	private JPanel titlePanel;
	private JPanel personalDataPanel;
	private JPanel autenticateDataPanel;
	private JPanel autenticateLabelsDataPanel;
	private JPanel autenticateFieldsDataPanel;
	private JPanel controlPanel;	
	private JCheckBox editUserData;
	private boolean type;
	
	@Autowired
	private ISession sessionService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PrivilegiosService privilegiosService;
	@Autowired
	private OrigenDeDatosService origenDeDatosService;
	
	@PostConstruct
	public void init() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}
	
	@Override
	public void initProperties() {
		setLayout(new MigLayout("insets 0 0 0 0"));
		configureElementsToPerformEdition(false);
		titlePanel.setFont(new Font("arial", Font.BOLD, 14));
		accept.setIcon(new ImageIcon(getClass().getResource("/media/acceptIcon.png")));
		cancel.setIcon(new ImageIcon(getClass().getResource("/media/cancelIcon.png")));
		
		DefaultComboBoxModel cModel = new DefaultComboBoxModel();
		cModel.addElement("Super usuario");
		cModel.addElement("Supervisor de produccion");
		cModel.addElement("Calidad");
		cModel.addElement("Ejecutivo");
		privilegiosComboBox.setModel(cModel);
	}

	@Override
	public void initObjects() {
		nombreUsuarioField = new JTextField(50);
		userNameField = new JTextField(50);
		apellidoPaternoField = new JTextField(50);
		apellidoMaternoField = new JTextField(50);
		password = new JPasswordField(50);
		repeatPassword = new JPasswordField(50);		
		accept = new JButton("Aceptar");
		cancel = new JButton("Cancelar");
		titleLabel = new JLabel("CONFIGURACION DE USUARIO");		
		nombrePersonalLabel = new JLabel("Nombre");
		userNameLabel = new JLabel("Usuario: ");
		apellidoPaternoLabel = new JLabel("Apellido paterno: ");
		apellidoMaternoLabel = new JLabel("Apellido materno: ");
		passwordLabel = new JLabel("Clave: ");
		repeatPasswordLabel = new JLabel("Repetir clave: ");
		editUserData = new JCheckBox("Editar informacion");
		privilegiosLabel = new JLabel("Privilegio: ");
		privilegiosComboBox = new JComboBox();
		
		titlePanel = new JPanel(new MigLayout("insets 15 300 0 0"));
		personalDataPanel = new JPanel(new MigLayout("insets 15 20 0 0 0"));
		autenticateLabelsDataPanel = new JPanel(new MigLayout("insets 0 0 0 0"));
		autenticateDataPanel = new JPanel(new MigLayout("insets 10 20 0 0"));
		autenticateFieldsDataPanel = new JPanel(new MigLayout("insets 0 21 0 0"));
		controlPanel = new JPanel(new MigLayout("insets 10 270 0 0"));
		
	}

	@Override
	public void initListeners() {
		accept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean goAway = true;
				if (!sessionService.getUsuario().getPassword().equals(password.getText())) {
					goAway = false;
					if (repeatPassword.getPassword().length == 0) {
						goAway = false;
						JOptionPane.showMessageDialog(null,
								"Por favor repita su clave de acceso",
								"", JOptionPane.WARNING_MESSAGE);
					} else {
						goAway = true;
					}
					if (goAway) {
						if (!repeatPassword.getText().isEmpty() &&
								!repeatPassword.getText().equals(password.getText())) {
							goAway = false;
							JOptionPane.showMessageDialog(null,
									"Las claves no coinciden, por favor vuelva a intentarlo",
									"", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				if (goAway) {
				
					String msg="La informacion se ha ";
					if (type) {
						sessionService.getUsuario().setApellidoMaterno(apellidoMaternoField.getText());
						sessionService.getUsuario().setApellidoPaterno(apellidoPaternoField.getText());
						sessionService.getUsuario().setNombre(nombreUsuarioField.getText());
						sessionService.getUsuario().setPassword(password.getText());
						sessionService.getUsuario().setUserName(userNameField.getText());
						usuarioService.saveUser(sessionService.getUsuario());
						msg+="actualizado correctamente";
					}
					else {
						Usuario nuevoUsuario = new Usuario();
						nuevoUsuario.setApellidoMaterno(apellidoMaternoField.getText());
						nuevoUsuario.setApellidoPaterno(apellidoPaternoField.getText());
						nuevoUsuario.setNombre(nombreUsuarioField.getText());
						nuevoUsuario.setPassword(password.getText());
						nuevoUsuario.setUserName(userNameField.getText());
						
						usuarioService.saveUser(nuevoUsuario);
						
						Privilegio privilegio = new Privilegio();
						
						if(privilegiosComboBox.getSelectedIndex() == 0) {
							privilegio.setSuperUser(true);
						}
						else if(privilegiosComboBox.getSelectedIndex() == 1){
							privilegio.setProductionSupervisor(true);
						}
						else if(privilegiosComboBox.getSelectedIndex() == 2){
							privilegio.setProductionCalidad(true);
						}
						else
							privilegio.setProductionHeads(true);
						
						privilegio.setUsuario(nuevoUsuario);
						//guardar privilegio
						msg+="guardado correctamente";
						privilegiosService.save(privilegio);
					}
					
					setUpUsarData();
					editUserData.setSelected(false);
					JOptionPane.showMessageDialog(null, msg, 
														"Configuracion de usuario", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setUpUsarData();
				editUserData.setSelected(false);
			}
		});
		
		editUserData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (editUserData.isSelected()) {
					configureElementsToPerformEdition(true);
				} else {
					configureElementsToPerformEdition(false);
				}
			}
		});
	}

	@Override
	public void createUI() {
		
		
		titlePanel.add(titleLabel);
		
		personalDataPanel.add(nombrePersonalLabel);
		personalDataPanel.add(nombreUsuarioField, "wrap");
		personalDataPanel.add(apellidoPaternoLabel);
		personalDataPanel.add(apellidoPaternoField, "wrap");
		personalDataPanel.add(apellidoMaternoLabel);
		personalDataPanel.add(apellidoMaternoField, "wrap");
		personalDataPanel.add(buildSeparator(), "width 660:660:660, span");
		//personalDataPanel.add(buildSeparator(), "width 620:620:620, span");

		autenticateLabelsDataPanel.add(userNameLabel, "wrap");
		autenticateLabelsDataPanel.add(passwordLabel, "wrap");
		autenticateLabelsDataPanel.add(repeatPasswordLabel, "wrap");
		autenticateLabelsDataPanel.add(privilegiosLabel, "wrap");
		
		autenticateFieldsDataPanel.add(userNameField, "wrap");
		autenticateFieldsDataPanel.add(password, "wrap");
		autenticateFieldsDataPanel.add(repeatPassword, "wrap");
		autenticateFieldsDataPanel.add(privilegiosComboBox, "wrap");

		autenticateDataPanel.add(autenticateLabelsDataPanel);
		autenticateDataPanel.add(autenticateFieldsDataPanel, "wrap");
		autenticateDataPanel.add(editUserData, "span");
		autenticateDataPanel.add(buildSeparator(), "width 660:660:660, span");
		//autenticateDataPanel.add(buildSeparator(), "width 620:620:620, span");
		
		controlPanel.add(accept);
		controlPanel.add(cancel);
		
		//this.setLayout(new MigLayout("insets 1 1 0 1, wrap 3", "", "[][][][]"));
		this.add(titlePanel, "wrap");
		this.add(personalDataPanel, "wrap");
		this.add(autenticateDataPanel, "wrap");
		this.add(controlPanel);
		
		
        
	}

	public void setUpUsarData() {
		
		if(type) {
			if (sessionService.getUsuario() != null) {
				nombreUsuarioField.setText(sessionService.getUsuario().getNombre());
				userNameField.setText(sessionService.getUsuario().getUserName());
				apellidoPaternoField.setText(sessionService.getUsuario().getApellidoPaterno());
				apellidoMaternoField.setText(sessionService.getUsuario().getApellidoMaterno());
				password.setText(sessionService.getUsuario().getPassword());
				repeatPassword.setText("");
				configureElementsToPerformEdition(false);	
				editUserData.setVisible(true);
				
			}
		}
		else {
			nombreUsuarioField.setText("");
			userNameField.setText("");
			apellidoPaternoField.setText("");
			apellidoMaternoField.setText("");
			password.setText("");
			repeatPassword.setText("");
			configureElementsToPerformEdition(true);
			editUserData.setVisible(false);
			
		}
		
	}
	
	private void configureElementsToPerformEdition(boolean perform) {
		nombreUsuarioField.setEnabled(perform);
		userNameField.setEnabled(perform);
		apellidoPaternoField.setEnabled(perform);
		apellidoMaternoField.setEnabled(perform);
		password.setEnabled(perform);
		repeatPassword.setEnabled(perform);
		accept.setEnabled(perform);
		cancel.setEnabled(perform);
		privilegiosComboBox.setVisible(perform);
		privilegiosLabel.setVisible(perform);
	}
	
	private JSeparator buildSeparator() {
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setForeground(Color.BLACK);		
		return separator;
	}

	@Override
	public void sessionActivated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDeactivated() {
		// TODO Auto-generated method stub
		
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}
	
	
}
