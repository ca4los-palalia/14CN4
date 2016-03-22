package com.cplsys.iacna.app.ui.workingarea;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import net.miginfocom.swing.MigLayout;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jdesktop.swingx.JXHyperlink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.app.ui.IACNAFrame;
import com.cplsys.iacna.app.ui.statusbar.IACNAStatusBar;
import com.cplsys.iacna.app.ui.workingarea.bascula.BasculaWorkArea;
import com.cplsys.iacna.app.ui.workingarea.configuration.ConfiguracionWorkArea;
import com.cplsys.iacna.app.ui.workingarea.formula.FormulaWorkArea;
import com.cplsys.iacna.app.ui.workingarea.material.MaterialPrepesadoVisorWorkArea;
import com.cplsys.iacna.app.ui.workingarea.material.MaterialVisorWorkArea;
import com.cplsys.iacna.app.ui.workingarea.material.MaterialWorkArea;
import com.cplsys.iacna.app.ui.workingarea.produccion.RegistroProduccionWorkArea;
import com.cplsys.iacna.app.ui.workingarea.usuario.UsuarioWorkArea;
import com.cplsys.iacna.domain.OrigenDeDatos;
import com.cplsys.iacna.domain.Privilegio;
import com.cplsys.iacna.services.ISession;
import com.cplsys.iacna.services.OrigenDeDatosService;
import com.cplsys.iacna.ui.Logged;
import com.cplsys.iacna.utils.IACNAConstants;
import com.cplsys.iacna.utils.UpdateCellExcel;
import com.cplsys.iacna.utils.ifaces.LogginListener;
import com.cplsys.iacna.utils.model.FileTreeModel;
import com.cplsys.iacna.utils.render.TreeCellRenderer;

@Repository
public class IACNAWorkingAreaPanel extends JPanel implements LogginListener {

	private static final long serialVersionUID = -257192353639070665L;

	private JTabbedPane optionTabs;
	private JPanel optionsPanel;
	private JPanel workArea;
	private JPanel workAreaCard;
	private JPanel formulasOptionsPanel;
	private JPanel reportesOptionsPanel;
	private JPanel basculaOptionsPanel;
	private JPanel usuarioOptionsPanel;
	private JPanel materialOptionsPanel;
	private JPanel materialPrepesadoOptionsPanel;
	private TreeCellRenderer renderer;
	private JXHyperlink importarFormula;
	private JXHyperlink reporteDeProduccion;
	private JXHyperlink importarBascula;
	private JXHyperlink edicionUsuarios;
	private JXHyperlink edicionUsuariosNuevoUsuario;
	private CardLayout cardManager;
	private JTree materiales;
	private JScrollPane materialesScroller;
	private JTree materialesPrepesados;
	private JScrollPane materialesPrepesadosScroller;
	private String rutaProductos = "";
	private String rutaProductosPrepesados = "";	
	
	@Autowired
	private FormulaWorkArea formulasWorkAreaPanel;
	@Autowired
	private RegistroProduccionWorkArea reportesWorkAreaPanel;
	@Autowired
	private BasculaWorkArea basculaWorkAreaPanel;
	@Autowired
	private ConfiguracionWorkArea configuracionWorkArea;
	@Autowired
	private UsuarioWorkArea usuarioWorkingArea;
	@Autowired
	private MaterialVisorWorkArea materialVisorWorkArea;
	@Autowired
	private MaterialPrepesadoVisorWorkArea materialPrepesadoVisorWorkArea;
	@Autowired
	private MaterialWorkArea materialWorkingArea;
	@Autowired
	private RegistroProduccionWorkArea registroProduccionWorkArea;
	@Autowired
	private OrigenDeDatosService origenDeDatosService;
	@Autowired
	private IACNAStatusBar iacnaStatusBar;
	@Autowired
	private Logged logged;
	@Autowired
	private IACNAFrame iacnaFrame;
	@Autowired
	private ISession session;
	@Autowired
	private FormulaWorkArea formulaWorkArea;
	@Autowired
	private UpdateCellExcel updateCellExcel;

	private JPopupMenu updateMaterialesPopUp;
	private JPopupMenu updateMaterialesPrepesadosPopUp;
	
	private JMenuItem updateMaterialesMenuItem;
	private JMenuItem updateMaterialesPrepesadosMenuItem;
	
