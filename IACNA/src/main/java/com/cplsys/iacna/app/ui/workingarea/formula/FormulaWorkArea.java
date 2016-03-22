package com.cplsys.iacna.app.ui.workingarea.formula;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.values.XmlValueDisconnectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.app.ui.statusbar.IACNAStatusBar;
import com.cplsys.iacna.domain.Formula;
import com.cplsys.iacna.domain.IACNADispatcher;
import com.cplsys.iacna.domain.Ingrediente;
import com.cplsys.iacna.domain.Turno;
import com.cplsys.iacna.launcher.ExtensionFilter;
import com.cplsys.iacna.services.BasculaService;
import com.cplsys.iacna.services.FormulaService;
import com.cplsys.iacna.services.IACNADispatcherService;
import com.cplsys.iacna.services.IngredienteService;
import com.cplsys.iacna.services.ProductoService;
import com.cplsys.iacna.services.TurnoService;
import com.cplsys.iacna.ui.SheetWindow;
import com.cplsys.iacna.ui.SheetWindowExcel;
import com.cplsys.iacna.utils.CellExcel;
import com.cplsys.iacna.utils.UpdateCellExcel;
import com.cplsys.iacna.utils.ifaces.IACNAIface;
import com.cplsys.iacna.utils.ifaces.LogginListener;

@Repository
public class FormulaWorkArea extends JPanel implements IACNAIface, LogginListener {

	private static final long serialVersionUID = -2607532134751788357L;
	
	private JLabel locationLegend;
	private JLabel turnoLegend;
	private JLabel batchLegend;
	
	private JLabel materialLabel;
	private JLabel materialNameLabel;	
	private JLabel productoLabel;
	private JLabel productoNameLabel;	
	private JLabel productoDescripcionLabel;
	private JLabel productoDescripcionNameLabel;	
	private JLabel formulaLabel;
	private JLabel formulaNameLabel;
	private JTextField pathLocationField;	
	private JButton openExcelFileButton;	
	private JTable resultsTable;
	private JScrollBar scrollBar;	
	private JPanel headerContent;
	private JPanel bodyContent;
	private JPanel footerContent;	
	private JSpinner spinnerBatch;	
	private JButton saveToProducction;
	private JButton cancel;	
	private JComboBox turnoComboBox;	
	private JFileChooser fileChooser;
	
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
	
	private Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    private boolean waitCursorIsShowing;
    private int indexsheet;
    private boolean cancellLoadFile;
    private Dimension d;
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
	private SheetWindowExcel sheetWindow;
	
	@PostConstruct
	public void init() {
		initObjects();
		initProperties();
		initListeners();
		createUI();
	}

	@Override
	public void initProperties() {
		setLayout(new MigLayout("insets 25 50 0 0"));
		saveToProducction.setEnabled(false);
		cancel.setEnabled(false);
		pathLocationField.setEditable(false);
		spinnerBatch.setEnabled(false);
		turnoComboBox.setModel(cModel);
	    turnoComboBox.setEnabled(false);
	    
		fileChooser.addChoosableFileFilter(new ExtensionFilter(
				new String[] { ".XLS", ".XLSX"}, "Documentos de Excel   (*.XLS|XLSX)"));
		fileChooser.setAcceptAllFileFilterUsed(false);
	    fileChooser.setDialogTitle("Cargar Archivo de Excel");

	    
	    openExcelFileButton.setIcon(new ImageIcon(getClass().getResource("/media/excelImport.png")));
	    
	    saveToProducction.setIcon(new ImageIcon(getClass().getResource("/media/acceptIcon.png")));
		cancel.setIcon(new ImageIcon(getClass().getResource("/media/cancelIcon.png")));
	}

	@Override
	public void initObjects() {
		d = Toolkit.getDefaultToolkit().getScreenSize();
		turnoComboBox = new JComboBox();
		turnoLegend = new JLabel("Turno: ");
		batchLegend = new JLabel("Batch: ");
		
		locationLegend = new JLabel("Formula: ");
		
		materialLabel = new JLabel("Material: ");
		materialNameLabel = new JLabel();
		productoLabel = new JLabel("Producto: ");
		productoNameLabel = new JLabel();
		
		productoDescripcionLabel = new JLabel("Descripcion: ");
		productoDescripcionNameLabel = new JLabel();
		
		formulaLabel = new JLabel("Nombre: ");
		formulaNameLabel = new JLabel();
		
		pathLocationField = new JTextField(64);
		openExcelFileButton = new JButton("Explorar");
		
		resultsTable = new JTable();
		scrollBar = new JScrollBar();
		scrollBar.add(resultsTable);
		
		spinnerBatch = new JSpinner();
		spinnerBatch.setModel(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));
		
