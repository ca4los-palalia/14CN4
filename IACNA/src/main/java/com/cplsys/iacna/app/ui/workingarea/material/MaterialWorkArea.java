package com.cplsys.iacna.app.ui.workingarea.material;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import net.miginfocom.swing.MigLayout;

import org.apache.poi.hssf.OldExcelFormatException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.dialect.IngresDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsys.iacna.app.ui.statusbar.IACNAStatusBar;
import com.cplsys.iacna.domain.Formula;
import com.cplsys.iacna.domain.IACNADispatcher;
import com.cplsys.iacna.domain.Ingrediente;
import com.cplsys.iacna.domain.RegistroProduccion;
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
import com.cplsys.iacna.utils.editor.FormulaCellEditor;
import com.cplsys.iacna.utils.editor.RegistroProduccionCellEditor;
import com.cplsys.iacna.utils.ifaces.IACNAIface;
import com.cplsys.iacna.utils.ifaces.LogginListener;

@Repository
public class MaterialWorkArea extends JPanel implements IACNAIface, LogginListener {

	private static final long serialVersionUID = -2607532134751788357L;
	
	private JLabel locationLegend;
	private JLabel turnoLegend;
	private JLabel batchLegend;
	
	private JTextField pathLocationField;
	
	private JButton openExcelFileButton;
	
	private JTable resultsTable;
	private JScrollBar scrollBar;	
	
	private JPanel headerContent;
	private JPanel headerRuta;
	private JPanel bodyContent;
	private JPanel footerContent;
	
	private JSpinner spinnerBatch;
	
	private JButton saveToProducction;
	private JButton cancel;
	
	private JComboBox turnoComboBox;
	
	private JFileChooser fileChooser;
	
	private List sheetData, data, list;
	private File file;
	private FileInputStream stream;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private Iterator rows;
	private XSSFRow row;
	private XSSFCell cell;
	private Vector campos;
	private int indexsheet;
	private CellExcel celResultsTable;
	
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
		cancel.setEnabled(false);
		fileChooser.addChoosableFileFilter(new ExtensionFilter(
				new String[] { ".XLS", ".XLSX"}, "Documentos de Excel   (*.XLS|XLSX)"));
		fileChooser.setAcceptAllFileFilterUsed(false);
	    fileChooser.setDialogTitle("Cargar Archivo de Excel");
	    