	@PostConstruct
	public void init() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}

	private void initProperties() {
		
		importarFormula.setClickedColor(Color.BLACK);
		importarFormula.setUnclickedColor(Color.BLACK);
		importarFormula.setToolTipText("Haga click aqui para importar un archivo de excel");
		importarFormula.setFont(new Font("arial", Font.BOLD, 16));
		importarFormula.setText("Importar Formula");
		
		reporteDeProduccion.setClickedColor(Color.black);
		reporteDeProduccion.setUnclickedColor(Color.black);
		reporteDeProduccion.setToolTipText("Haga click aqui para ver el reporte de produccion");
		
		importarBascula.setClickedColor(Color.BLACK);
		importarBascula.setUnclickedColor(Color.BLACK);
		importarBascula.setUnclickedColor(Color.BLACK);
		importarBascula.setToolTipText("click aqui para configurar las basculas disponibles");
		importarBascula.setText("Configuracion de Basculas");

		edicionUsuarios.setClickedColor(Color.BLACK);
		edicionUsuarios.setUnclickedColor(Color.BLACK);
		edicionUsuarios.setToolTipText("click aqui para configurar la informacion del usuario");
		edicionUsuarios.setText("Editar usuario");

		edicionUsuariosNuevoUsuario.setClickedColor(Color.BLACK);
		edicionUsuariosNuevoUsuario.setUnclickedColor(Color.BLACK);
		edicionUsuariosNuevoUsuario.setToolTipText("click aqui para Agregar un nuevo usuario");
		edicionUsuariosNuevoUsuario.setText("Nuevo usuario");

		reporteDeProduccion.setText("Registro de Produccion");
		reporteDeProduccion.setToolTipText("Haga click aqui para ver el reporte de produccion");

		workAreaCard.add(materialWorkingArea,
				IACNAConstants.MATERIAL_WORK_AREA_PANEL);
		workAreaCard.add(materialVisorWorkArea,
				IACNAConstants.MATERIAL_VIEWER_WORK_AREA_PANEL);
		workAreaCard.add(materialPrepesadoVisorWorkArea,
				IACNAConstants.MATERIAL_PREPESADO_VIEWER_WORK_AREA_PANEL);
		materiales.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		renderer.setRendererIcon(
				new ImageIcon(getClass().getResource("/media/FolderRoot.png")),
				new ImageIcon(getClass().getResource("/media/FolderOpen.png")),
				new ImageIcon(getClass().getResource("/media/excelFile.png")),
				new ImageIcon(getClass().getResource("/media/FolderGeneric.png")),
				new ImageIcon(getClass().getResource("/media/cancelIcon.png")));

		materiales.setCellRenderer(renderer);
		materialesPrepesados.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		materialesPrepesados.setCellRenderer(renderer);
		buildMaterialesTree();
		materialesScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		materialesPrepesadosScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		
		optionTabs.add(buildPanelReportes());
		optionTabs.add(buildPanelBascula());
		optionTabs.add(buildPanelUsuarios());
		optionTabs.add(buildPanelMaterial());
		optionTabs.add(buildPanelMaterialPrepesado());
				
		optionTabs.setTitleAt(0, IACNAConstants.REPORTES_TAB_NAME);
		optionTabs.setTitleAt(1, IACNAConstants.BASCULA_TAB_NAME);
		optionTabs.setTitleAt(2, IACNAConstants.USUARIOS_TAB_NAME);
		optionTabs.setTitleAt(3, IACNAConstants.MATERIAL_TAB_NAME);
		optionTabs.setTitleAt(4, IACNAConstants.MATERIAL_PREPESADO_TAB_NAME);
		
		optionTabs.setIconAt(0,new ImageIcon(getClass().getResource("/media/reportTab.png")));
		optionTabs.setIconAt(1,new ImageIcon(getClass().getResource("/media/BasculaTab.png")));
		optionTabs.setIconAt(2,new ImageIcon(getClass().getResource("/media/UsersTab.png")));
		optionTabs.setIconAt(3,new ImageIcon(getClass().getResource("/media/MaterialTab.png")));
		optionTabs.setIconAt(4,new ImageIcon(getClass().getResource("/media/PrePesadoTab.png")));
		
		optionTabs.setMnemonicAt(0, KeyEvent.VK_1);
		optionTabs.setMnemonicAt(1, KeyEvent.VK_2);
		optionTabs.setMnemonicAt(2, KeyEvent.VK_3);
		optionTabs.setMnemonicAt(3, KeyEvent.VK_4);
		optionTabs.setMnemonicAt(4, KeyEvent.VK_5);
		
		
		
	}

	private void initObjects() {
		updateMaterialesMenuItem = new JMenuItem("Actualizar lista de materiales");
		updateMaterialesPrepesadosMenuItem = new JMenuItem("Actualizar lista de materiales");
		updateMaterialesPopUp = new JPopupMenu();
		updateMaterialesPopUp.add(updateMaterialesMenuItem);
		updateMaterialesPrepesadosPopUp = new JPopupMenu();
		updateMaterialesPrepesadosPopUp.add(updateMaterialesPrepesadosMenuItem);
		
		renderer = new TreeCellRenderer();
		
		importarFormula = new JXHyperlink();
		reporteDeProduccion = new JXHyperlink();
		importarBascula = new JXHyperlink();
		edicionUsuarios = new JXHyperlink();
		edicionUsuariosNuevoUsuario = new JXHyperlink();

		materiales = new JTree();
		materialesPrepesados = new JTree();
		
		materialesScroller = new JScrollPane(materiales);
		materialesPrepesadosScroller = new JScrollPane(materialesPrepesados);
		
		optionTabs = new JTabbedPane(JTabbedPane.LEFT);

		workAreaCard = new JPanel(new CardLayout());
		
		formulasOptionsPanel = new JPanel(new MigLayout());
		reportesOptionsPanel = new JPanel(new MigLayout());
		basculaOptionsPanel = new JPanel(new MigLayout());
		usuarioOptionsPanel = new JPanel(new MigLayout());
		materialOptionsPanel = new JPanel(new MigLayout());
		materialPrepesadoOptionsPanel = new JPanel(new MigLayout(
				"insets 0 0 0 0"));
		
		optionsPanel = new JPanel(new MigLayout("insets 0 0 0 0"));
		workArea = new JPanel(new MigLayout("insets 0 0 0 0"));
		
		
		workAreaCard.add(new JPanel(), IACNAConstants.DEFAULT_LAYERED_PANE);
		workAreaCard.add(formulasWorkAreaPanel,
				IACNAConstants.FORMULA_WORK_AREA_PANEL);
		workAreaCard.add(reportesWorkAreaPanel,
				IACNAConstants.REPORTES_WORK_AREA_PANEL);
		workAreaCard.add(basculaWorkAreaPanel,
				IACNAConstants.BASCULA_WORK_AREA_PANEL);
		workAreaCard.add(usuarioWorkingArea,
				IACNAConstants.USUARIO_WORK_AREA_PANEL);
		workAreaCard.add(materialWorkingArea,
				IACNAConstants.MATERIAL_WORK_AREA_PANEL);
		workAreaCard.add(materialVisorWorkArea,
				IACNAConstants.MATERIAL_VIEWER_WORK_AREA_PANEL);
		workAreaCard.add(materialPrepesadoVisorWorkArea,
				IACNAConstants.MATERIAL_PREPESADO_VIEWER_WORK_AREA_PANEL);
		
		
	}
	


	private synchronized void initListeners() {
		updateMaterialesMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buildMaterialesTree();
			}
		});
		
		updateMaterialesPrepesadosMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buildMaterialesTree();
			}
		});
		
		importarFormula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardManager = (CardLayout) workAreaCard.getLayout();
				cardManager.show(workAreaCard,
						IACNAConstants.FORMULA_WORK_AREA_PANEL);
			}
		});

		importarBascula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardManager = (CardLayout) workAreaCard.getLayout();
				cardManager.show(workAreaCard,
						IACNAConstants.BASCULA_WORK_AREA_PANEL);
			}
		});

		reporteDeProduccion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardManager = (CardLayout) workAreaCard.getLayout();
				cardManager.show(workAreaCard,
						IACNAConstants.REPORTES_WORK_AREA_PANEL);
			}
		});

		edicionUsuarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardManager = (CardLayout) workAreaCard.getLayout();
				cardManager.show(workAreaCard,
						IACNAConstants.USUARIO_WORK_AREA_PANEL);
				usuarioWorkingArea.setType(true);
				usuarioWorkingArea.setUpUsarData();
			}
		});

		edicionUsuariosNuevoUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardManager = (CardLayout) workAreaCard.getLayout();
				cardManager.show(workAreaCard,
						IACNAConstants.USUARIO_WORK_AREA_PANEL);
				usuarioWorkingArea.setType(false);
				usuarioWorkingArea.setUpUsarData();

			}
		});

		materiales.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				if (!rutaProductos.isEmpty()) {
					if (materiales.getSelectionPath() != null) {
						materialVisorWorkArea.getFile();
						File localFile = (File) materiales.getSelectionPath()
								.getLastPathComponent();
						if (localFile != null && localFile.exists()
								&& !localFile.isDirectory()) {
							cardManager = (CardLayout) workAreaCard.getLayout();
							cardManager
									.show(workAreaCard,
											IACNAConstants.MATERIAL_VIEWER_WORK_AREA_PANEL);
							materialVisorWorkArea.iniciarSecuenciaDeCargadoDeDatos(localFile);
						}
					}
				} else {
					configuracionWorkArea.setVisible(true);
				}
			}
		});

		materialesPrepesados.addTreeSelectionListener(new TreeSelectionListener() {
					@Override
					public void valueChanged(TreeSelectionEvent e) {
						if (!rutaProductosPrepesados.isEmpty()) {
							if (materialesPrepesados.getSelectionPath() != null) {
								File file = (File) materialesPrepesados
										.getSelectionPath()
										.getLastPathComponent();
								if (file != null && file.exists()
										&& !file.isDirectory()) {
									cardManager = (CardLayout) workAreaCard
											.getLayout();
									cardManager.show(workAreaCard,
													IACNAConstants.MATERIAL_PREPESADO_VIEWER_WORK_AREA_PANEL);
									materialPrepesadoVisorWorkArea.iniciarSecuenciaDeCargadoDeDatos(file);
									
								}
							}
						} else {
							configuracionWorkArea.setVisible(true);
						}
					}
				});

		materiales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					buildMaterialesTree();
				}
				if (e.isPopupTrigger()) {
					JTree tree = (JTree) (e.getSource());
					int x = e.getX();
					int y = e.getY();
					TreePath path = tree.getPathForLocation(x, y);
					if (path != null) {
						tree.setSelectionPath(path);
						updateMaterialesPopUp.show((JComponent) e.getSource(), e.getX(),
								e.getY());
					}
				}
			}
		});

		
		materialesPrepesados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					buildMaterialesTree();
				}
				if (e.isPopupTrigger()) {
					JTree tree = (JTree) (e.getSource());
					int x = e.getX();
					int y = e.getY();
					TreePath path = tree.getPathForLocation(x, y);
					if (path != null) {
						tree.setSelectionPath(path);
						updateMaterialesPrepesadosPopUp.show((JComponent) e.getSource(), 
								e.getX(), e.getY());
					}
				}
			}
		});
		
		optionTabs.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				cardManager = (CardLayout) workAreaCard.getLayout();
				cardManager.show(workAreaCard,
						IACNAConstants.DEFAULT_LAYERED_PANE);

				switch (optionTabs.getSelectedIndex()) {
				case 0:// formulasWorkAreaPanel.cleanComponents();
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				}

			}
		});
	}

	private void createUI() {
		
		optionsPanel.add(optionTabs);
		optionsPanel.add(optionTabs, "width 300:300:300");
		workArea.add(workAreaCard,"height 620:620:620, width 480:800:1280");
		
		
		this.add(optionsPanel);
		this.add(workArea);
		this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 0));
			
		optionsPanel.setBackground(Color.BLUE);
	}

	private JPanel buildPanelFormula() {
		formulasOptionsPanel.add(importarFormula, "wrap");
		return formulasOptionsPanel;
	}

	private JPanel buildPanelReportes() {
		
		reportesOptionsPanel.add(reporteDeProduccion, "wrap");
		return reportesOptionsPanel;
	}

	private JPanel buildPanelBascula() {
		basculaOptionsPanel.add(importarBascula, "wrap");
		return basculaOptionsPanel;
	}

	private JPanel buildPanelUsuarios() {
		usuarioOptionsPanel.add(edicionUsuarios, "wrap");
		usuarioOptionsPanel.add(edicionUsuariosNuevoUsuario, "wrap");
		return usuarioOptionsPanel;
	}

	private JPanel buildPanelMaterial() {
		materialOptionsPanel.add(materialesScroller,
				"width 305:305:305, height 630:630:630, wrap");
		return materialOptionsPanel;
	}

	private JPanel buildPanelMaterialPrepesado() {
		materialPrepesadoOptionsPanel.add(materialesPrepesadosScroller,
				"width 305:305:305, height 630:630:630, wrap");
		return materialPrepesadoOptionsPanel;
	}

	@Override
	public void sessionActivated() {

		LogginListener bListener = (LogginListener) basculaWorkAreaPanel;
		bListener.sessionActivated();
		
		bListener = (LogginListener) configuracionWorkArea;
		bListener.sessionActivated();
		buildMaterialesTree();
		
		
		bListener = (LogginListener) registroProduccionWorkArea;
		bListener.sessionActivated();
		bListener = (LogginListener) formulasWorkAreaPanel;
		bListener.sessionActivated();
		bListener = (LogginListener) iacnaStatusBar;
		bListener.sessionActivated();
		bListener = (LogginListener) materialPrepesadoVisorWorkArea;
		bListener.sessionActivated();
		bListener = (LogginListener) materialVisorWorkArea;
		bListener.sessionActivated();

		
		Set<Privilegio> privilegio =  session.getUsuario().getPrivilegios();

		for (Privilegio privilegio2 : privilegio) {
			if(privilegio2.isProductionSupervisor()) {
				optionTabs.setEnabledAt(0, true);
				reporteDeProduccion.setVisible(true);
				
				optionTabs.setEnabledAt(1, true);
				importarBascula.setVisible(true);
				
				optionTabs.setEnabledAt(2, false);
				edicionUsuarios.setVisible(false);
				edicionUsuariosNuevoUsuario.setVisible(false);
				
				optionTabs.setEnabledAt(3, true);
				materialesScroller.setVisible(true);
				optionTabs.setEnabledAt(4, true);
				materialesPrepesadosScroller.setVisible(true);
				break;
			}
			if(privilegio2.isSuperUser()) {
				optionTabs.setEnabledAt(0, true);
				reporteDeProduccion.setVisible(true);
				
				optionTabs.setEnabledAt(1, true);
				importarBascula.setVisible(true);
				
				optionTabs.setEnabledAt(2, true);
				edicionUsuarios.setVisible(true);
				edicionUsuariosNuevoUsuario.setVisible(true);
				
				optionTabs.setEnabledAt(3, true);
				materialesScroller.setVisible(true);
				optionTabs.setEnabledAt(4, true);
				materialesPrepesadosScroller.setVisible(true);
				
				break;
			}
			if(privilegio2.isProductionCalidad()) {
				
				optionTabs.setEnabledAt(0, true);
				reporteDeProduccion.setVisible(true);
				
				optionTabs.setEnabledAt(1, true);
				importarBascula.setVisible(true);
				
				optionTabs.setEnabledAt(2, false);
				edicionUsuarios.setVisible(false);
				edicionUsuariosNuevoUsuario.setVisible(false);
				
				optionTabs.setEnabledAt(3, false);
				materialesScroller.setVisible(false);
				
				optionTabs.setEnabledAt(4, false);
				materialesPrepesadosScroller.setVisible(false);
				break;
			}
			if(privilegio2.isProductionHeads()) {
				
				optionTabs.setEnabledAt(0, true);
				reporteDeProduccion.setVisible(true);
				
				optionTabs.setEnabledAt(1, false);
				importarBascula.setVisible(false);
				
				optionTabs.setEnabledAt(2, false);
				edicionUsuarios.setVisible(false);
				edicionUsuariosNuevoUsuario.setVisible(false);
				
				optionTabs.setEnabledAt(3, false);
				materialesScroller.setVisible(false);
				
				optionTabs.setEnabledAt(4, false);
				materialesPrepesadosScroller.setVisible(false);
				break;
			}
		}
	}

	private void buildMaterialesTree() {
		List<OrigenDeDatos> datos = origenDeDatosService.getAll();
		
		for (OrigenDeDatos origenDeDatos : datos) {
			
			if(iacnaFrame.isPrivilegio()) {
				rutaProductos = origenDeDatos.getLocationProductos();
				rutaProductosPrepesados = origenDeDatos.getLocationProductosPrepesados();
			}
			else {
				rutaProductos = origenDeDatos.getLocationProductosRed();
				rutaProductosPrepesados = origenDeDatos.getLocationProductosRed();
			}
			
			if (rutaProductos != null && !rutaProductos.isEmpty()) {
				materiales.setModel(new FileTreeModel(new File(rutaProductos)));
			}
			if (rutaProductosPrepesados != null
					&& !rutaProductosPrepesados.isEmpty()) {
				materialesPrepesados.setModel(new FileTreeModel(new File(
						rutaProductosPrepesados)));
			}
			break;
		}
	}

	@Override
	public void sessionDeactivated() {
		optionTabs.setSelectedIndex(1);
		optionTabs.setSelectedIndex(0);
	}


}
