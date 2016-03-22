package com.cplsys.iacna.ui;

import java.awt.CardLayout;
import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.domain.Bascula;
import com.cplsys.iacna.domain.Formula;
import com.cplsys.iacna.domain.Producto;
import com.cplsys.iacna.services.BasculaService;
import com.cplsys.iacna.services.FormulaService;
import com.cplsys.iacna.services.ProductoService;
import com.cplsys.iacna.services.UsuarioService;
import com.cplsys.iacna.webservice.IACNAWebServiceImpl;

/**
 * 
 * @author CPL
 */
@Repository
public class IACNAUI extends JFrame {

	private static final long serialVersionUID = 6505230162149865894L;

	@Autowired
	Logged logged;
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	FormulaService formulaService;

	@Autowired
	BasculaService basculaService;
	
	@Autowired
	ProductoService productoServicio;
	
	@PostConstruct
	public void init() {

		initComponents();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jXTaskPane6.setVisible(false);

		spinnerBatch.setEnabled(false);
		buttonConfirmar.setEnabled(false);
		buttonCancelar.setEnabled(false);
		optionsTabbedPane.setEnabledAt(1, false);
        optionsTabbedPane.setEnabledAt(2, false);
        optionsTabbedPane.setEnabledAt(3, false);
        optionsTabbedPane.setEnabledAt(4, false);
        optionsTabbedPane.setEnabledAt(5, false);
        subMenuCerrarSesion.setEnabled(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		
	}

	public IACNAUI() {
		
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		optionsPanel = new javax.swing.JPanel();
		optionsTabbedPane = new javax.swing.JTabbedPane();
		startPanel = new javax.swing.JPanel();
		formulaPanel = new javax.swing.JPanel();
		jXTaskPaneContainer2 = new org.jdesktop.swingx.JXTaskPaneContainer();
		jXTaskPane4 = new org.jdesktop.swingx.JXTaskPane();
		jXTaskPane5 = new org.jdesktop.swingx.JXTaskPane();
		jXTaskPane6 = new org.jdesktop.swingx.JXTaskPane();
		reportePanel = new javax.swing.JPanel();
		jLabel7 = new javax.swing.JLabel();
		backUpPanel = new javax.swing.JPanel();
		materialPanel = new javax.swing.JPanel();
		jXTaskPaneContainer3 = new org.jdesktop.swingx.JXTaskPaneContainer();
		jXTaskPane7 = new org.jdesktop.swingx.JXTaskPane();
		jXTaskPane8 = new org.jdesktop.swingx.JXTaskPane();
		jXTaskPane9 = new org.jdesktop.swingx.JXTaskPane();
		productoPanel = new javax.swing.JPanel();
		jXTaskPaneContainer4 = new org.jdesktop.swingx.JXTaskPaneContainer();
		jXTaskPane10 = new org.jdesktop.swingx.JXTaskPane();
		jXTaskPane11 = new org.jdesktop.swingx.JXTaskPane();
		jXTaskPane12 = new org.jdesktop.swingx.JXTaskPane();
		workArea = new javax.swing.JPanel();
		iacnaLogo = new javax.swing.JLabel();
		operationsPanel = new javax.swing.JPanel();
		PanFrontDes = new javax.swing.JPanel();
		PanAltaMatDes = new javax.swing.JPanel();
		jLabel17 = new javax.swing.JLabel();
		jScrollPane6 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();
		jLabel18 = new javax.swing.JLabel();
		jButton10 = new javax.swing.JButton();
		jButton11 = new javax.swing.JButton();
		jTextField3 = new javax.swing.JTextField();
		PanEditarFormDes = new javax.swing.JPanel();
		jLabel8 = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTable2 = new javax.swing.JTable();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jLabel4 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		jComboBox5 = new javax.swing.JComboBox();
		jButton6 = new javax.swing.JButton();
		jLabel16 = new javax.swing.JLabel();
		jComboBox6 = new javax.swing.JComboBox();
		jButton7 = new javax.swing.JButton();
		jButton8 = new javax.swing.JButton();
		jComboBox7 = new javax.swing.JComboBox();
		PanLeerFormDes = new javax.swing.JPanel();
		jLabel14 = new javax.swing.JLabel();
		jComboBox10 = new javax.swing.JComboBox();
		jButton12 = new javax.swing.JButton();
		jButton13 = new javax.swing.JButton();
		PanReportDes = new javax.swing.JPanel();
		jLabel21 = new javax.swing.JLabel();
		jButton15 = new javax.swing.JButton();
		jScrollPane3 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		PanEditaMatDes = new javax.swing.JPanel();
		jLabel9 = new javax.swing.JLabel();
		jScrollPane5 = new javax.swing.JScrollPane();
		jTextArea4 = new javax.swing.JTextArea();
		jLabel10 = new javax.swing.JLabel();
		jButton18 = new javax.swing.JButton();
		jButton19 = new javax.swing.JButton();
		jComboBox13 = new javax.swing.JComboBox();
		PanDeletMatDes = new javax.swing.JPanel();
		jLabel19 = new javax.swing.JLabel();
		jComboBox14 = new javax.swing.JComboBox();
		jButton20 = new javax.swing.JButton();
		jButton21 = new javax.swing.JButton();
		PanAltaProdDes = new javax.swing.JPanel();
		jLabel20 = new javax.swing.JLabel();
		jLabel26 = new javax.swing.JLabel();
		jButton22 = new javax.swing.JButton();
		jButton23 = new javax.swing.JButton();
		jTextField5 = new javax.swing.JTextField();
		jComboBox1 = new javax.swing.JComboBox();
		PanEditaProdDes = new javax.swing.JPanel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jButton24 = new javax.swing.JButton();
		jButton25 = new javax.swing.JButton();
		jComboBox15 = new javax.swing.JComboBox();
		jComboBox3 = new javax.swing.JComboBox();
		PanDeletProdDes = new javax.swing.JPanel();
		jLabel27 = new javax.swing.JLabel();
		jComboBox16 = new javax.swing.JComboBox();
		jButton26 = new javax.swing.JButton();
		jButton27 = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();
		jComboBox4 = new javax.swing.JComboBox();
		PanCargarFormDes = new javax.swing.JPanel();
		labelFormula1 = new javax.swing.JLabel();
		jScrollPane9 = new javax.swing.JScrollPane();
		tableXLSXBase = new javax.swing.JTable();
		jLabel30 = new javax.swing.JLabel();
		buttonConfirmar = new javax.swing.JButton();
		buttonCancelar = new javax.swing.JButton();
		fieldFormulaPathXLS1 = new javax.swing.JTextField();
		buttonAbrirArchivo1 = new javax.swing.JButton();
		spinnerBatch = new javax.swing.JSpinner();
		PanModiicarFormDes = new javax.swing.JPanel();
		labelFormula2 = new javax.swing.JLabel();
		jScrollPane10 = new javax.swing.JScrollPane();
		tableXLSXBase1 = new javax.swing.JTable();
		jLabel31 = new javax.swing.JLabel();
		jButton31 = new javax.swing.JButton();
		jButton32 = new javax.swing.JButton();
		fieldFormulaPathXLS2 = new javax.swing.JTextField();
		buttonAbrirArchivo2 = new javax.swing.JButton();
		jSpinner2 = new javax.swing.JSpinner();
		iacnaTitleApplicationLabel = new javax.swing.JLabel();
		userLogged = new javax.swing.JLabel();
		statusBarContainer = new javax.swing.JPanel();
		progressBar = new javax.swing.JProgressBar();
		statusBarLabel = new javax.swing.JLabel();
		menuBar = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		menuSalir = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();
		jMenu4 = new javax.swing.JMenu();
		jMenu5 = new javax.swing.JMenu();
		jMenu3 = new javax.swing.JMenu();
		subMenuIniciarSesion = new javax.swing.JMenuItem();
		subMenuCerrarSesion = new javax.swing.JMenuItem();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new java.awt.Dimension(898, 800));
		setUndecorated(true);

		optionsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		optionsTabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
		optionsTabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				optionsTabbedPaneMousePressed(evt);
			}
		});

		javax.swing.GroupLayout startPanelLayout = new javax.swing.GroupLayout(startPanel);
        startPanel.setLayout(startPanelLayout);
        startPanelLayout.setHorizontalGroup(
            startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 338, Short.MAX_VALUE)
        );
        startPanelLayout.setVerticalGroup(
            startPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 633, Short.MAX_VALUE)
        );

        optionsTabbedPane.addTab("", startPanel);
        
		jXTaskPane4.setCollapsed(true);
		jXTaskPane4.setTitle("Cargar Fórmula");
		jXTaskPane4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jXTaskPane4MouseClicked(evt);
			}
		});
		jXTaskPaneContainer2.add(jXTaskPane4);

		jXTaskPane5.setCollapsed(true);
		jXTaskPane5.setTitle("Modificar Fórmula");
		jXTaskPane5.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jXTaskPane5MouseClicked(evt);
			}
		});
		jXTaskPaneContainer2.add(jXTaskPane5);

		jXTaskPane6.setCollapsed(true);
		jXTaskPane6.setTitle("Eliminar Fórmula");
		jXTaskPane6.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jXTaskPane6MouseClicked(evt);
			}
		});
		jXTaskPaneContainer2.add(jXTaskPane6);

		javax.swing.GroupLayout formulaPanelLayout = new javax.swing.GroupLayout(
				formulaPanel);
		formulaPanel.setLayout(formulaPanelLayout);
		formulaPanelLayout
				.setHorizontalGroup(formulaPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								formulaPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jXTaskPaneContainer2,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												318, Short.MAX_VALUE)
										.addContainerGap()));
		formulaPanelLayout.setVerticalGroup(formulaPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						formulaPanelLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(jXTaskPaneContainer2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										189,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(433, Short.MAX_VALUE)));

		optionsTabbedPane.addTab("Formulas", formulaPanel);

		jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel7.setText("REPORTE");

		javax.swing.GroupLayout reportePanelLayout = new javax.swing.GroupLayout(
				reportePanel);
		reportePanel.setLayout(reportePanelLayout);
		reportePanelLayout.setHorizontalGroup(reportePanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						reportePanelLayout.createSequentialGroup()
								.addGap(22, 22, 22).addComponent(jLabel7)
								.addContainerGap(266, Short.MAX_VALUE)));
		reportePanelLayout.setVerticalGroup(reportePanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						reportePanelLayout.createSequentialGroup()
								.addContainerGap().addComponent(jLabel7)
								.addContainerGap(608, Short.MAX_VALUE)));

		optionsTabbedPane.addTab("Reportes", reportePanel);

		javax.swing.GroupLayout backUpPanelLayout = new javax.swing.GroupLayout(
				backUpPanel);
		backUpPanel.setLayout(backUpPanelLayout);
		backUpPanelLayout.setHorizontalGroup(backUpPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 338, Short.MAX_VALUE));
		backUpPanelLayout.setVerticalGroup(backUpPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 633, Short.MAX_VALUE));

		optionsTabbedPane.addTab("Backup", backUpPanel);

		jXTaskPane7.setCollapsed(true);
		jXTaskPane7.setTitle("Crear Material");
		jXTaskPane7.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jXTaskPane7MouseClicked(evt);
			}
		});
		jXTaskPaneContainer3.add(jXTaskPane7);

		jXTaskPane8.setCollapsed(true);
		jXTaskPane8.setTitle("Editar Material");
		jXTaskPane8.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jXTaskPane8MouseClicked(evt);
			}
		});
		jXTaskPaneContainer3.add(jXTaskPane8);

		jXTaskPane9.setCollapsed(true);
		jXTaskPane9.setTitle("Eliminar Material");
		jXTaskPane9.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jXTaskPane9MouseClicked(evt);
			}
		});
		jXTaskPaneContainer3.add(jXTaskPane9);

		javax.swing.GroupLayout materialPanelLayout = new javax.swing.GroupLayout(
				materialPanel);
		materialPanel.setLayout(materialPanelLayout);
		materialPanelLayout
				.setHorizontalGroup(materialPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								materialPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jXTaskPaneContainer3,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												318, Short.MAX_VALUE)
										.addContainerGap()));
		materialPanelLayout.setVerticalGroup(materialPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						materialPanelLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(jXTaskPaneContainer3,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										189,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(433, Short.MAX_VALUE)));

		optionsTabbedPane.addTab("Material", materialPanel);

		jXTaskPane10.setCollapsed(true);
		jXTaskPane10.setTitle("Crear Producto");
		jXTaskPane10.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jXTaskPane10MouseClicked(evt);
			}
		});
		jXTaskPaneContainer4.add(jXTaskPane10);

		jXTaskPane11.setCollapsed(true);
		jXTaskPane11.setTitle("Editar Producto");
		jXTaskPane11.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jXTaskPane11MouseClicked(evt);
			}
		});
		jXTaskPaneContainer4.add(jXTaskPane11);

		jXTaskPane12.setCollapsed(true);
		jXTaskPane12.setTitle("Eliminar Producto");
		jXTaskPane12.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jXTaskPane12MouseClicked(evt);
			}
		});
		jXTaskPaneContainer4.add(jXTaskPane12);

		javax.swing.GroupLayout productoPanelLayout = new javax.swing.GroupLayout(
				productoPanel);
		productoPanel.setLayout(productoPanelLayout);
		productoPanelLayout
				.setHorizontalGroup(productoPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								productoPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jXTaskPaneContainer4,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												318, Short.MAX_VALUE)
										.addContainerGap()));
		productoPanelLayout.setVerticalGroup(productoPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						productoPanelLayout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(jXTaskPaneContainer4,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										189,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(433, Short.MAX_VALUE)));

		optionsTabbedPane.addTab("Producto", productoPanel);

		javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(
				optionsPanel);
		optionsPanel.setLayout(optionsPanelLayout);
		optionsPanelLayout.setHorizontalGroup(optionsPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(optionsTabbedPane));
		optionsPanelLayout.setVerticalGroup(optionsPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						optionsPanelLayout
								.createSequentialGroup()
								.addComponent(optionsTabbedPane,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										638,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)));

		optionsTabbedPane.getAccessibleContext().setAccessibleName("Tab2");

		workArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		iacnaLogo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
		iacnaLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/media/IACLogo.png"))); // NOI18N

		operationsPanel.setLayout(new java.awt.CardLayout());

		javax.swing.GroupLayout PanFrontDesLayout = new javax.swing.GroupLayout(
				PanFrontDes);
		PanFrontDes.setLayout(PanFrontDesLayout);
		PanFrontDesLayout.setHorizontalGroup(PanFrontDesLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 870, Short.MAX_VALUE));
		PanFrontDesLayout.setVerticalGroup(PanFrontDesLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 553, Short.MAX_VALUE));

		operationsPanel.add(PanFrontDes, "card1");

		jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel17.setText("Nombre:");

		jTextArea2.setColumns(20);
		jTextArea2.setRows(5);
		jScrollPane6.setViewportView(jTextArea2);

		jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel18.setText("Descripción:");

		jButton10.setText("Guardar");
		jButton10.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton10ActionPerformed(evt);
			}
		});

		jButton11.setText("Cancelar");
		jButton11.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton11ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout PanAltaMatDesLayout = new javax.swing.GroupLayout(
				PanAltaMatDes);
		PanAltaMatDes.setLayout(PanAltaMatDesLayout);
		PanAltaMatDesLayout
				.setHorizontalGroup(PanAltaMatDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanAltaMatDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanAltaMatDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																PanAltaMatDesLayout
																		.createSequentialGroup()
																		.addGroup(
																				PanAltaMatDesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel17,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								56,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel18))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				PanAltaMatDesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane6)
																						.addGroup(
																								PanAltaMatDesLayout
																										.createSequentialGroup()
																										.addComponent(
																												jTextField3,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												220,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(0,
																												558,
																												Short.MAX_VALUE))))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																PanAltaMatDesLayout
																		.createSequentialGroup()
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButton10)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton11)))
										.addContainerGap()));
		PanAltaMatDesLayout
				.setVerticalGroup(PanAltaMatDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanAltaMatDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanAltaMatDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel17)
														.addComponent(
																jTextField3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												PanAltaMatDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane6,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																128,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel18))
										.addGap(294, 294, 294)
										.addGroup(
												PanAltaMatDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton10)
														.addComponent(jButton11))
										.addContainerGap(59, Short.MAX_VALUE)));

		operationsPanel.add(PanAltaMatDes, "card2");

		jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel8.setText("Nombre:");

		jTable2.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null }, { null, null, null },
						{ null, null, null } }, new String[] { "Compuesto",
						"Descripcion", "Status" }) {
			boolean[] canEdit = new boolean[] { false, false, true };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane2.setViewportView(jTable2);

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jTextArea1.setText("Editar Descripción");
		jScrollPane1.setViewportView(jTextArea1);

		jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel4.setText("Descripción:");

		jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel15.setText("Compuestos:");

		jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Seleccione un elemento", "Elemento1", "Elemento2",
				"Elemento3", "Elemento4", "Elemento5" }));

		jButton6.setText("Agregar");

		jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel16.setText("Bascula Asignada:");

		jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Seleccione una báscula", "Bascula 1", "Bascula 2",
				"Bascula 3", "Bascula 4", "Bascula 5" }));

		jButton7.setText("Actualizar");
		jButton7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton7ActionPerformed(evt);
			}
		});

		jButton8.setText("Cancelar");
		jButton8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton8ActionPerformed(evt);
			}
		});

		jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Seleccione Fórmula", "Fórmula 1", "Fórmula 2",
				"Fórmula 3", "Fórmula 4", "Fórmula 5" }));

		javax.swing.GroupLayout PanEditarFormDesLayout = new javax.swing.GroupLayout(
				PanEditarFormDes);
		PanEditarFormDes.setLayout(PanEditarFormDesLayout);
		PanEditarFormDesLayout
				.setHorizontalGroup(PanEditarFormDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanEditarFormDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanEditarFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																PanEditarFormDesLayout
																		.createSequentialGroup()
																		.addGroup(
																				PanEditarFormDesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel8,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								56,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel4))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				PanEditarFormDesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								0,
																								Short.MAX_VALUE)
																						.addComponent(
																								jScrollPane1)
																						.addGroup(
																								PanEditarFormDesLayout
																										.createSequentialGroup()
																										.addGroup(
																												PanEditarFormDesLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addGroup(
																																PanEditarFormDesLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				jLabel16)
																																		.addPreferredGap(
																																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																		.addComponent(
																																				jComboBox6,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				196,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																PanEditarFormDesLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				jLabel15)
																																		.addPreferredGap(
																																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																		.addComponent(
																																				jComboBox5,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addGap(18,
																																				18,
																																				18)
																																		.addComponent(
																																				jButton6))
																														.addComponent(
																																jComboBox7,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																219,
																																javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGap(0,
																												467,
																												Short.MAX_VALUE))))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																PanEditarFormDesLayout
																		.createSequentialGroup()
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButton7)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton8)))
										.addContainerGap()));
		PanEditarFormDesLayout
				.setVerticalGroup(PanEditarFormDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanEditarFormDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanEditarFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel8)
														.addComponent(
																jComboBox7,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												PanEditarFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																128,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel4))
										.addGap(15, 15, 15)
										.addGroup(
												PanEditarFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jComboBox5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jButton6)
														.addComponent(
																jLabel15,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																23,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												113,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(25, 25, 25)
										.addGroup(
												PanEditarFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel16)
														.addComponent(
																jComboBox6,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(80, 80, 80)
										.addGroup(
												PanEditarFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton7)
														.addComponent(jButton8))
										.addContainerGap(59, Short.MAX_VALUE)));

		operationsPanel.add(PanEditarFormDes, "card13");

		jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel14.setText("Formula:");

		jComboBox10.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Seleccione Fórmula", "Formula 1", "Formula 2", "Formula 3",
				"Formula 4" }));
		jComboBox10.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBox10ItemStateChanged(evt);
			}
		});
		jComboBox10.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox10ActionPerformed(evt);
			}
		});

		jButton12.setText("Eliminar");
		jButton12.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton12ActionPerformed(evt);
			}
		});

		jButton13.setText("Cancelar");

		javax.swing.GroupLayout PanLeerFormDesLayout = new javax.swing.GroupLayout(
				PanLeerFormDes);
		PanLeerFormDes.setLayout(PanLeerFormDesLayout);
		PanLeerFormDesLayout
				.setHorizontalGroup(PanLeerFormDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanLeerFormDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel14)
										.addGap(18, 18, 18)
										.addComponent(
												jComboBox10,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												172,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(621, Short.MAX_VALUE))
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								PanLeerFormDesLayout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(jButton12)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jButton13)
										.addGap(12, 12, 12)));
		PanLeerFormDesLayout
				.setVerticalGroup(PanLeerFormDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanLeerFormDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanLeerFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jComboBox10,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel14))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												225, Short.MAX_VALUE)
										.addGroup(
												PanLeerFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton12)
														.addComponent(jButton13))
										.addGap(274, 274, 274)));

		operationsPanel.add(PanLeerFormDes, "card4");

		jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel21.setText("Fecha");

		jButton15.setText("Exportar");
		jButton15.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton15ActionPerformed(evt);
			}
		});

		jScrollPane3.setPreferredSize(new java.awt.Dimension(870, 402));

		jTable1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null },
						{ null, null, null, null, null, null, null, null, null,
								null } }, new String[] { "Bascula",
						"Descripcion", "U/M", "Cantidad", "Min", "Max",
						"Batch 1", "Batch 2", "....", "Batch n" }) {
			boolean[] canEdit = new boolean[] { false, false, false, true,
					false, true, true, true, true, true };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane3.setViewportView(jTable1);

		javax.swing.GroupLayout PanReportDesLayout = new javax.swing.GroupLayout(
				PanReportDes);
		PanReportDes.setLayout(PanReportDesLayout);
		PanReportDesLayout
				.setHorizontalGroup(PanReportDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								PanReportDesLayout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(jButton15)
										.addGap(12, 12, 12))
						.addGroup(
								PanReportDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanReportDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel21)
														.addComponent(
																jScrollPane3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		PanReportDesLayout
				.setVerticalGroup(PanReportDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanReportDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel21)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												348,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(jButton15)
										.addGap(274, 274, 274)));

		operationsPanel.add(PanReportDes, "card5");

		jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel9.setText("Nombre:");

		jTextArea4.setColumns(20);
		jTextArea4.setRows(5);
		jTextArea4.setText("Editar Descripción");
		jScrollPane5.setViewportView(jTextArea4);

		jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel10.setText("Descripción:");

		jButton18.setText("Actualizar");
		jButton18.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton18ActionPerformed(evt);
			}
		});

		jButton19.setText("Cancelar");
		jButton19.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton19ActionPerformed(evt);
			}
		});

		jComboBox13.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Seleccione Material", "Fórmula 1", "Fórmula 2",
				"Fórmula 3", "Fórmula 4", "Fórmula 5" }));

		javax.swing.GroupLayout PanEditaMatDesLayout = new javax.swing.GroupLayout(
				PanEditaMatDes);
		PanEditaMatDes.setLayout(PanEditaMatDesLayout);
		PanEditaMatDesLayout
				.setHorizontalGroup(PanEditaMatDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanEditaMatDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanEditaMatDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																PanEditaMatDesLayout
																		.createSequentialGroup()
																		.addGroup(
																				PanEditaMatDesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel9,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								56,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel10))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				PanEditaMatDesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane5)
																						.addGroup(
																								PanEditaMatDesLayout
																										.createSequentialGroup()
																										.addComponent(
																												jComboBox13,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												219,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(0,
																												559,
																												Short.MAX_VALUE))))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																PanEditaMatDesLayout
																		.createSequentialGroup()
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButton18)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton19)))
										.addContainerGap()));
		PanEditaMatDesLayout
				.setVerticalGroup(PanEditaMatDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanEditaMatDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanEditaMatDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel9)
														.addComponent(
																jComboBox13,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												PanEditaMatDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																128,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel10))
										.addGap(294, 294, 294)
										.addGroup(
												PanEditaMatDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton18)
														.addComponent(jButton19))
										.addContainerGap(59, Short.MAX_VALUE)));

		operationsPanel.add(PanEditaMatDes, "card7");

		jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel19.setText("Material:");

		jComboBox14.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Seleccione Material", "Formula 1", "Formula 2", "Formula 3",
				"Formula 4" }));
		jComboBox14.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBox14ItemStateChanged(evt);
			}
		});
		jComboBox14.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox14ActionPerformed(evt);
			}
		});

		jButton20.setText("Eliminar");
		jButton20.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton20ActionPerformed(evt);
			}
		});

		jButton21.setText("Cancelar");

		javax.swing.GroupLayout PanDeletMatDesLayout = new javax.swing.GroupLayout(
				PanDeletMatDes);
		PanDeletMatDes.setLayout(PanDeletMatDesLayout);
		PanDeletMatDesLayout
				.setHorizontalGroup(PanDeletMatDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanDeletMatDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel19)
										.addGap(18, 18, 18)
										.addComponent(
												jComboBox14,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												172,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(620, Short.MAX_VALUE))
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								PanDeletMatDesLayout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(jButton20)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jButton21)
										.addGap(12, 12, 12)));
		PanDeletMatDesLayout
				.setVerticalGroup(PanDeletMatDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanDeletMatDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanDeletMatDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jComboBox14,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel19))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												225, Short.MAX_VALUE)
										.addGroup(
												PanDeletMatDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton20)
														.addComponent(jButton21))
										.addGap(274, 274, 274)));

		operationsPanel.add(PanDeletMatDes, "card8");

		jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel20.setText("Nombre:");

		jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel26.setText("Descripción:");

		jButton22.setText("Guardar");
		jButton22.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton22ActionPerformed(evt);
			}
		});

		jButton23.setText("Cancelar");
		jButton23.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton23ActionPerformed(evt);
			}
		});

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Seleccionar Material", "Material 1", "Material 2",
				"Material 3" }));

		javax.swing.GroupLayout PanAltaProdDesLayout = new javax.swing.GroupLayout(
				PanAltaProdDes);
		PanAltaProdDes.setLayout(PanAltaProdDesLayout);
		PanAltaProdDesLayout
				.setHorizontalGroup(PanAltaProdDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanAltaProdDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanAltaProdDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																PanAltaProdDesLayout
																		.createSequentialGroup()
																		.addGroup(
																				PanAltaProdDesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel20,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								56,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel26))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				PanAltaProdDesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								jTextField5,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								220,
																								Short.MAX_VALUE)
																						.addComponent(
																								jComboBox1,
																								0,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addGap(0,
																				0,
																				Short.MAX_VALUE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																PanAltaProdDesLayout
																		.createSequentialGroup()
																		.addGap(0,
																				698,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButton22)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton23)))
										.addContainerGap()));
		PanAltaProdDesLayout
				.setVerticalGroup(PanAltaProdDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanAltaProdDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanAltaProdDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel20)
														.addComponent(
																jTextField5,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												PanAltaProdDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel26)
														.addComponent(
																jComboBox1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(405, 405, 405)
										.addGroup(
												PanAltaProdDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton22)
														.addComponent(jButton23))
										.addContainerGap(56, Short.MAX_VALUE)));

		operationsPanel.add(PanAltaProdDes, "card9");

		jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel11.setText("Material:");

		jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel12.setText("Producto:");

		jButton24.setText("Actualizar");
		jButton24.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton24ActionPerformed(evt);
			}
		});

		jButton25.setText("Cancelar");
		jButton25.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton25ActionPerformed(evt);
			}
		});

		jComboBox15.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Seleccione Material", "Material 1", "Material 2",
				"Material 3", "Material 4", "Material 5" }));

		jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Seleccionar Producto", "Producto 1", "Producto 2",
				"Producto 3", "Producto 4" }));

		javax.swing.GroupLayout PanEditaProdDesLayout = new javax.swing.GroupLayout(
				PanEditaProdDes);
		PanEditaProdDes.setLayout(PanEditaProdDesLayout);
		PanEditaProdDesLayout
				.setHorizontalGroup(PanEditaProdDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanEditaProdDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanEditaProdDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																PanEditaProdDesLayout
																		.createSequentialGroup()
																		.addGroup(
																				PanEditaProdDesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel11,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								56,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabel12))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				PanEditaProdDesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								jComboBox15,
																								0,
																								219,
																								Short.MAX_VALUE)
																						.addComponent(
																								jComboBox3,
																								0,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addGap(0,
																				571,
																				Short.MAX_VALUE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																PanEditaProdDesLayout
																		.createSequentialGroup()
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButton24)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton25)))
										.addContainerGap()));
		PanEditaProdDesLayout
				.setVerticalGroup(PanEditaProdDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanEditaProdDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanEditaProdDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel11)
														.addComponent(
																jComboBox15,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												PanEditaProdDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel12)
														.addComponent(
																jComboBox3,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(405, 405, 405)
										.addGroup(
												PanEditaProdDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton24)
														.addComponent(jButton25))
										.addContainerGap(56, Short.MAX_VALUE)));

		operationsPanel.add(PanEditaProdDes, "card10");

		jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel27.setText("Material:");

		jComboBox16.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Seleccione Material", "Formula 1", "Formula 2", "Formula 3",
				"Formula 4" }));
		jComboBox16.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				jComboBox16ItemStateChanged(evt);
			}
		});
		jComboBox16.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox16ActionPerformed(evt);
			}
		});

		jButton26.setText("Eliminar");
		jButton26.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton26ActionPerformed(evt);
			}
		});

		jButton27.setText("Cancelar");

		jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel2.setText("Producto:");

		jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] {
				"Seleccione un Producto", "Producto 1", "Producto 2",
				"Producto 3" }));

		javax.swing.GroupLayout PanDeletProdDesLayout = new javax.swing.GroupLayout(
				PanDeletProdDes);
		PanDeletProdDes.setLayout(PanDeletProdDesLayout);
		PanDeletProdDesLayout
				.setHorizontalGroup(PanDeletProdDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								PanDeletProdDesLayout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(jButton26)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jButton27)
										.addGap(12, 12, 12))
						.addGroup(
								PanDeletProdDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanDeletProdDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																PanDeletProdDesLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel27)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jComboBox16,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				172,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																PanDeletProdDesLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel2)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				jComboBox4,
																				0,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addContainerGap(620, Short.MAX_VALUE)));
		PanDeletProdDesLayout
				.setVerticalGroup(PanDeletProdDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanDeletProdDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanDeletProdDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jComboBox16,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel27))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												PanDeletProdDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																jComboBox4,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												194, Short.MAX_VALUE)
										.addGroup(
												PanDeletProdDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton26)
														.addComponent(jButton27))
										.addGap(274, 274, 274)));

		operationsPanel.add(PanDeletProdDes, "card11");

		labelFormula1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		labelFormula1.setText("Fórmula:");

		tableXLSXBase = new javax.swing.JTable() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; // Disallow the editing of any cell
			}
		};
		tableXLSXBase.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] {

				}));
		jScrollPane9.setViewportView(tableXLSXBase);

		jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel30.setText("Batch");

		buttonConfirmar.setText("Confirmar");
		buttonConfirmar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonConfirmarActionPerformed(evt);
			}
		});

		buttonCancelar.setText("Cancelar");
		buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonCancelarActionPerformed(evt);
			}
		});

		fieldFormulaPathXLS1.setEditable(false);

		buttonAbrirArchivo1.setText("Abrir");
		buttonAbrirArchivo1
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						buttonAbrirArchivo1ActionPerformed(evt);
					}
				});

		spinnerBatch.setModel(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));

		javax.swing.GroupLayout PanCargarFormDesLayout = new javax.swing.GroupLayout(
				PanCargarFormDes);
		PanCargarFormDes.setLayout(PanCargarFormDesLayout);
		PanCargarFormDesLayout
				.setHorizontalGroup(PanCargarFormDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanCargarFormDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanCargarFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																PanCargarFormDesLayout
																		.createSequentialGroup()
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)
																		.addComponent(
																				buttonConfirmar)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				buttonCancelar))
														.addGroup(
																PanCargarFormDesLayout
																		.createSequentialGroup()
																		.addComponent(
																				labelFormula1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				56,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				PanCargarFormDesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane9,
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addGroup(
																								PanCargarFormDesLayout
																										.createSequentialGroup()
																										.addGroup(
																												PanCargarFormDesLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addGroup(
																																PanCargarFormDesLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				jLabel30)
																																		.addPreferredGap(
																																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																		.addComponent(
																																				spinnerBatch,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				61,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																PanCargarFormDesLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				fieldFormulaPathXLS1,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				376,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(
																																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																		.addComponent(
																																				buttonAbrirArchivo1,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				68,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)))
																										.addGap(0,
																												322,
																												Short.MAX_VALUE)))))
										.addContainerGap()));
		PanCargarFormDesLayout
				.setVerticalGroup(PanCargarFormDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanCargarFormDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanCargarFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																labelFormula1)
														.addComponent(
																fieldFormulaPathXLS1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																buttonAbrirArchivo1))
										.addGap(18, 18, 18)
										.addComponent(
												jScrollPane9,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												323,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												PanCargarFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel30)
														.addComponent(
																spinnerBatch,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(68, 68, 68)
										.addGroup(
												PanCargarFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																buttonConfirmar)
														.addComponent(
																buttonCancelar))
										.addContainerGap(56, Short.MAX_VALUE)));

		operationsPanel.add(PanCargarFormDes, "card6");

		labelFormula2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		labelFormula2.setText("Fórmula:");

		tableXLSXBase1.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {

				}, new String[] {

				}));
		jScrollPane10.setViewportView(tableXLSXBase1);

		jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel31.setText("Batch");

		jButton31.setText("Actualizar");
		jButton31.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton31ActionPerformed(evt);
			}
		});

		jButton32.setText("Cancelar");
		jButton32.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton32ActionPerformed(evt);
			}
		});

		fieldFormulaPathXLS2.setEditable(false);

		buttonAbrirArchivo2.setText("Abrir");
		buttonAbrirArchivo2
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						buttonAbrirArchivo2ActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout PanModiicarFormDesLayout = new javax.swing.GroupLayout(
				PanModiicarFormDes);
		PanModiicarFormDes.setLayout(PanModiicarFormDesLayout);
		PanModiicarFormDesLayout
				.setHorizontalGroup(PanModiicarFormDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanModiicarFormDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanModiicarFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																PanModiicarFormDesLayout
																		.createSequentialGroup()
																		.addGap(0,
																				0,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButton31)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jButton32))
														.addGroup(
																PanModiicarFormDesLayout
																		.createSequentialGroup()
																		.addComponent(
																				labelFormula2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				56,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				PanModiicarFormDesLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane10,
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addGroup(
																								PanModiicarFormDesLayout
																										.createSequentialGroup()
																										.addGroup(
																												PanModiicarFormDesLayout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addGroup(
																																PanModiicarFormDesLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				jLabel31)
																																		.addPreferredGap(
																																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																		.addComponent(
																																				jSpinner2,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				61,
																																				javax.swing.GroupLayout.PREFERRED_SIZE))
																														.addGroup(
																																PanModiicarFormDesLayout
																																		.createSequentialGroup()
																																		.addComponent(
																																				fieldFormulaPathXLS2,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				376,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)
																																		.addPreferredGap(
																																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																		.addComponent(
																																				buttonAbrirArchivo2,
																																				javax.swing.GroupLayout.PREFERRED_SIZE,
																																				68,
																																				javax.swing.GroupLayout.PREFERRED_SIZE)))
																										.addGap(0,
																												322,
																												Short.MAX_VALUE)))))
										.addContainerGap()));
		PanModiicarFormDesLayout
				.setVerticalGroup(PanModiicarFormDesLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								PanModiicarFormDesLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												PanModiicarFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																labelFormula2)
														.addComponent(
																fieldFormulaPathXLS2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																buttonAbrirArchivo2))
										.addGap(18, 18, 18)
										.addComponent(
												jScrollPane10,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												323,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												PanModiicarFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel31)
														.addComponent(
																jSpinner2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(68, 68, 68)
										.addGroup(
												PanModiicarFormDesLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jButton31)
														.addComponent(jButton32))
										.addContainerGap(56, Short.MAX_VALUE)));

		operationsPanel.add(PanModiicarFormDes, "card3");

        iacnaTitleApplicationLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        iacnaTitleApplicationLabel.setText("SISTEMA DE MONITOREO DE PESAJE IACNA S.A DE C.V");

        javax.swing.GroupLayout workAreaLayout = new javax.swing.GroupLayout(workArea);
        workArea.setLayout(workAreaLayout);
        workAreaLayout.setHorizontalGroup(
            workAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(workAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(operationsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                    .addGroup(workAreaLayout.createSequentialGroup()
                        .addGroup(workAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(iacnaTitleApplicationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userLogged, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(iacnaLogo)))
                .addContainerGap())
        );
        workAreaLayout.setVerticalGroup(
                workAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(workAreaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(workAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(iacnaLogo)
                        .addGroup(workAreaLayout.createSequentialGroup()
                            .addComponent(userLogged, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(iacnaTitleApplicationLabel)))
                    .addGap(18, 18, 18)
                    .addComponent(operationsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                    .addContainerGap())
            );

		statusBarContainer.setBorder(javax.swing.BorderFactory
				.createEtchedBorder());

		progressBar.setIndeterminate(true);
		progressBar.setStringPainted(true);

		statusBarLabel.setText("Barra de estado de la aplicación");

		javax.swing.GroupLayout statusBarContainerLayout = new javax.swing.GroupLayout(
				statusBarContainer);
		statusBarContainer.setLayout(statusBarContainerLayout);
		statusBarContainerLayout
				.setHorizontalGroup(statusBarContainerLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								statusBarContainerLayout
										.createSequentialGroup()
										.addComponent(
												statusBarLabel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												1073,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												84, Short.MAX_VALUE)
										.addComponent(
												progressBar,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												156,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
		statusBarContainerLayout
				.setVerticalGroup(statusBarContainerLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								statusBarContainerLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(
												progressBar,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												statusBarLabel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												20,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		jMenu1.setText("Archivo");
		menuSalir.setText("Salir");
        menuSalir.setToolTipText("Salir del Sistema");
        menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalirActionPerformed(evt);
            }
        });
        jMenu1.add(menuSalir);
		menuBar.add(jMenu1);

		jMenu2.setText("Edicion");
		jMenu2.setToolTipText("Iniciar Sesión");
		jMenu2.addMenuListener(new javax.swing.event.MenuListener() {
			public void menuCanceled(javax.swing.event.MenuEvent evt) {
			}

			public void menuDeselected(javax.swing.event.MenuEvent evt) {
			}

			public void menuSelected(javax.swing.event.MenuEvent evt) {
				jMenu2MenuSelected(evt);
			}
		});
		jMenu2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenu2ActionPerformed(evt);
			}
		});
		menuBar.add(jMenu2);

		jMenu4.setText("Herramientas");
		menuBar.add(jMenu4);

		jMenu5.setText("Acerca de");
		menuBar.add(jMenu5);

		jMenu3.setText("Sesion");
		jMenu3.setToolTipText("Cerrar Sesin");
		jMenu3.addMenuListener(new javax.swing.event.MenuListener() {
			public void menuCanceled(javax.swing.event.MenuEvent evt) {
			}

			public void menuDeselected(javax.swing.event.MenuEvent evt) {
			}

			public void menuSelected(javax.swing.event.MenuEvent evt) {
				jMenu3MenuSelected(evt);
			}
		});
		
		subMenuIniciarSesion.setText("Iniciar Sesion");
        
        subMenuIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuIniciarSesionActionPerformed(evt);
            }
        });
        jMenu3.add(subMenuIniciarSesion);

        subMenuCerrarSesion.setText("Cerrar Sesion");
        subMenuCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuCerrarSesionActionPerformed(evt);
            }
        });
        jMenu3.add(subMenuCerrarSesion);
        
		menuBar.add(jMenu3);

		setJMenuBar(menuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(optionsPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(workArea,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addContainerGap())
				.addComponent(statusBarContainer,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														optionsPanel,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														workArea,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(statusBarContainer,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void optionsTabbedPaneMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_optionsTabbedPaneMousePressed

		CardLayout cl2 = (CardLayout) (operationsPanel.getLayout());

		switch (optionsTabbedPane.getModel().getSelectedIndex()) {
		case 0:
			cl2.show(operationsPanel, "card1");
			break;

		case 1:
			cl2.show(operationsPanel, "card5");
			break;
		case 2:
			cl2.show(operationsPanel, "card1");
			break;
		case 3:
			cl2.show(operationsPanel, "card1");
			break;
		case 4:
			cl2.show(operationsPanel, "card1");
			break;
		}

	}// GEN-LAST:event_optionsTabbedPaneMousePressed

	private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenu2ActionPerformed

	}// GEN-LAST:event_jMenu2ActionPerformed

	private void jMenu2MenuSelected(javax.swing.event.MenuEvent evt) {// GEN-FIRST:event_jMenu2MenuSelected

	}// GEN-LAST:event_jMenu2MenuSelected

	private void jMenu3MenuSelected(javax.swing.event.MenuEvent evt) {// GEN-FIRST:event_jMenu3MenuSelected

		CardLayout cl2 = (CardLayout) (operationsPanel.getLayout());

		cl2.show(operationsPanel, "card1");

		// jTabbedPane1.enable(false);
		// jMenu3.setEnabled(false);

	}// GEN-LAST:event_jMenu3MenuSelected

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton7ActionPerformed
		JOptionPane.showMessageDialog(null, "Fórmula almacenada");
	}// GEN-LAST:event_jButton7ActionPerformed

	private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton8ActionPerformed

		JOptionPane.showMessageDialog(null, "Operacion cancelada");
	}// GEN-LAST:event_jButton8ActionPerformed

	private void jXTaskPane4MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jXTaskPane4MouseClicked
		jXTaskPane4.setSpecial(true);
		jXTaskPane5.setSpecial(false);
		jXTaskPane6.setSpecial(false);
		if (!jXTaskPane4.isCollapsed()) {
			jXTaskPane5.setCollapsed(true);
			jXTaskPane6.setCollapsed(true);
		}
		CardLayout cl2 = (CardLayout) (operationsPanel.getLayout());
		cl2.show(operationsPanel, "card6");
	}// GEN-LAST:event_jXTaskPane4MouseClicked

	private void jXTaskPane5MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jXTaskPane5MouseClicked
		jXTaskPane5.setSpecial(true);
		jXTaskPane4.setSpecial(false);
		jXTaskPane6.setSpecial(false);
		if (!jXTaskPane5.isCollapsed()) {
			jXTaskPane4.setCollapsed(true);
			jXTaskPane6.setCollapsed(true);
		}
		CardLayout cl2 = (CardLayout) (operationsPanel.getLayout());
		cl2.show(operationsPanel, "card3");
	}// GEN-LAST:event_jXTaskPane5MouseClicked

	private void jXTaskPane6MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jXTaskPane6MouseClicked
		jXTaskPane6.setSpecial(true);
		jXTaskPane5.setSpecial(false);
		jXTaskPane4.setSpecial(false);

		if (!jXTaskPane6.isCollapsed()) {
			jXTaskPane4.setCollapsed(true);
			jXTaskPane5.setCollapsed(true);
		}

		CardLayout cl2 = (CardLayout) (operationsPanel.getLayout());
		cl2.show(operationsPanel, "card4");
	}// GEN-LAST:event_jXTaskPane6MouseClicked

	private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton10ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton10ActionPerformed

	private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton11ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton11ActionPerformed

	private void jComboBox10ItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jComboBox10ItemStateChanged
		// TODO add your handling code here:
	}// GEN-LAST:event_jComboBox10ItemStateChanged

	private void jComboBox10ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox10ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jComboBox10ActionPerformed

	private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton12ActionPerformed

		if (jComboBox10.getSelectedIndex() == 0)
			JOptionPane.showMessageDialog(null, "Seleccione una Fórmula");

		else {
			int a = JOptionPane.showConfirmDialog(null, "eliminar ''"
					+ jComboBox10.getSelectedItem().toString() + "''");
			if (a == 0)
				JOptionPane.showMessageDialog(null,
						"''" + jComboBox10.getSelectedItem() + "'' Eliminado");

			else
				JOptionPane
						.showMessageDialog(null, "OPERACION CANCELADA: ''"
								+ jComboBox10.getSelectedItem()
								+ "'' no fue aliminado");
		}
	}

	private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton15ActionPerformed
		JOptionPane.showMessageDialog(null, "Exportar a Axcel");
	}// GEN-LAST:event_jButton15ActionPerformed


    private void subMenuIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {
        boolean c=false;
        
        logged.setVisible(true);
        subMenuCerrarSesion.setEnabled(true);
        subMenuIniciarSesion.setEnabled(false);
        if(logged.getUser().equals("1")){
        	//Mostrar componentes supervisor: reportes
        	menuSalir.setEnabled(false);
        	optionsTabbedPane.setEnabledAt(2, true);
        	
        	subMenuCerrarSesion.setEnabled(true);
            subMenuIniciarSesion.setEnabled(false);
            userLogged.setText(logged.getName() +" en linea");
        }
        if(logged.getUser().equals("3")){
        	//Mostrar componentes ADMINISTRADOR mostrar todo
        	menuSalir.setEnabled(false);
        	
        	optionsTabbedPane.setEnabledAt(1, true);
            optionsTabbedPane.setEnabledAt(2, true);
            optionsTabbedPane.setEnabledAt(3, true);
            optionsTabbedPane.setEnabledAt(4, true);
            optionsTabbedPane.setEnabledAt(5, true);
            jXTaskPane4.setVisible(true);
            jXTaskPane5.setVisible(true);
            jXTaskPane6.setVisible(false);
            subMenuCerrarSesion.setEnabled(true);
            subMenuIniciarSesion.setEnabled(false);
            userLogged.setText(logged.getName() +" en linea");
        }
        if(logged.getUser().equals("5")){
        	//Mostrar componentes para operador
        	menuSalir.setEnabled(false);
        	optionsTabbedPane.setEnabledAt(1, true);
        	jXTaskPane4.setVisible(false);
            jXTaskPane5.setVisible(false);
            jXTaskPane6.setVisible(true);
        	subMenuCerrarSesion.setEnabled(true);
            subMenuIniciarSesion.setEnabled(false);
            userLogged.setText(logged.getName() +" en linea");
        }
    }    
    
	private void jXTaskPane7MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jXTaskPane7MouseClicked
		jXTaskPane7.setSpecial(true);
		jXTaskPane8.setSpecial(false);
		jXTaskPane9.setSpecial(false);
		if (!jXTaskPane7.isCollapsed()) {
			jXTaskPane8.setCollapsed(true);
			jXTaskPane9.setCollapsed(true);
		}
		CardLayout cl2 = (CardLayout) (operationsPanel.getLayout());
		cl2.show(operationsPanel, "card2");
	}// GEN-LAST:event_jXTaskPane7MouseClicked

	private void jXTaskPane8MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jXTaskPane8MouseClicked

		jXTaskPane7.setSpecial(false);
		jXTaskPane8.setSpecial(true);
		jXTaskPane9.setSpecial(false);
		if (!jXTaskPane8.isCollapsed()) {
			jXTaskPane7.setCollapsed(true);
			jXTaskPane9.setCollapsed(true);
		}
		CardLayout cl2 = (CardLayout) (operationsPanel.getLayout());
		cl2.show(operationsPanel, "card7");
	}// GEN-LAST:event_jXTaskPane8MouseClicked

	private void jXTaskPane9MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jXTaskPane9MouseClicked

		jXTaskPane7.setSpecial(false);
		jXTaskPane8.setSpecial(false);
		jXTaskPane9.setSpecial(true);
		if (!jXTaskPane9.isCollapsed()) {
			jXTaskPane7.setCollapsed(true);
			jXTaskPane8.setCollapsed(true);
		}
		CardLayout cl2 = (CardLayout) (operationsPanel.getLayout());
		cl2.show(operationsPanel, "card8");
	}// GEN-LAST:event_jXTaskPane9MouseClicked

	private void jXTaskPane10MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jXTaskPane10MouseClicked

		jXTaskPane10.setSpecial(true);
		jXTaskPane11.setSpecial(false);
		jXTaskPane12.setSpecial(false);
		if (!jXTaskPane10.isCollapsed()) {
			jXTaskPane11.setCollapsed(true);
			jXTaskPane12.setCollapsed(true);
		}
		CardLayout cl2 = (CardLayout) (operationsPanel.getLayout());
		cl2.show(operationsPanel, "card9");
	}// GEN-LAST:event_jXTaskPane10MouseClicked

	private void jXTaskPane11MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jXTaskPane11MouseClicked

		jXTaskPane10.setSpecial(false);
		jXTaskPane11.setSpecial(true);
		jXTaskPane12.setSpecial(false);
		if (!jXTaskPane11.isCollapsed()) {
			jXTaskPane10.setCollapsed(true);
			jXTaskPane12.setCollapsed(true);
		}
		CardLayout cl2 = (CardLayout) (operationsPanel.getLayout());
		cl2.show(operationsPanel, "card10");
	}// GEN-LAST:event_jXTaskPane11MouseClicked

	private void jXTaskPane12MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jXTaskPane12MouseClicked

		jXTaskPane10.setSpecial(false);
		jXTaskPane11.setSpecial(false);
		jXTaskPane12.setSpecial(true);
		if (!jXTaskPane12.isCollapsed()) {
			jXTaskPane11.setCollapsed(true);
			jXTaskPane10.setCollapsed(true);
		}
		CardLayout cl2 = (CardLayout) (operationsPanel.getLayout());
		cl2.show(operationsPanel, "card11");
	}// GEN-LAST:event_jXTaskPane12MouseClicked

	private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton18ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton18ActionPerformed

	private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton19ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton19ActionPerformed

	private void jComboBox14ItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jComboBox14ItemStateChanged
		// TODO add your handling code here:
	}// GEN-LAST:event_jComboBox14ItemStateChanged

	private void jComboBox14ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox14ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jComboBox14ActionPerformed

	private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton20ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton20ActionPerformed

	private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton22ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton22ActionPerformed

	private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton23ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton23ActionPerformed

	private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton24ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton24ActionPerformed

	private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton25ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton25ActionPerformed

	private void jComboBox16ItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_jComboBox16ItemStateChanged
		// TODO add your handling code here:
	}// GEN-LAST:event_jComboBox16ItemStateChanged

	private void jComboBox16ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBox16ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jComboBox16ActionPerformed

	private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton26ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton26ActionPerformed

	private void buttonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {
		
		for (int i = 0; i < tableXLSXBase.getRowCount(); i++) {
			for (int j = 0; j < tableXLSXBase.getColumnCount(); j++) {
				switch (j) {
				
				}
			}
			//IACNAWebServiceImpl ws = new IACNAWebServiceImpl();
		}
	}

	private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buttonCancelarActionPerformed
		tableXLSXBase.setModel(new DefaultTableModel());

		spinnerBatch.setValue(new Integer(1));
		spinnerBatch.setEnabled(false);
		buttonConfirmar.setEnabled(false);
		buttonCancelar.setEnabled(false);
		buttonAbrirArchivo1.setEnabled(true);
		fieldFormulaPathXLS1.setText("");
	}

	private void buttonAbrirArchivo1ActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buttonAbrirArchivo1ActionPerformed
		List sheetData = new ArrayList();
		File file = getFile();

		FileInputStream stream;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		Iterator rows;
		XSSFRow row;
		List data = null;
		XSSFCell cell;

		try {
			if (file != null) {
				fieldFormulaPathXLS1.setText(file.toString());
				stream = new FileInputStream(file.toString());

				try {
					workbook = new XSSFWorkbook(stream);
					sheet = workbook.getSheetAt(getIndexSheet(
							workbook.getNumberOfSheets(), workbook));

					rows = sheet.rowIterator();
					while (rows.hasNext()) {

						row = ((XSSFRow) rows.next());
						Iterator cells = row.cellIterator();

						data = new ArrayList();
						while (cells.hasNext()) {
							cell = (XSSFCell) cells.next();
							data.add(cell);
						}
						sheetData.add(data);
					}// END while

					showExcelData(sheetData);

					buttonAbrirArchivo1.setEnabled(false);
					spinnerBatch.setEnabled(true);
					buttonConfirmar.setEnabled(true);
					buttonCancelar.setEnabled(true);
				} catch (IOException ex) {
				}
			}
		} catch (FileNotFoundException ex) {
		}
		System.gc();
		Runtime.getRuntime().gc();
	}// GEN-LAST:event_buttonAbrirArchivo1ActionPerformed

	private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton31ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton31ActionPerformed

	private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton32ActionPerformed

	}// GEN-LAST:event_jButton32ActionPerformed

	private void buttonAbrirArchivo2ActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buttonAbrirArchivo2ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_buttonAbrirArchivo2ActionPerformed

	public int getIndexSheet(int sheetCount, XSSFWorkbook workbook) {
		int index = 0;
		for (int i = 0; i < sheetCount; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("Formula"))
				index = i;
		}
		return index;
	}

	public File getFile() {
		File file = null;

		try {
			JFileChooser fileChooser = new JFileChooser();

			fileChooser.addChoosableFileFilter(new ExtensionFileFilter(
					new String[] { ".XLS", ".XLSX" },
					"Documentos de Excel   (*.XLS|XLSX)"));

			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.setDialogTitle("Cargar Archivo de Excel");
			int result = fileChooser.showOpenDialog(null);

			if (result == JFileChooser.APPROVE_OPTION) {

				file = fileChooser.getSelectedFile();
			} else if (result == JFileChooser.CANCEL_OPTION) {
				statusBarLabel.setText("Cargar archivo de Excel: Cancelado");// enviar
																				// mensaje
																				// a
																				// la
																				// barra
																				// de
																				// estado
			}
		} catch (Exception ex) {
		}
		return file;
	}