	    List<Turno> turnos = turnoService.getAll();
	    for (Turno turno : turnos) {
			cModel.addElement(turno.getNombre());
		}
	    turnoComboBox.setModel(cModel);
	    turnoComboBox.setEnabled(false);
	    
	}

	@Override
	public void initObjects() {
		turnoComboBox = new JComboBox();
		turnoLegend = new JLabel("Turno: ");
		
		locationLegend = new JLabel("Formula: ");
		pathLocationField = new JTextField(64);
		openExcelFileButton = new JButton("Explorar");
		
		resultsTable = new JTable();
		
		scrollBar = new JScrollBar();
		scrollBar.add(resultsTable);
		batchLegend = new JLabel("Batch: ");
		spinnerBatch = new JSpinner();
		spinnerBatch.setModel(new javax.swing.SpinnerNumberModel(1, 1, 99, 1));
		
		saveToProducction = new JButton("Confirmar");
		cancel = new JButton("Cancelar");
		
		headerContent = new JPanel(new MigLayout("insets 20 0 0 0"));
		headerRuta = new JPanel(new MigLayout("insets 20 0 0 0"));
		
		
		bodyContent = new JPanel(new MigLayout("insets 5 10 0 0", "[pref!][grow,fill]", "[]15[]"));
		footerContent = new JPanel(new MigLayout("insets 5 10 0 0", "[pref!][grow,fill]", "[]15[]"));
		/*
		bodyContent = new JPanel(new MigLayout("insets 15 55 0 0"));
		footerContent = new JPanel(new MigLayout("insets 15 55 0 0"));
		*/
		
		fileChooser = new JFileChooser();
		cModel = new DefaultComboBoxModel();
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
							JOptionPane.showMessageDialog(null, "Error al cargar el archivo\nFormato invalido o el archivo se encuentra danhado");
						}
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
				else if(opc== JFileChooser.CANCEL_OPTION)
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
					formula.setTotalBatchAProcesar(new Integer(spinnerBatch.getValue().toString()));
					formula.setTurno(turnoComboBox.getSelectedItem().toString());
					formulaService.saveFormula(formula);
					for (int i = 0; i < resultsTable.getRowCount(); i++) {
						Ingrediente ingrediente = new Ingrediente();
						for (int j = 0; j < resultsTable.getColumnCount(); j++) {
							switch (j) {
							case 0: ingrediente.setBascula(resultsTable.getValueAt(i,j).toString());	
								break;
							case 1:
								ingrediente.setMp(resultsTable.getValueAt(i, j).toString());
								break;
							case 2: ingrediente.setDescripcion(resultsTable.getValueAt(i, j)
										.toString());
								break;
								/*
							case 3: ingrediente.setUnidadMedida(resultsTable.getValueAt(i, j)
										.toString());
								break;
								*/
							case 3:ingrediente.setEspecificacion(
									Integer.parseInt(resultsTable.getValueAt(i, j)
									.toString()));
								break;
							case 4:	ingrediente.setToleranciaMinima(
									Integer.parseInt(resultsTable.getValueAt(i, j)
									.toString()));
								break;
							case 5: ingrediente.setToleranciaMaxima(
									Integer.parseInt(resultsTable.getValueAt(i, j)
									.toString()));
								break;
							}
						}
						ingrediente.setFormula(formula);
						ingredienteService.saveIngrediente(ingrediente);
						
					}
					iacnaDispatcher.setFormula(formula);
					iacnaDispatcherService.saveIACNADispatcher(iacnaDispatcher);
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
				resultsTable.setModel(new DefaultTableModel());
				spinnerBatch.setValue(new Integer(1));
				spinnerBatch.setEnabled(false);
				saveToProducction.setEnabled(false);
				cancel.setEnabled(false);
				openExcelFileButton.setEnabled(true);
				pathLocationField.setText("");
			}
		});
		
		turnoComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
		
		
	}

	@Override
	public void createUI() {
		headerContent.add(locationLegend);
		headerContent.add(pathLocationField, "width 855:855:855, span");
		headerContent.add(openExcelFileButton, "width 87:87:87, wrap");
		headerContent.add(buildSeparator(), "width 855:855:855, span");
		
		bodyContent.add(resultsTable, "height 450:450:450, width 800:800:800, span");
		
		bodyContent.add(buildSeparator(), "width 800:800:800, span");
		
		bodyContent.add(batchLegend);
		bodyContent.add(spinnerBatch);
		bodyContent.add(turnoLegend);
		bodyContent.add(turnoComboBox, "width 100:100:100, wrap");

		footerContent.add(saveToProducction);
		footerContent.add(cancel);
		
		
		/*
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 978, Short.MAX_VALUE)
            .addComponent(bodyContent)
        );
        
        //----------------------
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
            .addComponent(bodyContent)
        );
		*/
		this.setLayout(new MigLayout("insets 1 1 0 1, wrap 3", "", "[][][][]"));
		
		this.add(headerContent, "wrap");
		this.add(bodyContent, "wrap");
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
	
	private List getDataXlsFile(File archivo, int tipo) throws InvalidFormatException{
		
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
						indexsheet=sheetWindow.getIndexSheet();
						break;
					case 1:
						break;
				}
				
				
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
			catch(OldExcelFormatException e){
				return null;
			}
			
		} catch (Exception e1) {
			return null;
		}
	}
	
	private String getDataCell(XSSFCell cel){
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
	
	private void generarColumna(String title, int col) {

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
		
		
	}

	@Override
	public void sessionDeactivated() {
		// TODO Auto-generated method stub
		
	}
	
}
