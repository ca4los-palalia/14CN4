package com.cplsys.iacna.app.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import net.miginfocom.swing.MigLayout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.app.ui.statusbar.IACNAStatusBar;
import com.cplsys.iacna.app.ui.workingarea.IACNAWorkingAreaPanel;
import com.cplsys.iacna.app.ui.workingarea.configuration.ConfiguracionPathPlcDienste;
import com.cplsys.iacna.app.ui.workingarea.configuration.ConfiguracionWorkArea;
import com.cplsys.iacna.domain.OrigenDeDatos;
import com.cplsys.iacna.domain.Privilegio;
import com.cplsys.iacna.services.FormulaService;
import com.cplsys.iacna.services.ISession;
import com.cplsys.iacna.services.OrigenDeDatosService;
import com.cplsys.iacna.services.PrivilegiosService;
import com.cplsys.iacna.services.UsuarioService;
import com.cplsys.iacna.ui.Logged;
import com.cplsys.iacna.ui.UpdatePLC;
import com.cplsys.iacna.utils.IACNAAbout;
import com.cplsys.iacna.utils.IACNAConstants;
import com.cplsys.iacna.utils.ifaces.IACNAIface;
import com.cplsys.iacna.utils.ifaces.LogginListener;

@Repository
public class IACNAFrame extends JFrame implements IACNAIface, LogginListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel cardPanel;
	private JPanel defaultPanel;
	private JPanel panelInferior;
	private JPanel panelIzquierdo;
	private JMenu archivo;
	private JMenu herramientas;
	private JMenu session;
	private JMenu about;
	private JMenu logoMenu;
	private JMenuItem iniciarSession;
	private JMenuItem terminarSession;
	private JMenuItem salirApp;
	private JMenuItem origenesDeDatos;
	private JMenuItem cargarServicioaPLC;
	private JMenuItem updateInfoPLC;
	private JMenuItem aboutApp;
	private CardLayout cardLayout;	
	private JScrollPane scrollPane;
	private Dimension d;
	private boolean privilegio;
	private ProcessBuilder builder;
	private ProcessBuilder builder2;
	private Process process;
	private Process process2;
	
	@Autowired
	private IACNAWorkingAreaPanel workingAreaPanel;
	@Autowired
	private IACNAStatusBar statusBarPanel;
	private JMenuBar toolBar;
	@Autowired
	private ConfiguracionWorkArea configuracionWorkArea;
	@Autowired
	private ConfiguracionPathPlcDienste configuracionPathPlcDienste;
	@Autowired
	private ISession sesionService;
	@Autowired
	private Logged logged;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PrivilegiosService privilegiosService;
	@Autowired
	private IACNAAbout aboutIACNA;
	@Autowired
	private OrigenDeDatosService origenDeDatosService;
	@Autowired
	private UpdatePLC updatePLC;
	
	
	
	@PostConstruct
	public void init() {
		initObjects();
		initProperties();
		createUI();
		initListeners();
	}

	@Override
	public void initProperties() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/media/Base.png")));
		this.setTitle("IAC WEIGHING SYSTEM");		
		this.setDefaultCloseOperation(0);
		
		salirApp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		origenesDeDatos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		cargarServicioaPLC.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		updateInfoPLC.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK));
		iniciarSession.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		terminarSession.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK));
		
		toolBar.add(archivo);
		herramientas.add(origenesDeDatos);
		herramientas.add(cargarServicioaPLC);
		herramientas.add(updateInfoPLC);
		updateInfoPLC.setVisible(false);
		toolBar.add(herramientas);
		session.add(iniciarSession);
		archivo.add(salirApp);
		session.add(terminarSession);
		toolBar.add(session);
		about.add(aboutApp);
		toolBar.add(about);
		logoMenu.setIcon(new ImageIcon(getClass().getResource("/media/IACLogo.png")));
		toolBar.add(logoMenu);
		setJMenuBar(toolBar);
		
		terminarSession.setEnabled(false);
		cargarServicioaPLC.setEnabled(false);
		
		salirApp.setIcon(new ImageIcon(getClass().getResource("/media/ExitSystem.png")));
		origenesDeDatos.setIcon(new ImageIcon(getClass().getResource("/media/OrigFolderData.png")));
		cargarServicioaPLC.setIcon(new ImageIcon(getClass().getResource("/media/devicePLC.png")));
		
		iniciarSession.setIcon(new ImageIcon(getClass().getResource("/media/StartSession.png")));
		terminarSession.setIcon(new ImageIcon(getClass().getResource("/media/OutSession.png")));
		
		cardPanel.add(defaultPanel, IACNAConstants.DEFAULT_LAYERED_PANE);
		cardPanel.add(scrollPane, IACNAConstants.WORK_AREA_LAYERED_PANE);
		panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
		
	}

	@Override
	public void initObjects() {
		cardPanel = new JPanel(new CardLayout());
		d = Toolkit.getDefaultToolkit().getScreenSize();
		toolBar = new JMenuBar();
		archivo = new JMenu("Archivo");
		herramientas = new JMenu("Herramientas");
		session = new JMenu("Acceso");
		about = new JMenu("About");
		logoMenu = new JMenu();
		
		iniciarSession = new JMenuItem("Iniciar Sesion");
		terminarSession = new JMenuItem("Finalizar Sesion");
		salirApp = new JMenuItem("Salir");
		origenesDeDatos = new JMenuItem("Origines de Datos");
		cargarServicioaPLC = new JMenuItem("Conectar a PLC");
		updateInfoPLC = new JMenuItem("Actualizar...");
		aboutApp = new JMenuItem("Acerca de");
		
		scrollPane = new JScrollPane(workingAreaPanel);//desactivar scroll
		
		defaultPanel = new JPanel(new MigLayout("insets 0 0 0 0"));
		
		panelIzquierdo = new JPanel();
		panelInferior = new JPanel(new FlowLayout());
		
	}

	@Override
	public void initListeners() {
		
		iniciarSession.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				logged.setVisible(true);
				if (logged.isAccessGranted()) {
					statusBarPanel.publishMessageOnStatusBar(
							"Bienvenido, usted ha sido autenticado como " + 
					logged.getUsuarioSistema());
					
					cardLayout = (CardLayout)cardPanel.getLayout();
					cardLayout.show(cardPanel, IACNAConstants.WORK_AREA_LAYERED_PANE);
					iniciarSession.setEnabled(false);
					terminarSession.setEnabled(true);
					cargarServicioaPLC.setEnabled(true);
					salirApp.setEnabled(false);
					logged.setAccessGranted(false);
					workingAreaPanel.sessionActivated();
					
					
					//------------------------
					
					
					
					boolean enablePrivileges = false;
					Set<Privilegio> privilegios = sesionService.getUsuario().getPrivilegios();
					for (Privilegio privilegio : privilegios) {
						if (privilegio.isSuperUser()) {
							enablePrivileges = true;
						}
					}
					
					if (enablePrivileges) {
						setPrivilegio(true);
						
					} else {
						setPrivilegio(false);
					}
					
					//---------------
					sessionActivated();
				}
			}
		});
		
		terminarSession.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				iniciarSession.setEnabled(true);
				terminarSession.setEnabled(false);
				cargarServicioaPLC.setEnabled(false);
				salirApp.setEnabled(true);
				cardLayout = (CardLayout)cardPanel.getLayout();
				cardLayout.show(cardPanel, IACNAConstants.DEFAULT_LAYERED_PANE);
				statusBarPanel.publishMessageOnStatusBar("Bienvenido al Sistema de Pesaje IACNA");
				workingAreaPanel.sessionDeactivated();
				sesionService.setUsuario(null);
				
				try {
					process.destroy();
					process2.destroy();
					sessionDeactivated();
				}catch(Exception e2) {
					
				}
			}
		});

		origenesDeDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sesionService.getUsuario() != null) {
					boolean enablePrivileges = false;
					Set<Privilegio> privilegios = sesionService.getUsuario().getPrivilegios();
					for (Privilegio privilegio : privilegios) {
						if (privilegio.isSuperUser()) {
							enablePrivileges = true;
						}
					}
					if (enablePrivileges) {
						configuracionWorkArea.setVisible(true);
						
					} else {
						JOptionPane.showMessageDialog(null, 
								"USTED NO TIENE LOS SUFICIENTES PRIVILEGIOS PARA \n" +
								"CONFIGURAR LOS ORIGENES DE DATOS.", 
								"",	JOptionPane.WARNING_MESSAGE);
					}
				} else {
					iniciarSession.doClick();
				}
			}
		});
		
		cargarServicioaPLC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String url = isPathPlcService()+"\\plcDienste.exe";
					String url2 = isPathPlcService()+"\\plcDienste.MBascula.exe";
					if(!url.equals("")) {
						builder = new ProcessBuilder(url);
						process = builder.start();
						
						builder2 = new ProcessBuilder(url2);
						process2 = builder2.start();
					}
					else
						JOptionPane.showMessageDialog(null,
								"ruta del servicio PLC no configurada\nreintente de nuevo",
								"COnfiguracion Cancelada", 
								JOptionPane.WARNING_MESSAGE);
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					
					JOptionPane.showMessageDialog(null,
							"Error al Iniciar el servicio [No disponible]\nAceptr para continuar...",
							"Archivo no encontrado:\n", 
							JOptionPane.ERROR_MESSAGE);
					
					try {
						String path ="";
						configuracionPathPlcDienste.setVisible(true);
						if (configuracionPathPlcDienste.isCentinela()) {
							List<OrigenDeDatos> origenDeDatos2 = origenDeDatosService.getAll();
							path = origenDeDatos2.get(1).getLocationProductos()+"\\plcDienste.exe";
							
							if(!path.equals("")) {
								builder = new ProcessBuilder(path);
								process = builder.start();
							}
							else
								JOptionPane.showMessageDialog(null,
										"ruta del servicio PLC no configurada\nreintente de nuevo",
										"COnfiguracion Cancelada", 
										JOptionPane.WARNING_MESSAGE);
						}
					}catch(IOException e2){}
				}
			}
		});
		
		updateInfoPLC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updatePLC.setVisible(true);
				
			}
		});
		
		aboutApp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aboutIACNA.setVisible(true);
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override public void windowClosing(WindowEvent e) {
				
				/*
				int opc = JOptionPane.showConfirmDialog(null, "Esta seguro de cerrar la aplicacion", 
						"xxxxxxxxxxxxxxxxxxxx", JOptionPane.YES_NO_OPTION);
				if (opc == JOptionPane.OK_OPTION) {
					if(localFile.delete()) {
						
					}System.exit(0);
				}
				*/
				
			}
		});
		
		salirApp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int opc = JOptionPane.showConfirmDialog(null, 
						"Confirmar salida de sistema","Salida del sistema", 
						JOptionPane.YES_NO_OPTION);
				if (opc == JOptionPane.OK_OPTION) {
					
					
					
					
					
					
					
					
					File localFile = new File("IACNA.properties");
					
					if(localFile.delete()) {
						localFile.deleteOnExit();
					}
					System.exit(0);
				}		
			}
		});
	}

	@Override
	public void createUI() {
		
		panelIzquierdo.add(cardPanel);
		panelInferior.add(statusBarPanel);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(panelInferior,BorderLayout.SOUTH);
		this.getContentPane().add(panelIzquierdo,BorderLayout.WEST);
		
		this.pack();
	}

	
	private void createLogFile() {
		
		FileWriter archivoConfig;
		FileInputStream fileConfig;
		Properties propiedades = new Properties();
		
		try {
			archivoConfig = new FileWriter("IACNA.properties");
			fileConfig = new FileInputStream("IACNA.properties");
			propiedades.load(fileConfig);
			propiedades.putAll(getConfig());
			propiedades.store(new FileOutputStream("IACNA.properties"), "Config");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private HashMap getConfig() {
		HashMap map = new HashMap();
		map.put("prueba", "www.google.com");
		return map;
	}
	
	public boolean isPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(boolean privilegio) {
		this.privilegio = privilegio;
	}
	
	private String isPathPlcService() {
		String path = "";
		
		List<OrigenDeDatos> origenDeDatos = origenDeDatosService.getAll();
		if(origenDeDatos != null && !origenDeDatos.isEmpty()) {
			
			try {
				if(origenDeDatos.get(1).getLocationProductos() != null 
						&& !origenDeDatos.get(1).getLocationProductos().equals("")) {
					//path = origenDeDatos.get(1).getLocationProductos()+"\\plcDienste.exe";
					path = origenDeDatos.get(1).getLocationProductos();
				}
			}
			catch (Exception e){
				configuracionPathPlcDienste.setVisible(true);
				if (configuracionPathPlcDienste.isCentinela()) {
					List<OrigenDeDatos> origenDeDatos2 = origenDeDatosService.getAll();
					//path = origenDeDatos2.get(1).getLocationProductos()+"\\plcDienste.exe";
					path = origenDeDatos2.get(1).getLocationProductos();
				}
			}
		}
		return path;
	}

	@Override
	public void sessionActivated() {
		
		
		
		Set<Privilegio> privilegio =  sesionService.getUsuario().getPrivilegios();

		for (Privilegio privilegio2 : privilegio) {
			
			if(privilegio2.isSuperUser()) {
				archivo.setVisible(true);
				herramientas.setVisible(true);
				origenesDeDatos.setVisible(true);
				cargarServicioaPLC.setVisible(true);
				
				break;
			}
			if(privilegio2.isProductionSupervisor()) {
				archivo.setVisible(true);
				herramientas.setVisible(true);
				origenesDeDatos.setVisible(false);
				cargarServicioaPLC.setVisible(true);
				
				break;
			}
			if(privilegio2.isProductionCalidad()) {
				archivo.setVisible(true);
				herramientas.setVisible(false);
				origenesDeDatos.setVisible(true);
				cargarServicioaPLC.setVisible(true);
			}
			if(privilegio2.isProductionHeads()) {
				
				archivo.setVisible(true);
				herramientas.setVisible(false);
				origenesDeDatos.setVisible(false);
				cargarServicioaPLC.setVisible(false);
			}
		}
		
	}

	@Override
	public void sessionDeactivated() {
		
	}

	
}
