package com.cplsys.iacna.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.domain.Privilegio;
import com.cplsys.iacna.domain.Usuario;
import com.cplsys.iacna.services.ISession;
import com.cplsys.iacna.services.UsuarioService;
import com.cplsys.iacna.utils.ifaces.IACNAIface;

/**
 * 
 * @author CPL
 */
@Repository
public class Logged extends JDialog implements IACNAIface {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ISession sessionService;

	private String user;
	private String pass;
	private String nombre;
	private boolean accessGranted;
	
	private JTextField userNameField;
	private JPasswordField userPasswordField;
	
	private JButton aceptar;
	private JButton cancelar;
	
	private JPanel fieldContainer;
	private JPanel secureLogoContainer;
	private JPanel controlContainer;
	
	//Administrador, Operador, Supervisor
	private String usuarioSistema;

	public Logged() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
		//initComponents();
		
	}
	
	@Override
	public void initProperties() {
		userNameField.setColumns(50);
		userPasswordField.setColumns(50);
		this.setTitle("acceso al sistema");
		setModal(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setSize(300, 137);
		setResizable(false);
		userNameField.setFont(new Font("Arial", Font.BOLD, 13));
		userPasswordField.setFont(new Font("Arial", Font.BOLD, 13));
		
		userNameField.setToolTipText("Ingrese su nombre de usuario");
		userPasswordField.setToolTipText("Ingrese su clave de acceso");
		aceptar.setToolTipText("Haga click aqui para verificar sus credenciales");
	}

	@Override
	public void initObjects() {	
		userNameField = new JTextField();
		userPasswordField = new JPasswordField();
		aceptar = new JButton("Aceptar");
		cancelar = new JButton("Cancelar");
		fieldContainer = new JPanel(new MigLayout("insets 10 0 0 0"));
		secureLogoContainer = new JPanel(new MigLayout("insets 5 10 0 0"));
		controlContainer = new JPanel(new MigLayout("insets 3 15 0 0"));
	}

	@Override
	public void initListeners() {
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				performLogin();
			}
		});

		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setUser("cancel");
				setPass("");
				dispose();
			}
		});
		
		userNameField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				performLogin();
			}
		});
		
		userPasswordField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				performLogin();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setUser("cancel");
				setPass("");
				dispose();
			}
		});
		
	}

	@Override
	public void createUI() {
		secureLogoContainer.add(new JLabel(
				new ImageIcon(getClass().getResource("/media/secure.png"))));
		fieldContainer.add(userNameField, "wrap");
		fieldContainer.add(userPasswordField, "wrap");
		controlContainer.add(aceptar);
		controlContainer.add(cancelar);
		JPanel container = new JPanel(new MigLayout());
		container.add(fieldContainer, "wrap");
		container.add(controlContainer);
		JPanel globalContainer = new JPanel(new MigLayout("insets 0 0 0 0"));
		globalContainer.add(secureLogoContainer);
		globalContainer.add(container);
		getContentPane().add(globalContainer);
	}
	

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		labelUser = new javax.swing.JLabel();
		labelPassword = new javax.swing.JLabel();
		passwordField = new javax.swing.JPasswordField();
		buttonAcept = new javax.swing.JButton();
		buttonCancel = new javax.swing.JButton();
		userField = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		labelUser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		labelUser.setIcon(new ImageIcon(getClass().getResource("/media/UserIdentified.png")));
		labelUser.setToolTipText("Nombre de Usuario");

		labelPassword.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		labelPassword.setIcon(new ImageIcon(getClass().getResource("/media/PassUserIdentified.png")));
		labelPassword.setToolTipText("Ingrese Contrasena");
		

		passwordField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				passwordFieldActionPerformed(evt);
			}
		});

		//buttonAcept.setText("Aceptar");
		buttonAcept.setIcon(new ImageIcon(getClass().getResource("/media/acceptIcon.png")));
		buttonAcept.setToolTipText("Aceptar");
		
		buttonAcept.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonAceptActionPerformed(evt);
			}
		});
		buttonAcept.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				buttonAceptKeyPressed(evt);
			}
		});

		//buttonCancel.setText("Cancelar");
		buttonCancel.setIcon(new ImageIcon(getClass().getResource("/media/cancelIcon.png")));
		buttonCancel.setToolTipText("Cancelar");
		buttonCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonCancelActionPerformed(evt);
			}
		});

		userField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				userFieldActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(labelPassword)
												.addComponent(labelUser))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		buttonAcept,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		79,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		buttonCancel,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE))
												.addComponent(userField)
												.addComponent(
														passwordField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														166,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(0, 29, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(labelUser)
												.addComponent(
														userField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(labelPassword)
												.addComponent(
														passwordField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(buttonAcept)
												.addComponent(buttonCancel))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	private void buttonAceptActionPerformed(java.awt.event.ActionEvent evt) {
		performLogin();
	}

	private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {
		setUser("cancel");
		setPass("");
		dispose();
	}

	private void buttonAceptKeyPressed(java.awt.event.KeyEvent evt) {

	}

	private void performLogin() {
		if (userNameField.getText().isEmpty()
				|| userPasswordField.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null,
					"POR FAVOR COMPLETE LA INFORMACION SOLICITADA", "",
					JOptionPane.WARNING_MESSAGE);
		} else {
			Usuario usuario = usuarioService.getUsuario(userNameField.getText(), 
					userPasswordField.getText());
			
			if (usuario != null) {
				sessionService.setUsuario(usuario);
				Set<Privilegio> privileges = usuario.getPrivilegios();
				for (Privilegio privilegio : privileges) {
					if (privilegio.isProductionCalidad()) {
						setUsuarioSistema("Usuario de Calidad");
					} 
					if (privilegio.isProductionSupervisor()) {
						setUsuarioSistema("Supervisor de Produccion");
					}
					if (privilegio.isSuperUser()) {
						setUsuarioSistema("Administrador del Sistema");
					}
					if (privilegio.isProductionHeads()) {
						setUsuarioSistema("Vista Ejecutiva");
					}
				}
				setUser(usuario.getIdUsuario().toString());
				if (usuario.getApellidoPaterno() != null && usuario.getApellidoMaterno() != null) {
					setNombre(usuario.getApellidoPaterno() + " "+ usuario.getApellidoMaterno());	
				}
				accessGranted = true;
				dispose();
				userNameField.setText("");
				userPasswordField.setText("");
			}
			else {
				userNameField.setText("");
				userPasswordField.setText("");
				JOptionPane.showMessageDialog(null, 
						"Acceso Denegado, El usuario o la clave son incorrectos", "", 
						JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		setUser("cancel");
		setPass("");
		dispose();
	}

	private void userFieldActionPerformed(java.awt.event.ActionEvent evt) {
		performLogin();
	}

	private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {
		performLogin();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean isAccessGranted() {
		return accessGranted;
	}

	public void setAccessGranted(boolean accessGranted) {
		this.accessGranted = accessGranted;
	}

	public String getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(String usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}

	// Variables declaration - do not modify
	private javax.swing.JButton buttonAcept;
	private javax.swing.JButton buttonCancel;
	private javax.swing.JLabel labelPassword;
	private javax.swing.JLabel labelUser;
	private javax.swing.JPasswordField passwordField;
	private javax.swing.JTextField userField;
	// End of variables declaration

	
}
