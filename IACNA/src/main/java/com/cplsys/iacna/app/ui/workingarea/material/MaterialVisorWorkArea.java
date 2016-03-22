package com.cplsys.iacna.app.ui.workingarea.material;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import net.miginfocom.swing.MigLayout;

import org.apache.poi.hssf.OldExcelFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.values.XmlValueDisconnectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.app.ui.statusbar.IACNAStatusBar;
import com.cplsys.iacna.app.ui.workingarea.formula.FormulaWorkArea;
import com.cplsys.iacna.app.ui.workingarea.formula.ProccessStack;
import com.cplsys.iacna.domain.Formula;
import com.cplsys.iacna.domain.IACNADispatcher;
import com.cplsys.iacna.domain.Ingrediente;
import com.cplsys.iacna.domain.Privilegio;
import com.cplsys.iacna.domain.Turno;
import com.cplsys.iacna.services.BasculaService;
import com.cplsys.iacna.services.FormulaService;
import com.cplsys.iacna.services.IACNADispatcherService;
import com.cplsys.iacna.services.ISession;
import com.cplsys.iacna.services.IngredienteService;
import com.cplsys.iacna.services.ProductoService;
import com.cplsys.iacna.services.TurnoService;
import com.cplsys.iacna.ui.Logged;
import com.cplsys.iacna.ui.SheetWindow;
import com.cplsys.iacna.ui.SheetWindowExcel;
import com.cplsys.iacna.utils.CellExcel;
import com.cplsys.iacna.utils.IACNAConstants;
import com.cplsys.iacna.utils.UpdateCellExcel;
import com.cplsys.iacna.utils.ifaces.IACNAIface;
import com.cplsys.iacna.utils.ifaces.LogginListener;