		saveToProducction = new JButton("Confirmar");
		cancel = new JButton("Cancelar");
		
		headerContent = new JPanel(new MigLayout("insets 20 0 0 0"));
		bodyContent = new JPanel(new MigLayout("insets 15 55 0 0"));
		footerContent = new JPanel(new MigLayout("insets 15 55 0 0"));
		fileChooser = new JFileChooser();
		cModel = new DefaultComboBoxModel();

		//-----------------
		sheetData = new ArrayList();
		celResultsTable=new CellExcel();
	}

	@Override
	public void initListeners() {
		openExcelFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				statusBar.publishMessageOnStatusBar("Abriendo Archivo xls");
				int opc = fileChooser.showOpenDialog(null);
				if (opc == JOptionPane.OK_OPTION) {
										
					try {
						
						file = fileChooser.getSelectedFile();
						sheetData=getDataXlsFile(file, 0);
						
						if(sheetData!=null){
							pathLocationField.setText(file.toString());
							showExcelData(sheetData, false);
							openExcelFileButton.setEnabled(false);
							spinnerBatch.setEnabled(true);
							saveToProducction.setEnabled(true);
							cancel.setEnabled(true);
							turnoComboBox.setEnabled(true);
						}
						else {
							cleanComponents();
							JOptionPane.showMessageDialog(null, "Error al cargar el archivo\nFormato invalido o el archivo se encuentra danhado");
						}
						
					} catch (Exception e1) {
						cleanComponents();
						e1.printStackTrace();
					}
				}
				else if(opc== JFileChooser.CANCEL_OPTION)
					cleanComponents();
	            	statusBar.publishMessageOnStatusBar(
	            			"Carga de formulas cancelada");//enviar mensaje a la barra de estado
			}
		});
		
		saveToProducction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int opc = JOptionPane.showConfirmDialog(null,  "Esta seguro de transferir\n" +
															   "esta formula a produccion", "", 
															   JOptionPane.YES_NO_OPTION);
				if (opc == JOptionPane.OK_OPTION) {
					IACNADispatcher iacnaDispatcher = new IACNADispatcher();
					Formula formula = new Formula();
					
					formula.setDescripcion(productoDescripcionNameLabel.getText());
					formula.setNombre(formulaNameLabel.getText());
					formula.setTotalBatchAProcesar(
							new Integer(spinnerBatch.getValue().toString()));
					formula.setTurno(turnoComboBox.getSelectedItem().toString());
					formulaService.saveFormula(formula);
					
					for (int i = 0; i < resultsTable.getRowCount(); i++) {
						Ingrediente ingrediente = new Ingrediente();
						ingrediente.setFormula(formula);
						ingrediente.setFormulaIdForWS(formula.getIdFormula().toString());
						
						for (int j = 0; j < resultsTable.getColumnCount(); j++) {
							
							switch (j) {
							case 0: ingrediente.setBascula(resultsTable.getValueAt(i,j).toString());
									
								if(resultsTable.getValueAt(i,j).toString().equals("6")){
									ingrediente.setPrepesadoBascula(true);								
								}
								else{
									ingrediente.setPrepesadoBascula(false);
								}
								break;
							
							case 1: ingrediente.setMp(resultsTable.getValueAt(i, j).toString());
								break;
							case 2: ingrediente.setDescripcion(resultsTable.getValueAt(i, j)
										.toString());
									ingrediente.setNombre(resultsTable.getValueAt(i, j)
											.toString());
								break;
							case 3: ingrediente.setUnidadMedida(resultsTable.getValueAt(i, j)
										.toString());
								break;
							case 4: ingrediente.setEspecificacion(
									Integer.parseInt(resultsTable.getValueAt(i, j)
									.toString()));
								break;
							case 5:	ingrediente.setToleranciaMinima(
									Integer.parseInt(resultsTable.getValueAt(i, j)
									.toString()));
								break;
							case 6: ingrediente.setToleranciaMaxima(
									Integer.parseInt(resultsTable.getValueAt(i, j)
									.toString()));
								break;
							}
						}
						ingredienteService.saveIngrediente(ingrediente);						
					}
					iacnaDispatcher.setFormula(formula);
					iacnaDispatcherService.saveIACNADispatcher(iacnaDispatcher);
					proccessStack.pushToProccessStack(iacnaDispatcher);
					statusBar.updateEmergencyStopManager();
					resultsTable.setModel(new DefaultTableModel());
					spinnerBatch.setValue(new Integer(1));
					spinnerBatch.setEnabled(false);
					saveToProducction.setEnabled(false);
					cancel.setEnabled(false);
					openExcelFileButton.setEnabled(true);
					pathLocationField.setText("");
					statusBar.publishMessageOnStatusBar("Datos enviados");
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cleanComponents();
			}
		});
		
		turnoComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
				
		resultsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(resultsTable.getModel().getColumnCount() > 0) {
					
					try{
						primerValor=(resultsTable.getValueAt(resultsTable.getSelectedRow(), 
								resultsTable.getSelectedColumn())).toString();
						System.err.println(resultsTable.getValueAt(resultsTable.getSelectedRow(), 
								resultsTable.getSelectedColumn()));
					}
					catch(Exception e1){
						System.err.println("error al obtener primerValor 'FormulaWorkArea 318'");
					}
				}
				
			}
		});
		
		resultsTable.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if(resultsTable.getModel().getRowCount() > 0) {					
					if(resultsTable.getSelectedRow() >= 0 || 
							resultsTable.getSelectedColumn() >= 0) {
						
						String changeValue=resultsTable.getValueAt(resultsTable.getSelectedRow(), resultsTable.getSelectedColumn()).toString();
						
						if(primerValor!=null){
							if(!primerValor.equalsIgnoreCase(changeValue)){
								
								
								//ACTUALIZAR celda de excel
								try {
									
									bodyContent.setCursor(waitCursor);
									updateCellExcel(celResultsTable.getRowExcel(resultsTable.getSelectedRow()), changeValue);
									bodyContent.setCursor(defaultCursor);
								} catch (XmlValueDisconnectedException e1) {
									e1.printStackTrace();
								}
								
								System.err.println("Actualizado");
							}
						}
					}
				}
			}
		});
	}

	@Override
	public void createUI() {
		headerContent.add(locationLegend);
		headerContent.add(pathLocationField);
		headerContent.add(openExcelFileButton, "width 120:120:120, wrap");		
		headerContent.add(materialLabel);
		headerContent.add(materialNameLabel);
		headerContent.add(productoLabel);
		headerContent.add(productoNameLabel);
		headerContent.add(productoDescripcionLabel);
		headerContent.add(productoDescripcionNameLabel);
		headerContent.add(formulaLabel);
		headerContent.add(formulaNameLabel, "width 87:87:87, wrap");
		
		headerContent.add(buildSeparator(), "width 855:855:855, span");
		
		bodyContent.add(resultsTable, "height 450:450:450, width 800:800:800, span");		
		bodyContent.add(buildSeparator(), "width 800:800:800, span");		
		bodyContent.add(batchLegend);
		bodyContent.add(spinnerBatch);
		bodyContent.add(turnoLegend);
		bodyContent.add(turnoComboBox, "width 100:100:100, wrap");

		footerContent.add(saveToProducction);
		footerContent.add(cancel);
		
		this.add(headerContent, "wrap");
		this.add(bodyContent, "wrap");
		this.add(footerContent);
	}
	
	private JSeparator buildSeparator() {
		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setForeground(Color.BLACK);		
		return separator;
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
										System.err.println("Modificado: ["+cell.getRowIndex()+"-"+(modificarCell)+"]");
										XSSFRow titleRow = sheet.getRow(modificarCell);// Obtener Fila
										XSSFCell hcell = titleRow.getCell(0); //Celda
										hcell.setCellValue(changeValue); // Asignar valor
										
										try{
											fileOut = new FileOutputStream(file);
											workbook.write(fileOut);
											//fileOut.close();
											found=true;
											break;
										}catch(FileNotFoundException e){
											JOptionPane.showMessageDialog(null, "El proceso no puede accesar al archivo");
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
				}// END for columnas
				
				if (start == true) {
					tempRow++;
				}
				if(found==true)
					break;				
			}// END for Filas
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public String getDataCell(XSSFCell cel){
		String salida="";
		
		switch (cel.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				salida = cel.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cel)) {
					salida = cel.getDateCellValue().toString();
				} else {
					salida = String.valueOf(((int) cel
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
		return salida;
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
							if (!salida.equals("")){								
								if(prepesado==false){									
									campos.add(salida);									
									if(cell.getColumnIndex()==0){
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
								resultsTable.setValueAt(campos.get(k), row, k);
								if(k!=0){
									resultsTable.isCellEditable(row, k);
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
	
	public List getDataXlsFile(File archivo, int tipo) throws InvalidFormatException{
		
		List sheetDataTemp = new ArrayList();		
		statusBar.publishMessageOnStatusBar("Leyendo archivo xls "+archivo.getName());
		
		try {
			stream =new FileInputStream(archivo.toString());
			
			try {
				workbook = new XSSFWorkbook(stream);
				setWorkbook(workbook);
				//SheetWindow sheetWindow = new SheetWindow(workbook.getNumberOfSheets(), workbook);
				sheetWindow.loadSheets(workbook.getNumberOfSheets(), workbook);
				switch(tipo){
					case 0:					
						statusBar.publishMessageOnStatusBar("Hojas contenidas en el archivo "+archivo.getName());						
						sheetWindow.setVisible(true);
						indexsheet=sheetWindow.getIndexSheet();
						setIndexsheet(indexsheet);
						
						break;
					case 1:
						break;
				}
				
				if(indexsheet!=0) {
					sheet = workbook.getSheetAt(indexsheet);	
					setSheet(sheet);
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
					setCancellLoadFile(true);
					return null;
				}
				
			}
			catch(OldExcelFormatException e){
				return null;
			}
			
		} catch (Exception e1) {
			return null;
		}
	}
	
	public void generarColumna(String title, int col) {

		DefaultTableModel dtm = (DefaultTableModel) resultsTable.getModel();
		dtm.addColumn(title);
		TableColumn column = resultsTable.getColumnModel().getColumn(col);
		column.setPreferredWidth(150);
	}
	
	private void generarFila() {
		DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
		model.addRow(new Object[] {});
	}

	public JSpinner getSpinnerBatch() {
		return spinnerBatch;
	}

	public void setSpinnerBatch(JSpinner spinnerBatch) {
		this.spinnerBatch = spinnerBatch;
	}
	
	public void editingStopped(ChangeEvent e) {
        System.out.println("A cell has been edited.");
    }
	
	public void editingCanceled(ChangeEvent e) {
        System.out.println("Editing of a cell has been canceled.");
    }

	@Override
	public void sessionActivated() {
		
		List<Turno> turnos = turnoService.getAll();
	    for (Turno turno : turnos) {
			cModel.addElement(turno.getNombre());
		}
	}

	@Override
	public void sessionDeactivated() {
		cleanComponents();
	}
	
	public void cleanComponents() {
		
		resultsTable.setModel(new DefaultTableModel());
		spinnerBatch.setValue(new Integer(1));
		spinnerBatch.setEnabled(false);
		saveToProducction.setEnabled(false);
		cancel.setEnabled(false);
		openExcelFileButton.setEnabled(true);
		pathLocationField.setText("");
		materialNameLabel.setText("");
		productoNameLabel.setText("");
		productoDescripcionNameLabel.setText("");
		formulaNameLabel.setText("");
		sheetData = new ArrayList();
	}
	
	public boolean isCancellLoadFile() {
		return cancellLoadFile;
	}

	public void setCancellLoadFile(boolean cancellLoadFile) {
		this.cancellLoadFile = cancellLoadFile;
	}

	
	public XSSFSheet getSheet() {
		return sheet;
	}

	public void setSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}

	public XSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public int getIndexsheet() {
		return indexsheet;
	}

	public void setIndexsheet(int indexsheet) {
		this.indexsheet = indexsheet;
	}

	public Iterator getRows() {
		return rows;
	}

	public void setRows(Iterator rows) {
		this.rows = rows;
	}

	
	
}
