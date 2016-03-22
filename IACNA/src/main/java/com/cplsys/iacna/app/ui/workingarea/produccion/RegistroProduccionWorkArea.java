package com.cplsys.iacna.app.ui.workingarea.produccion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;

import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXHyperlink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.app.ui.statusbar.IACNAStatusBar;
import com.cplsys.iacna.domain.BatchProduccion;
import com.cplsys.iacna.domain.Privilegio;
import com.cplsys.iacna.domain.RegistroProduccion;
import com.cplsys.iacna.domain.Turno;
import com.cplsys.iacna.services.BatchProduccionService;
import com.cplsys.iacna.services.ISession;
import com.cplsys.iacna.services.RegistroProduccionService;
import com.cplsys.iacna.services.TurnoService;
import com.cplsys.iacna.ui.Logged;
import com.cplsys.iacna.utils.editor.RegistroProduccionCellEditor;
import com.cplsys.iacna.utils.ifaces.IACNAIface;
import com.cplsys.iacna.utils.ifaces.LogginListener;
import com.cplsys.iacna.utils.model.RegistroProduccionModel;
import com.cplsys.iacna.utils.model.RegistroProduccionTableModelListener;

@Repository
public class RegistroProduccionWorkArea extends JPanel implements IACNAIface, LogginListener {

	private static final long serialVersionUID = -2607532134751788357L;

	private static final int COLUMNA_BASCULA = 0;
	private static final int COLUMNA_MP = 1;
	private static final int COLUMNA_DESCRIPCION = 2;
	private static final int COLUMNA_ESPECIFICACION = 3;
	private static final int COLUMNA_MIN = 4;
	private static final int COLUMNA_MAX = 5;

	private JPanel headerContent;
	private JPanel headerDetailsContent;
	private JPanel bodyContent;
	private JPanel optionsPanelButtons;
	
	private JLabel titleLabel;
	private JLabel dateLabel;
	private JLabel turnLabel;
	private JLabel turnLabelContent;
	private JTextField nombreFormulaField;
	
	private JButton excelExport;
	private JXHyperlink performQueryBetweenDates;
	/**
	 * //private JComboBox turnoComboBox;
	//private DefaultComboBoxModel cModel;
	private JRadioButton allRadioButton;
	private JRadioButton rangRadioButton;
	private JRadioButton turnRadioButton;
	private JRadioButton formulaRadioButton;
	private ButtonGroup groupB;
	private JXDatePicker fromDate;
	private JXDatePicker toDate;
	**/
	private JScrollPane scroller;
	private JTable resultsTable;
	private TableRowSorter<TableModel> sorter;
	
	

	private JFileChooser chooser;
	private FileNameExtensionFilter filterXls;
	
	private LineBorder bord;

	private JButton buscarTodo;
	private JButton buscarNombreFormula;
	private JButton buscarTurno;
	private JButton buscarPeriodo;

	private String[] headers = { "Bascula", "MP", "Descripcion",
			"Especificacion", "Tol. Min", "Tol Max" };
	@Autowired
	private RegistroProduccionService registroProduccionService;
	@Autowired
	private BatchProduccionService batchProductionService;
	@Autowired
	private IACNAStatusBar statusBar;	
	@Autowired
	private TurnoService turnoService;
	@Autowired
	private Logged logged;
	@Autowired
	private FiltroGeneralWindow generalFormulaWindow;
	@Autowired
	private FiltroFormulaWindow listaFormulaWindow;
	@Autowired
	private FiltroTurnosWindow listaTurnosWindow;
	@Autowired
	private FiltroPeriodoWindow filtroPeriodoWindow;
	@Autowired
	private ISession session;
	
	JTable miTabla;
	