@Repository
public class MaterialVisorWorkArea extends JPanel implements IACNAIface,
		LogginListener {

	private static final long serialVersionUID = -7592306703221764280L;

	private JPanel headerContent;
	private JLabel materialLabel;
	private JTextField materialNameLabel;
	private JLabel productoLabel;
	private JTextField productoNameLabel;
	private JLabel productoDescripcionLabel;
	private JTextField productoDescripcionNameLabel;
	private JLabel formulaLabel;
	private JTextField formulaNameLabel;
	private JLabel locationLegend;
	private JLabel turnoLegend;
	private JLabel batchLegend;
	private JTextField pathLocationField;
	private JSpinner spinnerBatch;
	private JButton saveToProducction;
	private JButton cancel;
	private JComboBox turnoComboBox;
	private JLabel title;
	private JTable excelViewer;
	private JScrollPane scroller;
	private JPanel titlePanel;
	private JPanel container;
	private JPanel footerContent;
	
	private String primerValor;
	private List sheetData, data, list;
	private File file;
	private FileInputStream stream;
	private FileOutputStream fileOut;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private Iterator rows;
	private XSSFRow row;
	private XSSFCell cell;
	private Vector campos;
	private CellExcel celResultsTable;
	private int indexsheet;

	@Autowired
	private IACNAStatusBar statusBar;
	@Autowired
	private ProductoService productoServicio;
	@Autowired
	private FormulaService formulaService;
	@Autowired
	private BasculaService basculaService;
	@Autowired
	private TurnoService turnoService;
	@Autowired
	private IACNADispatcherService iacnaDispatcherService;
	private DefaultComboBoxModel cModel;
	@Autowired
	private IngredienteService ingredienteService;
	@Autowired
	private ProccessStack proccessStack;
	@Autowired
	private Logged logged;
	@Autowired
	private UpdateCellExcel updateCellExcel;
	@Autowired
	private FormulaWorkArea formulaWorkArea;
	@Autowired
	private SheetWindowExcel sheetWindow;
	/**'private SheetWindow sheetWindow;**/
	
	

	@PostConstruct
	public void init() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}

	@Override
	public void initProperties() {

		title.setFont(new Font("arial", Font.BOLD, 14));
		saveToProducction.setEnabled(false);
		cancel.setEnabled(false);
		pathLocationField.setEditable(false);
		pathLocationField.setFont(new Font("Arial", Font.PLAIN, 8));
		spinnerBatch.setEnabled(false);
		spinnerBatch.setModel(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));
		turnoComboBox.setModel(cModel);
		turnoComboBox.setEnabled(false);
		saveToProducction.setIcon(new ImageIcon(getClass().getResource(
				"/media/acceptIcon.png")));
		cancel.setIcon(new ImageIcon(getClass().getResource(
				"/media/cancelIcon.png")));
		materialNameLabel.setEditable(false);
		productoNameLabel.setEditable(false);
		productoDescripcionNameLabel.setEditable(false);
		formulaNameLabel.setEditable(false);
		
		materialNameLabel.setFont(new Font("Arial", Font.PLAIN, 9));
		productoDescripcionNameLabel.setFont(new Font("Arial", Font.PLAIN, 9));		
		formulaNameLabel.setFont(new Font("Arial", Font.PLAIN, 9));
		
		excelViewer.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//excelViewer.setMaximumSize(new Dimension(2147483647, 64));
		//excelViewer.setMinimumSize(new Dimension(60, 64));
		//excelViewer.setPreferredSize(new Dimension(300, 64));
		excelViewer.setPreferredScrollableViewportSize(new Dimension(640, 800));
		excelViewer.setFont(new Font("Arial", Font.PLAIN, 9));
	}

	@Override
	public void initObjects() {

		excelViewer = new JTable();
		scroller = new JScrollPane(excelViewer);
		
		/*scroller = new JScrollPane();
		scroller.setViewportView(excelViewer);
		*/
		title = new JLabel("INFORMACION DEL MATERIAL");
		materialLabel = new JLabel("Material: ");
		materialNameLabel = new JTextField();
		productoLabel = new JLabel("Producto: ");
		productoNameLabel = new JTextField();
		productoDescripcionLabel = new JLabel("Descripcion: ");
		productoDescripcionNameLabel = new JTextField();
		formulaLabel = new JLabel("Nombre: ");
		formulaNameLabel = new JTextField();
		
		spinnerBatch = new JSpinner();
		saveToProducction = new JButton("Confirmar");
		cancel = new JButton("Cancelar");
		turnoComboBox = new JComboBox();
		locationLegend = new JLabel("Ruta: ");
		turnoLegend = new JLabel("Turno: ");
		batchLegend = new JLabel("Batch: ");

		pathLocationField = new JTextField();
		
		titlePanel = new JPanel(new MigLayout("insets 15 320 0 0"));
		headerContent = new JPanel(new MigLayout("insets 20 0 0 0"));

		container = new JPanel(new MigLayout("insets 10 0 0 0"));
		footerContent = new JPanel(new MigLayout("insets 15 0 0 0"));

		cModel = new DefaultComboBoxModel();

		List<Turno> turnos = turnoService.getAll();
		for (Turno turno : turnos) {
			cModel.addElement(turno.getNombre());
		}
		turnoComboBox.setModel(cModel);
		celResultsTable = new CellExcel();
		
		sheetData = new ArrayList();
		celResultsTable=new CellExcel();
	}

	@Override
	public void initListeners() {

		saveToProducction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isTurnoValido()) {
					String isExcede = excedeNumeroDeRegistrosEnPLC(new Integer(spinnerBatch
							.getValue().toString())); 
					//if (isExcede.equals("")) {
						int opc = JOptionPane.showConfirmDialog(null,
								"Esta seguro de transferir\n"
										+ "esta formula a produccion", "",
								JOptionPane.YES_NO_OPTION);
						if (opc == JOptionPane.OK_OPTION) {
							IACNADispatcher iacnaDispatcher = new IACNADispatcher();
							Formula formula = new Formula();
							formula.setDescripcion(productoDescripcionNameLabel
									.getText());
							formula.setNombre(formulaNameLabel.getText());
							formula.setTotalBatchAProcesar(new Integer(spinnerBatch
									.getValue().toString()));
							formula.setTurno(turnoComboBox.getSelectedItem()
									.toString());
							formulaService.saveFormula(formula);
							for (int i = 0; i < excelViewer.getRowCount(); i++) {
								Ingrediente ingrediente = new Ingrediente();
								ingrediente.setFormula(formula);
								ingrediente.setFormulaIdForWS(String
										.valueOf(formula.getIdFormula()));
								for (int j = 0; j < excelViewer.getColumnCount(); j++) {

									switch (j) {
									case 0:
										ingrediente.setBascula(excelViewer
												.getValueAt(i, j).toString());

										if (excelViewer.getValueAt(i, j).toString()
												.equals("6")) {
											ingrediente.setPrepesadoBascula(true);
										} else {
											ingrediente.setPrepesadoBascula(false);
										}
										break;

									case 1:
										ingrediente.setMp(excelViewer.getValueAt(i,
												j).toString());
										break;
									case 2:
										ingrediente.setDescripcion(excelViewer
												.getValueAt(i, j).toString());
										ingrediente.setNombre(excelViewer
												.getValueAt(i, j).toString());
										break;
									case 3:
										ingrediente.setEspecificacion(Double
												.parseDouble(excelViewer.getValueAt(i,
														j).toString()));
										break;
									case 4:
										
										/*
										double valorMinimo =
												Double
												.parseDouble(excelViewer.getValueAt(i,
												3).toString()) - 
												Double
												.parseDouble(excelViewer.getValueAt(i,
														j).toString());
										
										ingrediente.setToleranciaMinima(valorMinimo);
										
										*/
										ingrediente.setToleranciaMinima(Double
												.parseDouble(excelViewer.getValueAt(i,
														j).toString()));
										break;
									case 5:
										/*
										double valorMaximo =
										Double
										.parseDouble(excelViewer.getValueAt(i,
										3).toString()) + 
										Double
										.parseDouble(excelViewer.getValueAt(i,
												j).toString());
										
										ingrediente.setToleranciaMaxima(valorMaximo);
										*/
										ingrediente.setToleranciaMaxima(Double
												.parseDouble(excelViewer.getValueAt(i,
														j).toString()));
										break;
									}
								}
								ingredienteService.saveIngrediente(ingrediente);
							}
							iacnaDispatcher.setFormula(formula);
							iacnaDispatcherService
									.saveIACNADispatcher(iacnaDispatcher);
							proccessStack.pushToProccessStack(iacnaDispatcher);
							statusBar.updateEmergencyStopManager();
							excelViewer.setModel(new DefaultTableModel());
							spinnerBatch.setValue(new Integer(1));
							spinnerBatch.setEnabled(false);
							saveToProducction.setEnabled(false);
							cancel.setEnabled(false);
							pathLocationField.setText("");
							turnoComboBox.setEnabled(false);
							materialNameLabel.setText("");
							productoNameLabel.setText("");
							productoDescripcionNameLabel.setText("");
							formulaNameLabel.setText("");
							setFile(null);
							statusBar
									.publishMessageOnStatusBar("Datos enviados a produccion");
						}	
					/*
					} else {
						JOptionPane.showMessageDialog(null,
								"Error, el sistema no puede procesar esta solicitud\n" +
								"La memoria del plc esta limitada a 20 Ingredientes, la\n" +
								"solicitud actual consta de:\n\n" + isExcede +
								"\n\nPor favor intente nuevamente con un numero menor\n" +
								"de ingredientes o de batchs.", "",
								JOptionPane.INFORMATION_MESSAGE);
					}
					
					*/
					
				} else {
					JOptionPane.showMessageDialog(null,
									"EL TURNO SELECCIONADO ES INCORRECTO\n" +
									"POR FAVOR SELECCIONE UN TURNO VALIDO",
									"", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cleanComponents();
			}
		});

		excelViewer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (excelViewer.getModel().getColumnCount() > 0) {

					try {
						primerValor = (excelViewer.getValueAt(
								excelViewer.getSelectedRow(),
								excelViewer.getSelectedColumn())).toString();
					} catch (Exception e1) {
						System.err
								.println("error al obtener primerValor 'FormulaWorkArea 318'");
					}
				}

			}
		});

		excelViewer.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				//HACER CAMBIO DE VALOR EN CELDA
				ejecutarActualizacion();				
			}
		});

	}

	private void ejecutarActualizacion() {
		if(excelViewer.getModel().getRowCount() > 0) {					
			if(excelViewer.getSelectedRow() >= 0 || 
					excelViewer.getSelectedColumn() >= 0) {
				
				String changeValue=excelViewer.getValueAt(excelViewer.getSelectedRow(), excelViewer.getSelectedColumn()).toString();
				
				if(primerValor!=null){
					if(!primerValor.equalsIgnoreCase(changeValue)){
						
						
						//ACTUALIZAR celda de excel
						try {
							
							updateCellExcel(celResultsTable.getRowExcel(excelViewer.getSelectedRow()), changeValue);
						} catch (XmlValueDisconnectedException e1) {
							e1.printStackTrace();
						}
						
						System.err.println("Actualizado");
					}
				}
			}
		}
	}
	@Override
	public void createUI() {
		titlePanel.add(title);
		headerContent.add(locationLegend);
		headerContent.add(pathLocationField, "width 608:608:608, span, grow");
		JPanel descripcionFormulaPanel = new JPanel(new MigLayout(
				"insets 0 0 0 0"));
		descripcionFormulaPanel.add(materialLabel);
		descripcionFormulaPanel.add(materialNameLabel, "width 50:50:50");
		descripcionFormulaPanel.add(productoLabel, "gap unrelated");
		descripcionFormulaPanel.add(productoNameLabel, "width 80:80:80");
		descripcionFormulaPanel.add(productoDescripcionLabel, "gap unrelated");
		descripcionFormulaPanel.add(productoDescripcionNameLabel,
				"width 130:130:130");
		descripcionFormulaPanel.add(formulaLabel, "gap unrelated");
		descripcionFormulaPanel.add(formulaNameLabel, "width 100:100:100");
		headerContent.add(descripcionFormulaPanel, "span, grow");
		headerContent.add(buildSeparator(), "width 640:640:640, span");
		container.add(scroller, "height 400:400:400, width 855:855:855, span");
		container.add(scroller, "span");
		container.add(buildSeparator(), "width 640:640:640, span");
		container.add(batchLegend);
		container.add(spinnerBatch);
		container.add(turnoLegend);
		container.add(turnoComboBox, "width 100:100:100, wrap");
		container.add(saveToProducction);
		container.add(cancel);

		this.setLayout(new MigLayout("insets 0 50 0 0"));

		this.add(titlePanel, "wrap");
		this.add(headerContent, "wrap");
		this.add(container, "wrap");
		this.add(footerContent);
	}

	private JSeparator buildSeparator() {
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setForeground(Color.BLACK);
		return separator;
	}

	public void showExcelData(List sheetData, boolean prepesado) {

		int col = 0, row = -1, tempRow = 0;
		String salida = "";
		list = null;
		cell=null;
		boolean start = false;
		boolean title= false;
		boolean activarExtraccionPrePesado=false;
		celResultsTable=new CellExcel();
		
		try {
			for (int i = 0; i < sheetData.size(); i++) {
				campos = new Vector();
				list = (List) sheetData.get(i);
				for (int j = 0; j < list.size(); j++) {
					cell = (XSSFCell) list.get(j);
					
					
					salida = getDataCell(cell);
					
					if(salida.equalsIgnoreCase("Material")){
						try{
							materialNameLabel.setText(getDataCell((XSSFCell) list.get(j+1)));
							productoNameLabel.setText(getDataCell((XSSFCell) list.get(j+3)));
							productoDescripcionNameLabel.setText(getDataCell((XSSFCell) list.get(j+5)));
							formulaNameLabel.setText(getDataCell((XSSFCell) list.get(j+7)));
						}
						catch(Exception e3){
							JOptionPane.showMessageDialog(null, "Error en el formato");
						}
					}
					
					// Identificar informacion para la tabla
					if (!salida.isEmpty() && salida.length() > 9) {
						if (salida.substring(0, 8).equalsIgnoreCase("Formulac"))
							start = true;
					}

					if (tempRow == 2) {// Generar Columnas
						if (j < 7)
							if (!salida.isEmpty()){
								generarColumna(salida,(cell.getColumnIndex() + col));		
							}								
							else
								col -= 1;
					}

					if (tempRow > 3) {// pre carga de informacion
						if (j < 7) {
							if (!salida.equals("")) {								
								if(prepesado==false) {
									//------------
									if(j > 3 && j <= 6 && start == true)  {
										try {
											salida = String.valueOf(Double.parseDouble(cell.toString()));
										}
										catch (Exception e) {}
									}
									//------------
									campos.add(salida);
									
									if(cell.getColumnIndex()==0) {
										//celResultsTable.setValueAtCelda(cell.getRowIndex()+"-"+cell.getColumnIndex());
										celResultsTable.setValueAtCelda(cell.getRowIndex());
									}
								}
								else{
									if(cell.getColumnIndex()==0){
										if(salida.equalsIgnoreCase("5")){
											activarExtraccionPrePesado=true;
											celResultsTable.setValueAtCelda(cell.getRowIndex());
										}
									}
									if(activarExtraccionPrePesado==true){
										campos.add(salida);
									}
								}
							}
							if (salida.equalsIgnoreCase("TOTAL"))
								start = false;
						}
					}
					if (j < list.size() - 1)
						salida = "";// Celda Vacia
				}// END for columnas
				
				activarExtraccionPrePesado=false;
				
				if (start == true) {
					tempRow++;
					if (tempRow > 4) {
						row++;
						if (campos.size() > 4) {
							generarFila();
							for (int k = 0; k < campos.size(); k++){
								excelViewer.setValueAt(campos.get(k), row, k);
								if(k!=0){
									excelViewer.isCellEditable(row, k);
								}
							}
						} else
							row -= 1;
					}
				}
				campos.clear();
			}// END for Filas
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null,"ERROR en el metodo: showExcelData(List sheetData)" + e);
			e.printStackTrace();
		}
	}
		
	public void updateCellExcel(int modificarCell, String changeValue) throws XmlValueDisconnectedException {

		int tempRow = 0;
		String salida = "";		
		cell=null;
		boolean start = false;
		boolean found=false;
		try {
			sheetData=getDataXlsFile(file,1);
			for (int i = 0; i < sheetData.size(); i++) {
				list = (List) sheetData.get(i);
				for (int j = 0; j < list.size(); j++) {
					cell = (XSSFCell) list.get(j);
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						salida = cell.getRichStringCellValue().getString();
						break;
						
					case Cell.CELL_TYPE_NUMERIC:
						try {
							if (DateUtil.isCellDateFormatted(cell))
								salida = cell.getDateCellValue().toString();
							else
								salida = String.valueOf(((int) cell.getNumericCellValue()));
							
							break;
						} catch(XmlValueDisconnectedException e){
							salida = String.valueOf(((int) cell.getNumericCellValue()));
							break;
						}						
						
					case Cell.CELL_TYPE_BOOLEAN:
						salida = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						salida = String.valueOf(cell.getCellFormula());
						break;
					}

					if (!salida.isEmpty() && salida.length() > 9)
						if (salida.substring(0, 8).equalsIgnoreCase("Formulac"))
							start = true;
					
					if (tempRow > 3) {
						if (j < 8) {
							if (!salida.equals("")){
								
								if(cell.getRowIndex()==(modificarCell+1)){
									
									if(cell.getColumnIndex()==0){
										XSSFRow titleRow = sheet.getRow(modificarCell);
										XSSFCell hcell = titleRow.getCell(0);
										hcell.setCellValue(changeValue);
										
										try{
											fileOut = new FileOutputStream(file);
											workbook.write(fileOut);
											found=true;
											break;
										} catch(FileNotFoundException e) {
											JOptionPane.showMessageDialog(null, "El proceso no puede accesar al archivo\nReintente otra vez","Error de escritura",JOptionPane.WARNING_MESSAGE);
											excelViewer.setValueAt(primerValor, excelViewer.getSelectedRow(), excelViewer.getSelectedColumn());
											break;
										}
									}
								}
							}
							
							if (salida.equalsIgnoreCase("TOTAL"))
								start = false;
						}
					}
					if (j < list.size() - 1)
						salida = "";
				}
				if (start == true) {
					tempRow++;
				}
				if(found==true)
					break;				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void generarColumna(String title, int col) {
		DefaultTableModel dtm = (DefaultTableModel) excelViewer.getModel();
		dtm.addColumn(title);
		TableColumn column = excelViewer.getColumnModel().getColumn(col);
		column.setPreferredWidth(150);
	}

	private void generarFila() {
		DefaultTableModel model = (DefaultTableModel) excelViewer.getModel();
		model.addRow(new Object[] {});
	}

	private String getDataCell(XSSFCell cel) {
		String salida = "";

		switch (cel.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			salida = cel.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cel)) {
				salida = cel.getDateCellValue().toString();
			} else {
				salida = String.valueOf(((int) cel.getNumericCellValue()));
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			salida = String.valueOf(cel.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			salida = String.valueOf(cel.getCellFormula());
			break;
		}
		return salida;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getDataXlsFile(File archivo, int tipo)
			throws InvalidFormatException {
		
		List sheetDataTemp = new ArrayList();		
		statusBar.publishMessageOnStatusBar("Leyendo archivo xls "+archivo.getName());
		
		try {
			stream =new FileInputStream(archivo.toString());
			
			try {
				workbook = new XSSFWorkbook(stream);
				//SheetWindow sheetWindow = new SheetWindow(workbook.getNumberOfSheets(), workbook);
				sheetWindow.loadSheets(workbook.getNumberOfSheets(), workbook);
				
				switch(tipo){
					case 0:					
						statusBar.publishMessageOnStatusBar("Hojas contenidas en el archivo "+archivo.getName());						
						sheetWindow.setVisible(true);
						if(sheetWindow.isCancel()) {
							indexsheet = 0;
							sheetWindow.setIndexSheet(0);
						}
						else
							indexsheet=sheetWindow.getIndexSheet();
						
						
						break;
					case 1:
						break;
				}
				
				if(indexsheet!=0) {
					sheet = workbook.getSheetAt(indexsheet);	
					
					rows = sheet.rowIterator();	
					sheetDataTemp = new ArrayList();
					while (rows.hasNext()) {
						row = ((XSSFRow) rows.next());
						Iterator cells = row.cellIterator();
						data = new ArrayList();

						while (cells.hasNext()) {
							cell = (XSSFCell) cells.next();
							data.add(cell);
						}
						sheetDataTemp.add(data);
					}
					return sheetDataTemp;
				}
				else {
					if(!sheetWindow.isCancel()) {
						
						JOptionPane.showMessageDialog(null, 
								"Posible estructura incorrecta de lectura en el archivo","" +
								"Error al cargar el archivo", JOptionPane.WARNING_MESSAGE);
					}
					
					return null;
				}
				
			}
			catch(OldExcelFormatException e){
				JOptionPane.showMessageDialog(null,
						"Incompatibilidad del archivo",
						"Error al cargar el archivo", 
						JOptionPane.WARNING_MESSAGE);
				return null;
			}
			
		} catch (Exception e1) {
			System.err.println(e1);
			JOptionPane.showMessageDialog(null, "Error en el archivo\n"+e1,"Error al cargar el archivo", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	public void cleanComponents() {

		excelViewer.setModel(new DefaultTableModel());
		spinnerBatch.setValue(new Integer(1));
		spinnerBatch.setEnabled(false);
		saveToProducction.setEnabled(false);
		cancel.setEnabled(false);
		pathLocationField.setText("");
		materialNameLabel.setText("");
		productoNameLabel.setText("");
		productoDescripcionNameLabel.setText("");
		formulaNameLabel.setText("");
		turnoComboBox.setEnabled(false);
		setFile(null);
	}

	@Override
	public void sessionActivated() {

		if (cModel.getSize() == 0) {
			List<Turno> turnos = turnoService.getAll();
			Calendar turnoHoras;
			DateFormat dateFormat;
			String horaInicial;
			String horaFinal;
			int hora;
			int minuto;
			Date date = null;
			for (Turno turno : turnos) {
				Calendar calendar = Calendar.getInstance();
				hora = calendar.get(Calendar.HOUR_OF_DAY);
				minuto = calendar.get(Calendar.MINUTE);
				horaInicial = turno.getHorario().substring(0, 7);
				horaFinal = turno.getHorario().substring(11, 18);
				dateFormat = new SimpleDateFormat(IACNAConstants.FORMATO_TIEMPO);
				try {
					date = dateFormat.parse(horaInicial);
					dateFormat.format(date);
				} catch (Exception e) {

				}

				cModel.addElement(turno.getNombre());
			}
			turnoComboBox.setModel(cModel);
		}
		
	}

	@Override
	public void sessionDeactivated() {

	}

	private boolean isTurnoValido() {
		Calendar turnoHoras;
		DateFormat dateFormat;
		
		int hora;
		int minuto;
		Date date = null;

		boolean turnoCorrecto = false;

		List<Turno> turnos = turnoService.getAll();
		switch (turnoComboBox.getSelectedIndex()) {

		case 0:
			for (Turno turno2 : turnos) {
				if (turno2.getNombre().equals(Turno.PRIMER_TURNO)) {
					Calendar calendarActual = Calendar.getInstance();
					int horaActual = calendarActual.get(Calendar.HOUR_OF_DAY);
					int minutoActual = calendarActual.get(Calendar.MINUTE);

					Calendar horaInicio = Calendar.getInstance();
					horaInicio.set(Calendar.HOUR_OF_DAY, Integer.parseInt(turno2.getHorario().substring(0, 2)));
					horaInicio.set(Calendar.MINUTE, Integer.parseInt(turno2.getHorario().substring(3, 5)));
					
					Calendar horaFinal = Calendar.getInstance();
					horaFinal.set(Calendar.HOUR_OF_DAY,
							Integer.parseInt(turno2.getHorario().substring(11, 13)));
					horaFinal.set(Calendar.MINUTE, 
							Integer.parseInt(turno2.getHorario().substring(14, 16)));
					if (calendarActual.after(horaInicio) && calendarActual.before(horaFinal)) {
						turnoCorrecto = true;
					}					
				}
			}
			break;
		case 1:
			for (Turno turno2 : turnos) {
				if (turno2.getNombre().equals(Turno.SEGUNDO_TURNO)) {
					Calendar calendarActual = Calendar.getInstance();
					int horaActual = calendarActual.get(Calendar.HOUR_OF_DAY);
					int minutoActual = calendarActual.get(Calendar.MINUTE);

					Calendar horaInicio = Calendar.getInstance();
					horaInicio.set(Calendar.HOUR_OF_DAY, Integer.parseInt(turno2.getHorario().substring(0, 2)));
					horaInicio.set(Calendar.MINUTE, Integer.parseInt(turno2.getHorario().substring(3, 5)));
					
					Calendar horaFinal = Calendar.getInstance();
					horaFinal.set(Calendar.HOUR_OF_DAY,
							Integer.parseInt(turno2.getHorario().substring(11, 13)));
					horaFinal.set(Calendar.MINUTE, 
							Integer.parseInt(turno2.getHorario().substring(14, 16)));
					if (calendarActual.after(horaInicio) && calendarActual.before(horaFinal)) {
						turnoCorrecto = true;
					}					
				}
			}

			break;
		case 2:
			for (Turno turno2 : turnos) {
				if (turno2.getNombre().equals(Turno.TERCER_TURNO)) {
					Calendar calendarActual = Calendar.getInstance();
					int horaActual = calendarActual.get(Calendar.HOUR_OF_DAY);
					int minutoActual = calendarActual.get(Calendar.MINUTE);

					Calendar horaInicio = Calendar.getInstance();
					horaInicio.set(Calendar.HOUR, Integer.parseInt(turno2.getHorario().substring(0, 2)));
					horaInicio.set(Calendar.MINUTE, Integer.parseInt(turno2.getHorario().substring(3, 5)));
					
					Calendar horaFinal = Calendar.getInstance();
					horaFinal.set(Calendar.HOUR,
							Integer.parseInt(turno2.getHorario().substring(11, 13)));
					horaFinal.set(Calendar.MINUTE, 
							Integer.parseInt(turno2.getHorario().substring(14, 16)));
					
					/*
					if (calendarActual.after(horaInicio) && calendarActual.before(horaFinal)) {
						turnoCorrecto = true;
					}
					*/
					
					if( (horaActual >= 22 && minutoActual >= 30) && (horaActual <= 23 && minutoActual <= 59) ) {
						turnoCorrecto = true;
					}
					if( (horaActual >= 0 && minutoActual >= 0) && (horaActual < 7 && minutoActual <= 59) ) {
						turnoCorrecto = true;
					}
				}
			}
			break;
		}

		return turnoCorrecto;
	}

	public void iniciarSecuenciaDeCargadoDeDatos(File archivo) {

		file = archivo;
		
		statusBar.publishMessageOnStatusBar("Abriendo Archivo xls");
								
			try {
				
				sheetData=getDataXlsFile(file, 0);
				
				if(sheetData!=null){
					excelViewer.setModel(new DefaultTableModel());
					showExcelData(sheetData, false);
					pathLocationField.setText(file.toString());
					spinnerBatch.setEnabled(true);
					saveToProducction.setEnabled(true);
					cancel.setEnabled(true);
					turnoComboBox.setEnabled(true);
				}
				else {
					cleanComponents();
				}
				
			} catch (Exception e1) {
				cleanComponents();
				e1.printStackTrace();
			}
		
	
	}

private String excedeNumeroDeRegistrosEnPLC(int batch){
		
		String returnValue = "";
		int bascula = 0;
		int conteoDeBasculas = 0;
		int basculaAContar=0;
		
		for (int i = 0; i < excelViewer.getRowCount(); i++) {
			bascula = Integer.parseInt(excelViewer.getValueAt(i, 0).toString());
			conteoDeBasculas = 0;
			for (int j = 0; j < excelViewer.getRowCount(); j++) {
				basculaAContar = Integer.parseInt(excelViewer.getValueAt(j, 0).toString());
				
				if (bascula == basculaAContar) {
					conteoDeBasculas ++;
				}
			}
			if( (conteoDeBasculas * batch) > 20) {
				returnValue = conteoDeBasculas
						+" Ingredientes en bascula "
						+ bascula+"\n"
						+ "Numero de registros en PLC solicitados: "+ (conteoDeBasculas * batch);
				break;
			}
		}
		return returnValue;
	}
}
