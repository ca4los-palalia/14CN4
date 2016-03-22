package com.cplsys.iacna.app.ui.workingarea.bascula;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import net.miginfocom.swing.MigLayout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.app.ui.statusbar.IACNAStatusBar;
import com.cplsys.iacna.domain.Bascula;
import com.cplsys.iacna.domain.Usuario;
import com.cplsys.iacna.services.BasculaService;
import com.cplsys.iacna.services.UsuarioService;
import com.cplsys.iacna.utils.ifaces.IACNAIface;
import com.cplsys.iacna.utils.ifaces.LogginListener;

@Repository
public class BasculaWorkArea  extends JPanel implements IACNAIface, LogginListener {

	private static final long serialVersionUID = -1415493598629941769L;
	private JLabel tittleLegend;
	
	public JToggleButton buttonBascula1;
	public JToggleButton buttonBascula2;
	public JToggleButton buttonBascula3;
	public JToggleButton buttonBascula4;
	public JToggleButton buttonBascula5;
	public JToggleButton buttonBascula6;
	public JToggleButton buttonBascula7;
	public JToggleButton buttonBascula8;
	public JToggleButton buttonBascula9;
	
	private SpinnerModel modelB8;
	private SpinnerModel modelB9;
	private JSpinner batchDefinition9;
	private JSpinner batchDefinition8;
	
	private boolean readingFromDB1;
	private boolean readingFromDB2;
	private boolean readingFromDB3;
	private boolean readingFromDB4;
	private boolean readingFromDB5;
	private boolean readingFromDB6;
	private boolean readingFromDB7;
	private boolean readingFromDB8;
	private boolean readingFromDB9;
	
	private JButton refreshButton;
	
	private JPanel headerContent;
	private JPanel header;
	private JPanel controlPanel;
	
	private final String FONT_NAME = "Arial";

	private Graphics2D g2;
	
	private Font font;
	private final int FONT_SIZE = 32;
	
	private JDialog dialog;
	
	private JLabel userNameLabel;
	private JLabel passwordLabel;
	
	private JTextField userNameField;
	private JPasswordField passwordField;
	
	private JButton accept;
	private JButton cancel;
	
	private JPanel fieldDialogContainer;
	private JPanel controlDialogContainer;
	
	private boolean accessGranted;
	
	private final String LEYENDA_BASCULA_HABILITADA = "Habilitada";
	private final String LEYENDA_BASCULA_DESHABILITADA = "Deshabilitada";
	@Autowired
	private BasculaService basculaService;
	@Autowired 
	private IACNAStatusBar statusBar;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ThreadScannBasculas threadScannBasculas;
	
	
	private JSpinner batch;
	private JDialog batchDialog;
	
	private JButton confirmBatchDefinition;
	private JButton cancelBatchDefinition;

	private JPanel controlB8;
	private JPanel controlB9;

	private final String DISBLE_ICON = "/media/yellowBox.png";
	private final String ENABLE_ICON = "/media/greenBox.png";
	
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
		Dimension dim = new Dimension(128, 128);
		buttonBascula1.setPreferredSize(dim);
		buttonBascula2.setPreferredSize(dim);
		buttonBascula3.setPreferredSize(dim);
		buttonBascula4.setPreferredSize(dim);
		buttonBascula5.setPreferredSize(dim);
		buttonBascula6.setPreferredSize(dim);
		buttonBascula7.setPreferredSize(dim);
		buttonBascula8.setPreferredSize(dim);
		buttonBascula9.setPreferredSize(dim);
		
		buttonBascula1.setBorderPainted(false);
		buttonBascula1.setContentAreaFilled(false);
		buttonBascula1.setFocusPainted(false);
		
		buttonBascula2.setBorderPainted(false);
		buttonBascula2.setContentAreaFilled(false);
		buttonBascula2.setFocusPainted(false);
		
		buttonBascula3.setBorderPainted(false);
		buttonBascula3.setContentAreaFilled(false);
		buttonBascula3.setFocusPainted(false);
		
		buttonBascula4.setBorderPainted(false);
		buttonBascula4.setContentAreaFilled(false);
		buttonBascula4.setFocusPainted(false);
		
		buttonBascula5.setBorderPainted(false);
		buttonBascula5.setContentAreaFilled(false);
		buttonBascula5.setFocusPainted(false);
		
		buttonBascula6.setBorderPainted(false);
		buttonBascula6.setContentAreaFilled(false);
		buttonBascula6.setFocusPainted(false);
		