	@PostConstruct
	public void init() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}

	@Override
	public void initProperties() {
		this.setLayout(new MigLayout("insets 0 0 0 0"));
		
		
		titleLabel.setFont(new Font("arial", Font.BOLD, 14));
		excelExport.setToolTipText(
				"Click para exportar los datos a una hoja de excel");
		excelExport.setText("Exportar");
		excelExport.setEnabled(false);
		performQueryBetweenDates.setIcon(new ImageIcon(getClass().getResource(
				"/media/search.gif")));
		performQueryBetweenDates.setEnabled(false);
		
		resultsTable.setFillsViewportHeight(true);
		resultsTable.setDefaultRenderer(RegistroProduccion.class,
				new RegistroProduccionCellEditor());
		//resultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		resultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		resultsTable.setMaximumSize(new Dimension(2147483647, 64));
		resultsTable.setMinimumSize(new Dimension(60, 64));
		//resultsTable.setPreferredSize(new Dimension(640, 600));
		resultsTable.setPreferredScrollableViewportSize(new Dimension(640, 800));

		chooser.setFileFilter(filterXls);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		nombreFormulaField.setEditable(false);
		buscarTodo.setIcon(new ImageIcon(getClass().getResource("/media/ReportAll.png")));
		buscarNombreFormula.setIcon(new ImageIcon(getClass().getResource("/media/ReportFormula.png")));
		buscarPeriodo.setIcon(new ImageIcon(getClass().getResource("/media/ReportPeriod.png")));
		buscarTurno.setIcon(new ImageIcon(getClass().getResource("/media/ReportTurn.png")));		
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		//----------
		buscarNombreFormula.setVisible(false);
		buscarTurno.setVisible(false);
		buscarPeriodo.setVisible(false);
		//----------
	}

	@Override
	public void initObjects() {
		nombreFormulaField = new JTextField(30);
		
		
		performQueryBetweenDates = new JXHyperlink();
		turnLabelContent = new JLabel("Turn is unkown");
		chooser = new JFileChooser();
		filterXls = new FileNameExtensionFilter("Documentos MS Excel 95/2003",
				"xls");
		titleLabel = new JLabel("REGISTRO DE PRODUCCION");
		turnLabel = new JLabel("Turno: ");
		dateLabel = new JLabel("Fecha: " + new Date());
		resultsTable = new JTable();
		scroller = new JScrollPane(resultsTable);
		excelExport = new JButton(new ImageIcon(getClass().getResource(
				"/media/excel.png")));

		
		buscarTodo = new JButton("Mostrar");
		//buscarTodo = new JButton("General.");
		buscarNombreFormula  = new JButton("Formula.");
		buscarTurno = new JButton("Turno.");;
		buscarPeriodo = new JButton("Periodo.");
		

		headerContent = new JPanel(new MigLayout("insets 5 350 0 0"));
		headerDetailsContent = new JPanel(new MigLayout("insets 20 20 0 0"));
		optionsPanelButtons = new JPanel(new MigLayout("insets 20 20 0 0"));
		bodyContent = new JPanel(new MigLayout("insets 0 0 0 0"));
		
		miTabla = new JTable();
	
	}

	@Override
	public void initListeners() {
		
		buscarTodo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				resetTableModel();
				filtroPeriodoWindow.setVisible(true);				
				if(filtroPeriodoWindow.getRegistroProduccions()!=null) {
					if(!filtroPeriodoWindow.getRegistroProduccions().isEmpty()) {
						resetTableModel();
						List<RegistroProduccion> registros = 
								filtroPeriodoWindow.getRegistroProduccions();
							if (registros != null && !registros.isEmpty()) {
								excelExport.setEnabled(true);
								resultsTable.setModel(new RegistroProduccionModel(registros));
								statusBar.publishMessageOnStatusBar("Registros encontrados");
								dateLabel.setText(registros.get(0).getFechaRegistroDia());
								turnLabelContent.setText(registros.get(0).getTurno());
							} else {
								statusBar.publishMessageOnStatusBar(
										"No se encontraron registros");
								JOptionPane.showMessageDialog(null,
										"No se encontrarron registros");
								resetTableModel();
							}
					}
				}
				else statusBar.publishMessageOnStatusBar(
						"No se encontraron registros");
				updateRowsDimension();
			
				
				/**
				generalFormulaWindow.setVisible(true);
				resetTableModel();
				List<RegistroProduccion> registros = generalFormulaWindow.getList();
				if(registros==null) {
					JOptionPane.showMessageDialog(null, "No se encotraron registros", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					resultsTable.setModel(new RegistroProduccionModel(registros));
					excelExport.setEnabled(true);
					statusBar.publishMessageOnStatusBar("Registros del mes"+"  ["+resultsTable.getModel().getRowCount()+" Registros]");
				}
				updateRowsDimension();
				**/
			}
		});
		
		buscarNombreFormula.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				listaFormulaWindow.setVisible(true);
				if(listaFormulaWindow.getRegistroProduccions()!=null) {
					if(!listaFormulaWindow.getRegistroProduccions().isEmpty()) {
						resetTableModel();
						List<RegistroProduccion> registros = 
								listaFormulaWindow.getRegistroProduccions();
							if (registros != null && !registros.isEmpty()) {
								excelExport.setEnabled(true);
								resultsTable.setModel(new RegistroProduccionModel(registros));
								statusBar.publishMessageOnStatusBar("Registros encontrados");
							} else {
								statusBar.publishMessageOnStatusBar(
										"No se encontraron registros");
								JOptionPane.showMessageDialog(null,
										"No se encontrarron registros");
								resetTableModel();
							}
					}
				}
				else statusBar.publishMessageOnStatusBar(
						"No se encontraron registros");				
				updateRowsDimension();
			}
		});
				
		buscarPeriodo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetTableModel();
				filtroPeriodoWindow.setVisible(true);				
				if(filtroPeriodoWindow.getRegistroProduccions()!=null) {
					if(!filtroPeriodoWindow.getRegistroProduccions().isEmpty()) {
						resetTableModel();
						List<RegistroProduccion> registros = 
								filtroPeriodoWindow.getRegistroProduccions();
							if (registros != null && !registros.isEmpty()) {
								excelExport.setEnabled(true);
								resultsTable.setModel(new RegistroProduccionModel(registros));
								statusBar.publishMessageOnStatusBar("Registros encontrados");
							} else {
								statusBar.publishMessageOnStatusBar(
										"No se encontraron registros");
								JOptionPane.showMessageDialog(null,
										"No se encontrarron registros");
								resetTableModel();
							}
					}
				}
				else {
					statusBar.publishMessageOnStatusBar(
							"No se encontraron registros");
					excelExport.setEnabled(false);
				}
				
				updateRowsDimension();
			}
		});
		
		buscarTurno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listaTurnosWindow.setVisible(true);
				if(listaTurnosWindow.getRegistroProduccions()!=null){
					if(!listaTurnosWindow.getRegistroProduccions().isEmpty()){
						resetTableModel();
						List<RegistroProduccion> registros = listaTurnosWindow.getRegistroProduccions();
						
						if (registros != null && !registros.isEmpty()) {
							resultsTable.setModel(new RegistroProduccionModel(registros));
							statusBar.publishMessageOnStatusBar("Registros para el : "+registros.get(0).getTurno());
							performQueryBetweenDates.setEnabled(false);
						} else {
							statusBar.publishMessageOnStatusBar("No se encontrarron registros");
							resetTableModel();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "No se encontraron registros");
					resetTableModel();
				}
				updateRowsDimension();
			}
		});

		excelExport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				FileOutputStream fileOut = null;
				File fileXLS = null;
				switch (chooser.showSaveDialog(null)) {
				case 0:
					try {
						fileXLS = chooser.getSelectedFile();
						String name = fileXLS.getName();
						if (name.indexOf('.') == -1) {
							name += ".xlsx";
							fileXLS = new File(fileXLS.getParentFile(), name);
						}
						fileOut = new FileOutputStream(fileXLS);
						RegistroProduccionModel model = (RegistroProduccionModel) resultsTable
								.getModel();
						String[] headers = new String[model.getColumnCount()];
						for (int i = 0; i < headers.length; i++) {
							headers[i] = model.getColumnName(i);
						}

						XSSFWorkbook wb = new XSSFWorkbook();
						XSSFSheet sheet = wb.createSheet();
						CellStyle headerStyle = wb.createCellStyle();
						headerStyle
								.setFillBackgroundColor(IndexedColors.GREY_40_PERCENT
										.getIndex());
						headerStyle.setAlignment(CellStyle.ALIGN_CENTER);

						Date fecha = new Date();
						SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); 
						
						XSSFRow titleRow = sheet.createRow((short) 0); // Crear Fila
						XSSFCell hcell = titleRow.createCell((short) 0); //Celda
						hcell.setCellValue("Reporte de Produccion, elaborado: "+ sdf.format(fecha)); // Asignar valor

						//Unir celdas de primera fila
						sheet.addMergedRegion(new CellRangeAddress(0,0,0,headers.length - 1));
						hcell.setCellStyle(headerStyle);

						XSSFRow row = sheet.createRow((short) 1);//Crear Nueva Fila
						
						int columAcc =0;//Nueva variable ultimo conteo de columna
						
						for (int i = 0; i < headers.length; i++) {
							XSSFCell cell = row.createCell(i);
							cell.setCellValue(headers[i]);
							cell.setCellStyle(headerStyle);
							sheet.autoSizeColumn(i);
							columAcc = i;
						}
						
						//--------------//Add columna de fecha de produccion
						XSSFCell cell1 = row.createCell(columAcc+1);
						cell1.setCellValue("Fecha Produccion");
						cell1.setCellStyle(headerStyle);
						sheet.autoSizeColumn(columAcc+1);
						columAcc = 0;//nueva variable para contar ultima columna add
						//--------------

						row = sheet.createRow((short) 2);

						int rowContinue = 3;
						List<RegistroProduccion> registroProduccion = model.getAllData();
						
						
						
						for (int i = 0; i < model.getRowCount(); i++) {
							row = sheet.createRow((short) rowContinue);
							String valorCelda = "";
							
							for (int j = 0; j < model.getColumnCount(); j++) {
								switch (j) {
								case 0:
									valorCelda = registroProduccion.get(i)
											.getBascula();
									break;
								case 1:
									valorCelda = registroProduccion.get(i)
											.getMp();
									break;
								case 2:
									valorCelda = registroProduccion.get(i)
											.getDescripcion();
									break;
								case 3:
									valorCelda = String.valueOf(registroProduccion.get(i)
											.getEspecificacion());
									break;
								case 4:
									valorCelda = String.valueOf(registroProduccion.get(i)
											.getToleranciaMinima());
									break;
								case 5:
									valorCelda = String.valueOf(registroProduccion.get(i)
											.getToleranciaMaxima());
									break;
								default:
									int idx = j - 6;
									Object[] bProductionArray = registroProduccion
											.get(i).getBatchProduccion()
											.toArray();
									if (idx < bProductionArray.length) {
										BatchProduccion batchProduccion = (BatchProduccion) bProductionArray[idx];

										Double batch = batchProduccion
												.getPesoObtenido();
										
										DecimalFormat formatoDecimal = new DecimalFormat("0.00"); 
										valorCelda = formatoDecimal.format(batch).toString();

										if (valorCelda.isEmpty())
											valorCelda = "0";
									}
									break;
								}

								XSSFCell cell = row.createCell(j);
								cell.setCellValue(valorCelda);
								XSSFFont font = wb.createFont();
								font.setBoldweight(font.BOLDWEIGHT_BOLD);
								headerStyle.setFont(font);
								cell.setCellStyle(headerStyle);
								sheet.autoSizeColumn(j);
								valorCelda = "";
								
								columAcc = j;
							}
							//------------------------
							XSSFCell cell = row.createCell(columAcc+1);
							
								cell.setCellValue(registroProduccion.get(i)
									.getFechaRegistroDia());
							//------------------------
							rowContinue++;
						}

						wb.write(fileOut);

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						try {
							// Cerramos nuestro archivo
							fileOut.close();
							// abrimos el archivo generado con el programa
							// correspondiente
							if (System.getProperty("os.name").equals("Linux")) {
								Runtime.getRuntime().exec(
										"libreoffice "
												+ fileXLS.getAbsolutePath());
							} else {
								Runtime.getRuntime().exec(
										"rundll32 url.dll,FileProtocolHandler "
												+ fileXLS.getAbsolutePath());
							}

						} catch (IOException ex) {

						}
					}
					
					JOptionPane.showMessageDialog(null, 
							"El reporte de produccion se ha generado exitosamente",
							"Reporte Exportado", JOptionPane.INFORMATION_MESSAGE);
					
					statusBar.publishMessageOnStatusBar(
							"El reporte de produccion se ha generado exitosamente");
					break;
				case 1:
					break;
				}
				updateRowsDimension();
			}
		});
	}

	@Override
	public void createUI() {
	
		
		headerContent.add(titleLabel);
		headerDetailsContent.add(dateLabel,"wrap");
		headerDetailsContent.add(turnLabel);		
		
		optionsPanelButtons.add(buscarTodo);
		optionsPanelButtons.add(excelExport,"span, wrap");
		optionsPanelButtons.add(scroller,"span");
		
		bodyContent.add(headerContent,"wrap");
		bodyContent.add(headerDetailsContent,"wrap");
		bodyContent.add(buildSeparator(), "span, wrap");
		bodyContent.add(optionsPanelButtons, "wrap");
		this.add(bodyContent);
		
		
	}

	private JSeparator buildSeparator() {
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setForeground(Color.BLACK);
		return separator;
	}

	@Override
	public void sessionActivated() {
	    resetTableModel();
	    Set<Privilegio> privilegio =  session.getUsuario().getPrivilegios();
	    
	    for (Privilegio privilegio2 : privilegio) {
				
			if(privilegio2.isSuperUser()) {
				excelExport.setVisible(true);
				break;
			}
			if(privilegio2.isProductionSupervisor()) {
				excelExport.setVisible(false);
				break;
			}
			if(privilegio2.isProductionCalidad()) {
				excelExport.setVisible(true);
				break;
			}
			if(privilegio2.isProductionHeads()) {
				excelExport.setVisible(true);
				break;
			}
		}
	}
	
	@Override
	public void sessionDeactivated() {
		resetTableModel();
	}
	
	public Calendar convertDate(Date dateIn){
		Calendar temp= Calendar.getInstance();
		temp.setTime(dateIn);
		return temp;
	}
	
	private void generarFila() {
		DefaultTableModel model = (DefaultTableModel) miTabla.getModel();
		model.addRow(new Object[] {});
	}
	
	public void generarColumna(String title, int col) {

		DefaultTableModel dtm = (DefaultTableModel) miTabla.getModel();
		dtm.addColumn(title);
		TableColumn column = miTabla.getColumnModel().getColumn(col);
		column.setPreferredWidth(150);
	}
		
	private void resetTableModel() {
		resultsTable.setModel(new RegistroProduccionModel(null));
		updateRowsDimension();
	}
	
	private void updateRowsDimension() {
		setUpColumnSize(resultsTable, COLUMNA_BASCULA, 70);
		setUpColumnSize(resultsTable, COLUMNA_MP, 100);
		setUpColumnSize(resultsTable, COLUMNA_DESCRIPCION, 390);
		setUpColumnSize(resultsTable, COLUMNA_ESPECIFICACION, 150);
		setUpColumnSize(resultsTable, COLUMNA_MIN, 70);
		setUpColumnSize(resultsTable, COLUMNA_MAX, 70);		
	}
	
	private void setUpColumnSize(JTable table, int column, int widht) {
		RegistroProduccionModel dtm = (RegistroProduccionModel) table.getModel();		
		TableColumn tableColumn = table.getColumnModel().getColumn(column);
		tableColumn.setPreferredWidth(widht);
	}

}