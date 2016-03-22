package com.cplsys.iacna.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.swing.JOptionPane;
import javax.swing.JTable;

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

import com.cplsys.iacna.app.ui.workingarea.formula.FormulaWorkArea;
import com.cplsys.iacna.app.ui.workingarea.material.MaterialVisorWorkArea;
import com.cplsys.iacna.ui.SheetWindow;

@Repository
public class UpdateCellExcel {

	private String primerValor;
	private File path;
	
	private JTable resultTable;
	private List sheetData;
	private List data;
	private List list;
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private Iterator rows;
	private XSSFRow row;
	private XSSFCell cell;
	private Vector campos;
	private CellExcel celResultsTable;
	
	@Autowired
	private FormulaWorkArea formulaWorkArea;
	@Autowired
	private MaterialVisorWorkArea materialVisorWorkArea;
	
	@PostConstruct
	public void init() {
		workbook = new XSSFWorkbook();
		celResultsTable = new CellExcel();
	}

	public void updateCellExcel(int modificarCelda, String nuevoValor,File file) throws XmlValueDisconnectedException {
		
		int tempRow = 0;
		String salida = "";
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
								
								if(cell.getRowIndex()==(modificarCelda+1)){
									
									if(cell.getColumnIndex()==0){
										System.err.println("Modificado: ["+cell.getRowIndex()+"-"+(modificarCelda)+"]");
										XSSFRow titleRow = sheet.getRow(modificarCelda);// Obtener Fila
										XSSFCell hcell = titleRow.getCell(0); //Celda
										hcell.setCellValue(nuevoValor); // Asignar valor
										
										try{
											FileOutputStream fileOut = new FileOutputStream(file);
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
	
	public List getDataXlsFile(File archivo, int tipo) throws InvalidFormatException{
		
		List sheetDataTemp = new ArrayList();
		//int indexsheet = formulaWorkArea.getIndexsheet();
		int indexsheet = 1;
		
		try {
			FileInputStream stream =new FileInputStream(archivo.toString());
			try {
				workbook = new XSSFWorkbook(stream);
								
				
				
				if(indexsheet!=0) {
					sheet = workbook.getSheetAt(indexsheet);
					rows =  sheet.rowIterator();
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
	
	public String getPrimerValor() {
		return primerValor;
	}

	public void setPrimerValor(String primerValor) {
		this.primerValor = primerValor;
	}

	public File getPath() {
		return path;
	}

	public void setPath(File path) {
		this.path = path;
	}

	public List getSheetData() {
		return sheetData;
	}

	public void setSheetData(List sheetData) {
		this.sheetData = sheetData;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public XSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public XSSFSheet getSheet() {
		return sheet;
	}

	public void setSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}

	public Iterator getRows() {
		return rows;
	}

	public void setRows(Iterator rows) {
		this.rows = rows;
	}

	public XSSFRow getRow() {
		return row;
	}

	public void setRow(XSSFRow row) {
		this.row = row;
	}

	public XSSFCell getCell() {
		return cell;
	}

	public void setCell(XSSFCell cell) {
		this.cell = cell;
	}

	public CellExcel getCelResultsTable() {
		return celResultsTable;
	}

	public void setCelResultsTable(CellExcel celResultsTable) {
		this.celResultsTable = celResultsTable;
	}
	
	
	
	
}