		buttonBascula7.setBorderPainted(false);
		buttonBascula7.setContentAreaFilled(false);
		buttonBascula7.setFocusPainted(false);
		
		buttonBascula8.setBorderPainted(false);
		buttonBascula8.setContentAreaFilled(false);
		buttonBascula8.setFocusPainted(false);
		
		buttonBascula9.setBorderPainted(false);
		buttonBascula9.setContentAreaFilled(false);
		buttonBascula9.setFocusPainted(false);	
		
		configureManualMode(false);

		dialog.setModal(true);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setSize(320, 130);
		dialog.setTitle("Autenticacion");
		
		tittleLegend.setFont(new Font("arial", Font.BOLD, 14));
		refreshBasculas();
		
		batchDefinition8.setModel(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));
		batchDialog.setModal(true);
		batchDialog.setLocationRelativeTo(null);
		batchDialog.setSize(300, 180);
		refreshButton.setIcon(new ImageIcon(getClass().getResource("/media/Refresh.png")));
	}
		
	@Override
	public void initObjects() {
		modelB8 = new SpinnerNumberModel(0,0,10000,1);
		modelB9 = new SpinnerNumberModel(0,0,10000,1);
		controlB8 = new JPanel(new MigLayout("insets 0 5 0 0"));
		controlB9 = new JPanel(new MigLayout("insets 0 5 0 0"));
		batchDefinition9 = new JSpinner(modelB8);
		batchDefinition8 = new JSpinner(modelB9);
		
		headerContent = new JPanel(new MigLayout("insets 0 230 0 0"));
		tittleLegend = new JLabel("CONFIGURACION DE BASCULAS");
		font = new Font(FONT_NAME, Font.BOLD, FONT_SIZE);
		dialog = new JDialog();
		userNameLabel = new JLabel("Usuario: ");
		passwordLabel = new JLabel("Clave: ");
		userNameField = new JTextField(20);
		passwordField = new JPasswordField(20);
		accept = new JButton("Aceptar");
		cancel = new JButton("Cancelar");
		fieldDialogContainer = new JPanel(new MigLayout("insets 5 15 0 0"));
		controlDialogContainer = new JPanel(new MigLayout("insets 3 60 0 0"));
		
		batch = new JSpinner();
		batchDialog = new JDialog();
		
		confirmBatchDefinition = new JButton("Aceptar");
		cancelBatchDefinition = new JButton("Cancel");
		
		threadScannBasculas.start();
		
		buttonBascula1= new JToggleButton("B1") {
			private static final long serialVersionUID = 2521767010863030515L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ButtonModel model = buttonBascula1.getModel();
			    g2 = (Graphics2D)g;
			    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
			    g2.setFont(font);
				if (!model.isSelected()) {
					g.drawImage(new ImageIcon(
							getClass().getResource(DISBLE_ICON)).getImage(), 0, 0, null);
				} else {
					g.drawImage(new ImageIcon(
							getClass().getResource(ENABLE_ICON)).getImage(), 0, 0, null);
				}
				g2.setColor(Color.WHITE);
				g2.drawString("1", 55, 80);
			
			}

		};
		buttonBascula2= new JToggleButton("B2") {
			private static final long serialVersionUID = 4893443565353687258L;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ButtonModel model = buttonBascula2.getModel();
			    g2 = (Graphics2D)g;
			    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
			    g2.setFont(font);
				if (!model.isSelected()) {
					g.drawImage(new ImageIcon(
							getClass().getResource(DISBLE_ICON)).getImage(), 0, 0, null);
				} else {
					g.drawImage(new ImageIcon(
							getClass().getResource(ENABLE_ICON)).getImage(), 0, 0, null);
				}
				g2.setColor(Color.WHITE);
				g2.drawString("2", 55, 80);
			}
		
		};
		buttonBascula3= new JToggleButton("B3") {
			private static final long serialVersionUID = 3411578437951589819L;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ButtonModel model = buttonBascula3.getModel();
			    g2 = (Graphics2D)g;
			    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
			    g2.setFont(font);
				if (!model.isSelected()) {
					g.drawImage(new ImageIcon(
							getClass().getResource(DISBLE_ICON)).getImage(), 0, 0, null);
				} else {
					g.drawImage(new ImageIcon(
							getClass().getResource(ENABLE_ICON)).getImage(), 0, 0, null);
				}
				g2.setColor(Color.WHITE);
				g2.drawString("3", 55, 80);
			
			}
		};
		buttonBascula4= new JToggleButton("B4") {
			private static final long serialVersionUID = 6531593441869932599L;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ButtonModel model = buttonBascula4.getModel();
			    g2 = (Graphics2D)g;
			    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
			    g2.setFont(font);
				if (!model.isSelected()) {
					g.drawImage(new ImageIcon(
							getClass().getResource(DISBLE_ICON)).getImage(), 0, 0, null);
				} else {
					g.drawImage(new ImageIcon(
							getClass().getResource(ENABLE_ICON)).getImage(), 0, 0, null);
				}
				g2.setColor(Color.WHITE);
				g2.drawString("4", 55, 80);
			}
		};
		buttonBascula5= new JToggleButton("B5"){
			private static final long serialVersionUID = 4521218145137385463L;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ButtonModel model = buttonBascula5.getModel();
			    g2 = (Graphics2D)g;
			    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
			    g2.setFont(font);
				if (!model.isSelected()) {
					g.drawImage(new ImageIcon(
							getClass().getResource(DISBLE_ICON)).getImage(), 0, 0, null);
				} else {
					g.drawImage(new ImageIcon(
							getClass().getResource(ENABLE_ICON)).getImage(), 0, 0, null);
				}				
				g2.setColor(Color.WHITE);
				g2.drawString("5", 55, 80);
			}
		};
		buttonBascula6= new JToggleButton("B6") {
			private static final long serialVersionUID = 338309209507362467L;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ButtonModel model = buttonBascula6.getModel();
			    g2 = (Graphics2D)g;
			    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
			    g2.setFont(font);
				if (!model.isSelected()) {
					g.drawImage(new ImageIcon(
							getClass().getResource(DISBLE_ICON)).getImage(), 0, 0, null);
				} else {
					g.drawImage(new ImageIcon(
							getClass().getResource(ENABLE_ICON)).getImage(), 0, 0, null);
				}
				g2.setColor(Color.WHITE);
				g2.drawString("6", 55, 80);
			}
		};
		buttonBascula7= new JToggleButton("B7") {
			private static final long serialVersionUID = 7251516145755743110L;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ButtonModel model = buttonBascula7.getModel();
			    g2 = (Graphics2D)g;
			    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
			    g2.setFont(font);
				if (!model.isSelected()) {
					g.drawImage(new ImageIcon(
							getClass().getResource(DISBLE_ICON)).getImage(), 0, 0, null);
				} else {
					g.drawImage(new ImageIcon(
							getClass().getResource(ENABLE_ICON)).getImage(), 0, 0, null);
				}
				g2.setColor(Color.WHITE);
				g2.drawString("7", 55, 80);
			
			}		
		};
		buttonBascula8= new JToggleButton("B8") {
			private static final long serialVersionUID = 6292632285006740575L;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ButtonModel model = buttonBascula8.getModel();
			    g2 = (Graphics2D)g;
			    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
			    g2.setFont(font);
				if (!model.isSelected()) {
					g.drawImage(new ImageIcon(
							getClass().getResource(DISBLE_ICON)).getImage(), 0, 0, null);
				} else {
					g.drawImage(new ImageIcon(
							getClass().getResource(ENABLE_ICON)).getImage(), 0, 0, null);
				}
				g2.setColor(Color.WHITE);
				g2.drawString("8", 55, 80);
			}
		};
		buttonBascula9= new JToggleButton("B9") {
			private static final long serialVersionUID = 7411956391755513894L;
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ButtonModel model = buttonBascula9.getModel();
			    g2 = (Graphics2D)g;
			    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			        RenderingHints.VALUE_ANTIALIAS_ON);
			    g2.setFont(font);			    
				if (!model.isSelected()) {
					g.drawImage(new ImageIcon(
							getClass().getResource(DISBLE_ICON)).getImage(), 0, 0, null);
				} else {
					g.drawImage(new ImageIcon(
							getClass().getResource(ENABLE_ICON)).getImage(), 0, 0, null);
				}				
				g2.setColor(Color.WHITE);
				g2.drawString("9", 55, 80);
			}
		};
		refreshButton = new JButton("Actualizar estados");
		header = new JPanel(new MigLayout("insets 10 320 0 0"));
		controlPanel = new JPanel(new MigLayout("insets 5 230 0 0"));
		
		
	}
	
	@Override
	public void initListeners() {
		buttonBascula1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (accessGranted) {
					Bascula bascula = basculaService.getBasculaByName("1");
					if (bascula != null) {
						if (buttonBascula1.isSelected()){
							bascula.setBasculaAsignada(true);			
							buttonBascula1.setText("B1 " + 
							LEYENDA_BASCULA_HABILITADA);
							statusBar.publishMessageOnStatusBar("La Bascula #1 ha sido habilitada");
					     } else {
				    		 bascula.setBasculaAsignada(false);
					    	 buttonBascula1.setText("B1 " + 
					    	 LEYENDA_BASCULA_DESHABILITADA);
					    	 statusBar.publishMessageOnStatusBar("La Bascula #1 ha sido deshabilitada");
					     }
						
						try{
							basculaService.saveBascula(bascula);
						}
						catch(Exception e1){}
					}
				} else {
					autenticateUser();
				}
				
			}
		});
		
		buttonBascula2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (accessGranted) {
					Bascula bascula = basculaService.getBasculaByName("2");
					if (bascula != null) {
						if (buttonBascula2.isSelected()){
							bascula.setBasculaAsignada(true);
							buttonBascula2.setText("B2 " + 
							LEYENDA_BASCULA_HABILITADA);
							statusBar.publishMessageOnStatusBar("La Bascula #2 ha sido habilitada");
					     } else {
					    	 bascula.setBasculaAsignada(false);
					    	 buttonBascula2.setText("B2 " + 
					    	 LEYENDA_BASCULA_DESHABILITADA);
					    	 statusBar.publishMessageOnStatusBar("La Bascula #2 ha sido deshabilitada");
					     }
						
						try{
							basculaService.saveBascula(bascula);
						}
						catch(Exception e2){}
					}	
				} else {
					autenticateUser();
				}
			}
		});

		buttonBascula3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (accessGranted) {
					Bascula bascula = basculaService.getBasculaByName("3");
					if (bascula != null) {
						if (buttonBascula3.isSelected()){
							bascula.setBasculaAsignada(true);
							buttonBascula3.setText("B3 " + 
							LEYENDA_BASCULA_HABILITADA);
							statusBar.publishMessageOnStatusBar("La Bascula #3 ha sido habilitada");
							
					     } else {
					    	 bascula.setBasculaAsignada(false);
					    	 buttonBascula3.setText("B3 " + 
					    			 LEYENDA_BASCULA_DESHABILITADA);
					    	 statusBar.publishMessageOnStatusBar("La Bascula #3 ha sido deshabilitada");
					     }
						
						try{
							basculaService.saveBascula(bascula);
						}
						catch(Exception e3){}
					}
				} else {
					autenticateUser();
				}
			}
		});
		
		buttonBascula4.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (accessGranted) {
					Bascula bascula = basculaService.getBasculaByName("4");
					if (bascula != null) {
						if (buttonBascula4.isSelected()){
							bascula.setBasculaAsignada(true);
							buttonBascula4.setText("B4 " + 
							LEYENDA_BASCULA_HABILITADA);
							statusBar.publishMessageOnStatusBar("La Bascula #4 ha sido habilitada");
					     } else {
				    		 bascula.setBasculaAsignada(false);
					    	 buttonBascula4.setText("B4 " + 
					    			 LEYENDA_BASCULA_DESHABILITADA);
					    	 statusBar.publishMessageOnStatusBar("La Bascula #4 ha sido deshabilitada");
					     }
						
						try{
							basculaService.saveBascula(bascula);
						}
						catch(Exception e4){}
					}
				} else {
					autenticateUser();
				}
			}
		});
		
		buttonBascula5.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (accessGranted) {
					Bascula bascula = basculaService.getBasculaByName("5");
					if (bascula != null ) {
						if (buttonBascula5.isSelected()){
							bascula.setBasculaAsignada(true);
							buttonBascula5.setText("B5 " + 
							LEYENDA_BASCULA_HABILITADA);
							statusBar.publishMessageOnStatusBar("La Bascula #5 ha sido habilitada");
					     } else {
					    	 bascula.setBasculaAsignada(false);
					    	 buttonBascula5.setText("B5 " + 
					    	 LEYENDA_BASCULA_DESHABILITADA);
					    	 statusBar.publishMessageOnStatusBar("La Bascula #5 ha sido deshabilitada");
					     }
						try{
							basculaService.saveBascula(bascula);
						}
						catch(Exception e5){}
						
					}
				} else {
					autenticateUser();
				}
			}
		});
		
		buttonBascula6.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (accessGranted) {
					Bascula bascula = basculaService.getBasculaByName("6");
					if (bascula != null) {
						if (buttonBascula6.isSelected()){
							bascula.setBasculaAsignada(true);
							buttonBascula6.setText("B6 " + 
							LEYENDA_BASCULA_HABILITADA);
							statusBar.publishMessageOnStatusBar("La Bascula #6 ha sido habilitada");
					     } else {
					    	 bascula.setBasculaAsignada(false);
					    	 buttonBascula6.setText("B6 " + 
					    			 LEYENDA_BASCULA_DESHABILITADA);
					    	 statusBar.publishMessageOnStatusBar("La Bascula #6 ha sido deshabilitada");
					     }
						try{
							basculaService.saveBascula(bascula);
						}
						catch(Exception e6){}
						
					}
				} else {
					autenticateUser();
				}
			}
		});
				
		buttonBascula7.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (accessGranted) {
					Bascula bascula = basculaService.getBasculaByName("7");
					if (bascula != null) {
						if (buttonBascula7.isSelected()){
							bascula.setBasculaAsignada(true);
							buttonBascula7.setText("B7 " + 
									LEYENDA_BASCULA_HABILITADA);
							statusBar.publishMessageOnStatusBar("La Bascula #7 ha sido habilitada");
					     } else {
				    		 bascula.setBasculaAsignada(false);
					    	 buttonBascula7.setText("B7 " + 
					    			 LEYENDA_BASCULA_DESHABILITADA);
					    	 statusBar.publishMessageOnStatusBar("La Bascula #7 ha sido deshabilitada");
					     }
						try{
							basculaService.saveBascula(bascula);
						}
						catch(Exception e7){
							
						}
							
					}
				} else {
					autenticateUser();
				}
			}
		});
		
		buttonBascula8.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (accessGranted) {
					Bascula bascula = basculaService.getBasculaByName("8");
					if (bascula != null) {
						if (buttonBascula8.isSelected()) {
							int batchs = new Integer(batchDefinition8.getValue().toString());
							if (batchs == 0) {
								batchDefinition8.setValue(bascula.getBatchs());	
							}
							bascula.setBatchs(new Integer(
									batchDefinition8.getValue().toString()));
							bascula.setBasculaAsignada(true);
							buttonBascula8.setText("B8 " +
									LEYENDA_BASCULA_HABILITADA);
							statusBar.publishMessageOnStatusBar(
									"La Bascula #8 ha sido habilitada y se han definido " +
							batchDefinition8.getValue().toString() + "iteraciones en la produccion");
					     } else {
					    	 bascula.setBasculaAsignada(false);
					    	 buttonBascula8.setText("B8 " + 
					    			 LEYENDA_BASCULA_DESHABILITADA);
					    	 statusBar.publishMessageOnStatusBar("La Bascula #8 ha sido deshabilitada");
					    	 batchDefinition8.setValue(0);
					    	 bascula.setBatchs(0);
					     }
						try{
							basculaService.saveBascula(bascula);
						}
						catch(Exception e8){}
						
					}
				} else {
					autenticateUser();
				}
			}
		});
		
		buttonBascula9.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (accessGranted) {
					Bascula bascula = basculaService.getBasculaByName("9");
					if (buttonBascula9.isSelected()){
						bascula.setBasculaAsignada(true);
						buttonBascula9.setText("B9 " + 
								LEYENDA_BASCULA_HABILITADA);
						statusBar.publishMessageOnStatusBar("La Bascula #9 ha sido habilitada y se han definido " +
							batchDefinition9.getValue().toString() + "iteraciones en la produccion");
				     }else {
			    		 bascula.setBasculaAsignada(false);
				    	 buttonBascula9.setText("B9 " +
				    			 LEYENDA_BASCULA_DESHABILITADA);
				    	 statusBar.publishMessageOnStatusBar("La Bascula #9 ha sido deshabilitada");
				     }
					
					try{
						basculaService.saveBascula(bascula);
					}
					catch(Exception e9){}
				} else {
					autenticateUser();
				}
	
			}
		});
		
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				accessGranted = false;
			}
		});
		
		accept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				performAutentication();
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
				accessGranted = false;
				statusBar.publishMessageOnStatusBar("La autenticacion es invalida, " +
						"probablemente el usuario o la contrasenha son incorrectos ");
			}
		});	
		
		userNameField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				performAutentication();
			}
		});
		
		passwordField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				performAutentication();
			}
		});
	
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshBasculas();
			}
		});
		
	}
	
	private void performAutentication() {
		Usuario usuario = usuarioService.getUsuario(userNameField.getText(),
				passwordField.getText());
		if (usuario != null) {
			dialog.dispose();
			accessGranted = true;
			statusBar.publishMessageOnStatusBar("Bienvenido, para habilitar o deshabiitar alguna" +
					"bascula, alguna en el tablero");
			configureManualMode(true);
		} else {
			JOptionPane.showMessageDialog(null, 
					"Las credenciales que ha ingresado son incorrectas.\n" +
					"Por favor verifique que tiene los permisos necesarios\n" +
					"para administrar este panel de control", "", 
					JOptionPane.WARNING_MESSAGE);
			statusBar.publishMessageOnStatusBar("La autenticacion es invalida, " +
					"probablemente el usuario o la contrasenha son incorrectos ");
			accessGranted = false;
		}
	
	}

	@Override
	public void createUI() {
		header.add(tittleLegend);
		
		headerContent.add(buildBasculaPanelContainer(1), "cell 0 0");
		headerContent.add(buildBasculaPanelContainer(2), "cell 0 1");
		headerContent.add(buildBasculaPanelContainer(3), "cell 0 2");
		
		headerContent.add(buildBasculaPanelContainer(4), "cell 1 0");
		headerContent.add(buildBasculaPanelContainer(5), "cell 1 1");
		headerContent.add(buildBasculaPanelContainer(6), "cell 1 2");

		headerContent.add(buildBasculaPanelContainer(7), "cell 2 0");
		headerContent.add(buildBasculaPanelContainer(8), "cell 2 1");
		headerContent.add(buildBasculaPanelContainer(9), "cell 2 2, wrap");
		
		controlPanel.add(refreshButton, "height 30:30:30");
		
		this.add(header, "span");
		this.add(headerContent, "wrap");
		this.add(controlPanel);
		
		fieldDialogContainer.add(userNameLabel);
		fieldDialogContainer.add(userNameField, "wrap");
		
		fieldDialogContainer.add(passwordLabel);
		fieldDialogContainer.add(passwordField, "wrap");

		controlDialogContainer.add(accept);
		controlDialogContainer.add(cancel);

		JPanel panelContainer = new JPanel(new MigLayout("insets 0 0 0 0"));
		panelContainer.add(fieldDialogContainer, "wrap");
		panelContainer.add(controlDialogContainer, "wrap");
		dialog.getContentPane().add(panelContainer);

		JPanel batchContainer = new JPanel(new MigLayout("insets 0 0 0 0"));
		batchContainer.add(new JLabel("Definicion de Batch"), "wrap");
		batchContainer.add(new JLabel("Batch: "));
		batchContainer.add(batch, "wrap");
		
		batchContainer.add(confirmBatchDefinition);
		batchContainer.add(cancelBatchDefinition);
		batchDialog.getContentPane().add(batchContainer);

	}
	
	private JPanel buildBasculaPanelContainer(int basculaNo) {
		JPanel panel = new JPanel(new MigLayout("insets 0 0 0 0"));
		switch (basculaNo) {
		case 1:	panel.add(buttonBascula1, "wrap");
		
			break;
		case 2:	panel.add(buttonBascula2, "wrap");
			break;
		case 3:	panel.add(buttonBascula3, "wrap");
			break;
		case 4:	panel.add(buttonBascula4, "wrap");
			break;
		case 5:	panel.add(buttonBascula5, "wrap");
			break;
		case 6:	panel.add(buttonBascula6, "wrap");
			break;
		case 7:	panel.add(buttonBascula7, "wrap");
			break;
		case 8:	
				panel.add(buttonBascula8, "wrap");
				/*controlB8.add(new JLabel("Batch: "));
				controlB8.add(batchDefinition8, "width 60:60:60");
				panel.add(controlB8, "span");
				*/
			break;
		case 9:	panel.add(buttonBascula9, "wrap");
			break;
		}
		return panel;
	}
	
	private synchronized void refreshBasculas() {
		List<Bascula> basculas = basculaService.getAll();
		if (basculas != null) {
			for (Bascula bascula : basculas) {
				if(bascula.getNombre().equals("1")){
					if (bascula.isBasculaAsignada()) {
						if (!buttonBascula1.isSelected()) {
							buttonBascula1.doClick();
						}
					} else {
						if (buttonBascula1.isSelected()) {
							buttonBascula1.doClick();
						}
					}
				}
				
				else if(bascula.getNombre().equals("2")){
					if (bascula.isBasculaAsignada()) {
						if (!buttonBascula2.isSelected()) {
							buttonBascula2.doClick();	
						}
					} else {
						if (buttonBascula2.isSelected()) {
							buttonBascula2.doClick();	
						}
					}
				}
				else if(bascula.getNombre().equals("3")){
					if (bascula.isBasculaAsignada()) {
						if (!buttonBascula3.isSelected()) {
							buttonBascula3.doClick();	
						}
					} else {
						if (buttonBascula3.isSelected()) {
							buttonBascula3.doClick();	
						}
					}
				}
				else if(bascula.getNombre().equals("4")){
					if (bascula.isBasculaAsignada()) {
						if (!buttonBascula4.isSelected()) {
							buttonBascula4.doClick();	
						}
					} else {
						if (buttonBascula4.isSelected()) {
							buttonBascula4.doClick();	
						}
					}
				}
				else if(bascula.getNombre().equals("5")){
					if (bascula.isBasculaAsignada()) {
						if (!buttonBascula5.isSelected()) {
							buttonBascula5.doClick();	
						}
					} else {
						if (buttonBascula5.isSelected()) {
							buttonBascula5.doClick();	
						}
					}
				}
				else if(bascula.getNombre().equals("6")){
					if (bascula.isBasculaAsignada()) {
						if (!buttonBascula6.isSelected()) {
							buttonBascula6.doClick();	
						}
					} else {
						if (buttonBascula6.isSelected()) {
							buttonBascula6.doClick();	
						}
					}
				}
				else if(bascula.getNombre().equals("7")){
					if (bascula.isBasculaAsignada()) {
						if (!buttonBascula7.isSelected()) {
							buttonBascula7.doClick();	
						}
					} else {
						if (buttonBascula7.isSelected()) {
							buttonBascula7.doClick();	
						}
					}
				}
				else if(bascula.getNombre().equals("8")){
					if (bascula.isBasculaAsignada()) {
						if (!buttonBascula8.isSelected()) {
							buttonBascula8.doClick();
							controlB8.setVisible(true);
							batchDefinition8.setValue(bascula.getBatchs());	
						}
					} else {
						if (buttonBascula8.isSelected()) {
							buttonBascula8.doClick();
							controlB8.setVisible(true);
							batchDefinition8.setValue(bascula.getBatchs());	
						}
					}
				}
				else if(bascula.getNombre().equals("9")){
					if (bascula.isBasculaAsignada()) {
						if (!buttonBascula9.isSelected()) {
							buttonBascula9.doClick();	
						}
					} else {
						if (buttonBascula9.isSelected()) {
							buttonBascula9.doClick();	
						}
					}
				}
			}
		}
	}
	
	public void autenticateUser() {
		accessGranted = false;
		configureManualMode(false);
		dialog.setVisible(true);
	}
	
	private void configureManualMode(boolean activeComponent) {
		
	}
	
	public void resetForm() {
		accessGranted = false;
	}

	@Override
	public void sessionActivated() {
		startMonitor();
	}

	@Override
	public void sessionDeactivated() {
		
	}

	public void startMonitor() {/*
		BasculasMonitor monitor = new BasculasMonitor();
		monitor.start();*/
	}
	
	private class BasculasMonitor extends Thread {
		
		public BasculasMonitor() {

		}
		
		@Override
		public void run() {
			try {
				performQueryDB();
				Thread.sleep(4000);
				run();
			} catch (Exception e) {

			}
			super.run();
		}
		
		private void performQueryDB() {
			List<Bascula> basculasConfiguration = 
					basculaService.getAll();			
			Bascula bascula = null;
			if (basculasConfiguration != null && 
				!basculasConfiguration.isEmpty()) {
				bascula = basculasConfiguration.get(0);
				if (bascula != null) {
					if (bascula.isBasculaAsignada()){
						buttonBascula1.setText("B1 " + 
						LEYENDA_BASCULA_HABILITADA);
						readingFromDB1 = true;
						System.err.println("bascula 1 habilitada");
				     } else {
				    	 readingFromDB1 = false;
				    	 buttonBascula1.setText("B1 " +
				    	 LEYENDA_BASCULA_DESHABILITADA);
				    	 System.err.println("bascula 1 deshabilitada");
				     }
					buttonBascula1.revalidate();
				}
			

				bascula = basculasConfiguration.get(1);
				if (bascula != null) {
					if (bascula.isBasculaAsignada()){
						readingFromDB2 = true;
						buttonBascula2.setText("B2 " + 
						LEYENDA_BASCULA_HABILITADA);
				     } else {
				    	 readingFromDB2 = false;
				    	 buttonBascula2.setText("B2 " + 
				    	 LEYENDA_BASCULA_DESHABILITADA);
				     }
					buttonBascula2.revalidate();
				}	
			
				bascula = basculasConfiguration.get(2);
				if (bascula != null) {
					if (bascula.isBasculaAsignada()){
						readingFromDB3 = true;
						buttonBascula3.setText("B3 " + 
						LEYENDA_BASCULA_HABILITADA);
				     } else {
				    	 readingFromDB3 = false;
				    	 buttonBascula3.setText("B3 " + 
				    			 LEYENDA_BASCULA_DESHABILITADA);
				     }
					buttonBascula3.revalidate();
				}

				bascula = basculasConfiguration.get(3);
				if (bascula != null) {
					if (bascula.isBasculaAsignada()) {
						readingFromDB4 = true;
						buttonBascula4.setText("B4 " + 
						LEYENDA_BASCULA_HABILITADA);
				     } else {
				    	 readingFromDB4 = false;
				    	 buttonBascula4.setText("B4 " +
				    			 LEYENDA_BASCULA_DESHABILITADA);
				     }
					buttonBascula4.revalidate();
				}
			
				bascula = basculasConfiguration.get(4);
				if (bascula != null ) {
					if (bascula.isBasculaAsignada()) {
						readingFromDB5 = true;
						buttonBascula5.setText("B5 " + 
						LEYENDA_BASCULA_HABILITADA);
				     } else {
				    	 readingFromDB5 = false;
				    	 buttonBascula5.setText("B5 " + 
				    	 LEYENDA_BASCULA_DESHABILITADA);
				     }
					buttonBascula5.revalidate();
				}
			

				bascula = basculasConfiguration.get(5);
				if (bascula != null) {
					if (bascula.isBasculaAsignada()) {
						readingFromDB6 = true;
						buttonBascula6.setText("B6 " + 
						LEYENDA_BASCULA_HABILITADA);
				     } else {
				    	 readingFromDB6 = false;
				    	 buttonBascula6.setText("B6 " + 
				    			 LEYENDA_BASCULA_DESHABILITADA);
				     }
					buttonBascula6.revalidate();
				}

				bascula = basculasConfiguration.get(6);
				if (bascula != null) {
					if (buttonBascula7.isSelected()) {
						readingFromDB7 = true;
						bascula.setBasculaAsignada(true);
						buttonBascula7.setText("B7 " + 
								LEYENDA_BASCULA_HABILITADA);
				     } else {
				    	 readingFromDB7 = false;
				    	 buttonBascula7.setText("B7 " + 
				    			 LEYENDA_BASCULA_DESHABILITADA);
				     }
					buttonBascula7.revalidate();
				}

				bascula = basculasConfiguration.get(7);
				if (bascula != null) {
					if (bascula.isBasculaAsignada()) {
						readingFromDB8 = true;
						batchDefinition8.setValue(bascula.getBatchs());
						buttonBascula8.setText("B8 " +
								LEYENDA_BASCULA_HABILITADA);
				     } else {
				    	 readingFromDB8 = false;
				    	 buttonBascula8.setText("B8 " + 
				    			 LEYENDA_BASCULA_DESHABILITADA);
				    	 batchDefinition8.setValue(0);
				     }
					buttonBascula8.revalidate();
				}
			
				bascula = basculasConfiguration.get(8);
				if (bascula.isBasculaAsignada()) {
					readingFromDB9 = true;
					buttonBascula9.setText("B9 " + 
							LEYENDA_BASCULA_HABILITADA);
			     }else {
			    	 readingFromDB9 = false;
			    	 buttonBascula9.setText("B9 " +
			    			 LEYENDA_BASCULA_DESHABILITADA);
			     }
				buttonBascula9.revalidate();
			}
			
		}
	}
}