private void subMenuCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {
        
        switch(JOptionPane.showConfirmDialog(null, "Realmente desea cerrar la sesion")){
            case 0:
                Component componentes[] = getContentPane().getComponents(); 
                //System.out.println(componentes[].length);
                for(int i=0; i<componentes.length;i++)
                {
                    String clase = componentes[i].getClass().getName();

                    if(clase.equals("javax.swing.JTextField"))
                    {
                        JTextField tmp = (JTextField)componentes[i];
                        tmp.setText("");
                    }

                    if(clase.equals("javax.swing.JPasswordField"))
                    {
                        JPasswordField tmp = (JPasswordField)componentes[i];
                        tmp.setText("");
                    }

                    if(clase.equals("javax.swing.JComboBox"))
                    {
                        JComboBox tmp = (JComboBox)componentes[i];
                        tmp.setSelectedIndex(0);
                    }

                    if(clase.equals("javax.swing.JCheckBox"))
                    {
                        JCheckBox tmp = (JCheckBox)componentes[i];
                        tmp.setSelected(false);
                    }
                    if(clase.equals("javax.swing.JTable"))
                    {
                        JTable tmp = (JTable)componentes[i];
                        tmp.setModel(new DefaultTableModel());
                    } 
                }
                menuSalir.setEnabled(true);
                userLogged.setText("");
                optionsTabbedPane.setEnabledAt(1, false);
                optionsTabbedPane.setEnabledAt(2, false);
                optionsTabbedPane.setEnabledAt(3, false);
                optionsTabbedPane.setEnabledAt(4, false);
                optionsTabbedPane.setEnabledAt(5, false);
                subMenuCerrarSesion.setEnabled(false);
                subMenuCerrarSesion.setEnabled(false);
                subMenuIniciarSesion.setEnabled(true);
                optionsTabbedPane.setSelectedIndex(0);
                break;
            case 1:  System.out.println("NO Cerrar session");
                break;
        }
        
    }

	private void menuSalirActionPerformed(java.awt.event.ActionEvent evt) {
		switch(JOptionPane.showConfirmDialog(null, "Confirmar salida del sistema IACNA")){
        case 0: System.exit(0);
            break;
    }
		
	}
	public void showExcelData(List sheetData) {

		int col = 0, row = -1, tempRow = 0;
		String salida = "";
		List list = null;
		XSSFCell cell;
		boolean start = false;

		try {
			for (int i = 0; i < sheetData.size(); i++) {
				Vector campos = new Vector();
				list = (List) sheetData.get(i);
				for (int j = 0; j < list.size(); j++) {
					cell = (XSSFCell) list.get(j);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						salida = cell.getRichStringCellValue().getString();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							salida = cell.getDateCellValue().toString();
						} else {
							// salida=String.valueOf(cell.getNumericCellValue());
							salida = String.valueOf(((int) cell
									.getNumericCellValue()));
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						salida = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						salida = String.valueOf(cell.getCellFormula());
						break;
					}

					if (!salida.isEmpty() && salida.length() > 9) {// Identificar
																	// inicio de
																	// informacion
																	// para la
																	// tabla
						if (salida.substring(0, 8).equalsIgnoreCase("Formulac"))
							start = true;
					}

					if (tempRow == 2) {// Generar Columnas
						if (j < 8)
							if (!salida.isEmpty())
								generarColumna(salida,
										(cell.getColumnIndex() + col));
							else
								col -= 1;
					}

					if (tempRow > 3) {// pre carga de informacion
						if (j < 8) {
							if (!salida.equals(""))
								campos.add(salida);
							if (salida.equalsIgnoreCase("TOTAL"))
								start = false;
						}
					}
					if (j < list.size() - 1)
						salida = "";// Celda Vacia
				}// END for columnas
				if (start == true) {
					tempRow++;
					if (tempRow > 4) {
						row++;
						if (campos.size() != 0) {
							generarFila();
							for (int k = 0; k < campos.size(); k++)
								tableXLSXBase.setValueAt(campos.get(k), row, k);
						} else
							row -= 1;
					}
				}
				campos.clear();
			}// END for Filas

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null,
					"ERROR en el metodo: showExcelData2(List sheetData)" + e);

		}

	}

	public void generarColumna(String title, int col) {

		DefaultTableModel dtm = (DefaultTableModel) tableXLSXBase.getModel();
		dtm.addColumn(title);
		TableColumn column = tableXLSXBase.getColumnModel().getColumn(col);
		column.setPreferredWidth(150);
	}

	public void eliminarColumnas() {
		int numCol = tableXLSXBase.getColumnCount();

		for (int i = 0; i < numCol; i++)
			tableXLSXBase.removeColumn(tableXLSXBase.getColumnModel()
					.getColumn(0));
	}

	public void eliminarFilas() {

		DefaultTableModel dtm = (DefaultTableModel) tableXLSXBase.getModel();
		int num = tableXLSXBase.getRowCount();

		for (int i = 0; i < num; i++)
			dtm.removeRow(0);
	}

	private void generarFila() {

		DefaultTableModel model = (DefaultTableModel) tableXLSXBase.getModel();
		model.addRow(new Object[] {});
	}

	public boolean logg() {
		boolean b = false;
		Logged a = new Logged();
		a.setVisible(true);

		if (a.getUser() != "") {
			if (a.getUser().equalsIgnoreCase("admin")
					&& a.getPass().equalsIgnoreCase("admin"))
				b = true;
			if (a.getUser().equalsIgnoreCase("example")
					&& a.getPass().equalsIgnoreCase("example"))
				b = true;
		}
		return b;
	}

	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		/*
		 * try {
		 * //UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel"
		 * );
		 * //UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel"
		 * );
		 * 
		 * 
		 * 
		 * com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme("Modern", "",
		 * "my company");
		 * UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		 * /* for (javax.swing.UIManager.LookAndFeelInfo info :
		 * javax.swing.UIManager.getInstalledLookAndFeels()) { if
		 * ("Nimbus".equals(info.getName())) {
		 * javax.swing.UIManager.setLookAndFeel(info.getClassName()); break; } }
		 * 
		 * } catch (ClassNotFoundException ex) {
		 * java.util.logging.Logger.getLogger
		 * (IACNAUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
		 * ex); } catch (InstantiationException ex) {
		 * java.util.logging.Logger.getLogger
		 * (IACNAUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
		 * ex); } catch (IllegalAccessException ex) {
		 * java.util.logging.Logger.getLogger
		 * (IACNAUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
		 * ex); } catch (javax.swing.UnsupportedLookAndFeelException ex) {
		 * java.util
		 * .logging.Logger.getLogger(IACNAUI.class.getName()).log(java.util
		 * .logging.Level.SEVERE, null, ex); }
		 */
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new IACNAUI().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel PanAltaMatDes;
	private javax.swing.JPanel PanAltaProdDes;
	private javax.swing.JPanel PanCargarFormDes;
	private javax.swing.JPanel PanLeerFormDes;
	private javax.swing.JPanel PanDeletMatDes;
	private javax.swing.JPanel PanDeletProdDes;
	private javax.swing.JPanel PanEditaMatDes;
	private javax.swing.JPanel PanEditaProdDes;
	private javax.swing.JPanel PanEditarFormDes;
	private javax.swing.JPanel PanFrontDes;
	private javax.swing.JPanel PanModiicarFormDes;
	private javax.swing.JPanel PanReportDes;
	private javax.swing.JPanel backUpPanel;
	private javax.swing.JButton buttonAbrirArchivo1;
	private javax.swing.JButton buttonAbrirArchivo2;
	private javax.swing.JButton buttonCancelar;
	private javax.swing.JButton buttonConfirmar;
	private javax.swing.JTextField fieldFormulaPathXLS1;
	private javax.swing.JTextField fieldFormulaPathXLS2;
	private javax.swing.JPanel formulaPanel;
	private javax.swing.JLabel iacnaLogo;
	private javax.swing.JLabel iacnaTitleApplicationLabel;
	private javax.swing.JButton jButton10;
	private javax.swing.JButton jButton11;
	private javax.swing.JButton jButton12;
	private javax.swing.JButton jButton13;
	private javax.swing.JButton jButton15;
	private javax.swing.JButton jButton18;
	private javax.swing.JButton jButton19;
	private javax.swing.JButton jButton20;
	private javax.swing.JButton jButton21;
	private javax.swing.JButton jButton22;
	private javax.swing.JButton jButton23;
	private javax.swing.JButton jButton24;
	private javax.swing.JButton jButton25;
	private javax.swing.JButton jButton26;
	private javax.swing.JButton jButton27;
	private javax.swing.JButton jButton31;
	private javax.swing.JButton jButton32;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JButton jButton8;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox10;
	private javax.swing.JComboBox jComboBox13;
	private javax.swing.JComboBox jComboBox14;
	private javax.swing.JComboBox jComboBox15;
	private javax.swing.JComboBox jComboBox16;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JComboBox jComboBox4;
	private javax.swing.JComboBox jComboBox5;
	private javax.swing.JComboBox jComboBox6;
	private javax.swing.JComboBox jComboBox7;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	private javax.swing.JLabel jLabel26;
	private javax.swing.JLabel jLabel27;
	private javax.swing.JLabel jLabel30;
	private javax.swing.JLabel jLabel31;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenu jMenu3;
	private javax.swing.JMenu jMenu4;
	private javax.swing.JMenu jMenu5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane10;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JScrollPane jScrollPane6;
	private javax.swing.JScrollPane jScrollPane9;
	private javax.swing.JSpinner jSpinner2;
	private javax.swing.JTable jTable1;
	private javax.swing.JTable jTable2;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextArea jTextArea2;
	private javax.swing.JTextArea jTextArea4;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField5;
	private org.jdesktop.swingx.JXTaskPane jXTaskPane10;
	private org.jdesktop.swingx.JXTaskPane jXTaskPane11;
	private org.jdesktop.swingx.JXTaskPane jXTaskPane12;
	private org.jdesktop.swingx.JXTaskPane jXTaskPane4;
	private org.jdesktop.swingx.JXTaskPane jXTaskPane5;
	private org.jdesktop.swingx.JXTaskPane jXTaskPane6;
	private org.jdesktop.swingx.JXTaskPane jXTaskPane7;
	private org.jdesktop.swingx.JXTaskPane jXTaskPane8;
	private org.jdesktop.swingx.JXTaskPane jXTaskPane9;
	private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer2;
	private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer3;
	private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer4;
	private javax.swing.JLabel labelFormula1;
	private javax.swing.JLabel labelFormula2;
	private javax.swing.JPanel materialPanel;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JPanel operationsPanel;
	private javax.swing.JPanel optionsPanel;
	private javax.swing.JTabbedPane optionsTabbedPane;
	private javax.swing.JPanel productoPanel;
	private javax.swing.JProgressBar progressBar;
	private javax.swing.JPanel reportePanel;
	private javax.swing.JSpinner spinnerBatch;
	private javax.swing.JPanel statusBarContainer;
	private javax.swing.JLabel statusBarLabel;
	private javax.swing.JMenuItem subMenuCerrarSesion;
	private javax.swing.JMenuItem subMenuIniciarSesion;
	private javax.swing.JTable tableXLSXBase;
	private javax.swing.JTable tableXLSXBase1;
	private javax.swing.JPanel workArea;
	private javax.swing.JPanel startPanel;
	private javax.swing.JLabel userLogged;
	private javax.swing.JMenuItem menuSalir;
	// End of variables declaration//GEN-END:variables
}
