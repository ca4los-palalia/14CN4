package com.cplsys.iacna.utils;

import java.awt.Color;

public class IACNAConstants {

	/** DEPLOYED WS URL */
	public final static String IACNA_WS_ADDRESS = "http://localhost:9999/ws/IACNA";
	/** WSDL ADDRESS */
	public final static String IACNA_WS_WSDL_ADDRESS = IACNA_WS_ADDRESS
			+ "?wsdl";
	/** WEBSERVICE NAMESPACE */
	public final static String IACNA_WS_NAME_SPACE = "http://webservice.iacna.cplsys.com/";
	/** WEB SERVICE NAME */
	public final static String IACNA_WS_SERVICE_NAME = "IACNAWebServiceImplService";
	
	public final static String DEFAULT_LAYERED_PANE = "DEFAULT";
	
	public final static String WORK_AREA_LAYERED_PANE = "WORK_AREA";
	
	public final static String FORMULA_TAB_NAME = "Formulas";
	
	public final static String REPORTES_TAB_NAME = "Reportes";
	
	public final static String BASCULA_TAB_NAME = "Bascula";
	
	public final static String USUARIOS_TAB_NAME = "Usuarios";
	
	public final static String MATERIAL_TAB_NAME = "Material";
	
	public final static String MATERIAL_PREPESADO_TAB_NAME = "PrePesado";
	
	public final static String FORMULA_WORK_AREA_PANEL = "FORMULA_WAREA";
	
	public final static String REPORTES_WORK_AREA_PANEL = "REPORTE_WAREA";
	
	public final static String BASCULA_WORK_AREA_PANEL = "BASCULA_WAREA";
	
	public final static String USUARIO_WORK_AREA_PANEL = "USUARIO_WAREA";
	
	public final static String MATERIAL_WORK_AREA_PANEL = "MATERIAL_WAREA";
	
	public final static String MATERIAL_VIEWER_WORK_AREA_PANEL = "MATERIAL_VIEWER_WAREA";
	
	public final static String MATERIAL_PREPESADO_VIEWER_WORK_AREA_PANEL = "MATERIAL_PREPESADO_VIEWER_WAREA";
	
	public final static String FORMATO_FECHA_BUSQUEDA_DIA = "yyyy-MM-dd";

	public final static Color PAIR_ROW_REPORT_TABLE = new Color(222, 222, 255);
	
	public final static Color CELL_CANCELED_FORMULA = Color.ORANGE;//new Color(150, 0, 24);
	
	public final static Color CELL_ENABLED_FORMULA = new Color(155, 196, 226);
	
	public final static String FORMATO_TIEMPO = "hh:mm:ss";
	
	public final static int SOCKET_PORT_COMUNICATION = 9091;
	
}