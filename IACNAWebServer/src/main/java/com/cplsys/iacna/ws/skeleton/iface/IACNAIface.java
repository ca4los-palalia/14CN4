package com.cplsys.iacna.ws.skeleton.iface;

import java.util.Calendar;

import com.cplsys.iacna.domain.Formula;
import com.cplsys.iacna.domain.Ingrediente;
import com.cplsys.iacna.domain.RegistroProduccion;
import com.cplsys.iacna.domain.ws.BasculaWS;

public interface IACNAIface {

	/**----------------------BASCULAS---------------------*/
	/**Devuelve el nombre de la bascula*/
	public String getBasculaNombre(int bascula);

	/**habilitada o deshabilitada (basculaAsignada)*/
	public boolean isBasculaHabilitada(int bascula);
	
	/**Devuelve si una bascula esta en modo manual o no**/
	public boolean isBasculaModoManual(int bascula);
	
	/**Devuelve una bascula con prepesado*/
	public boolean isBasculaPrepesado(int bascula);
	
	/**Devuelve la descripcion de una bascula*/
	public String getDescripcionBascula(int bascula);
	
	public void setDescripcionBascula(int bascula, String descripcion);
	
	public void habilitarBascula(int bascula);

	public void deshabilitarBascula(int bascula);
	
	public void setModoManualBascula(int bascula);
	
	/** obtiene el numero de envios al panelView
	 * ejem: [20], [20], [10]
	 * **/
	public int getBatchsCompleteBascula(int idBascula);
	
	/**Actualizar el numero de envios hacia el PanelView**/
	public void setBatchsCompleteBascula(int idBascula, int batchs);
	
	
	/**----------------------FORMULAS---------------------*/
	
	/**Devuelve el turno*/
	public String getTurnoFormula();
	
	/**Devuelve el nombre de la formula*/
	public String getNombreFormula(String idFormula);
	
	/**Devuelve un valor booleao para indicar si la formula esta cancelada*/
	public boolean isFormulaCancelada(String idFormula);
	
	public boolean isFormulaDespachada(String idFormula);
	
	public void setFormulaDespachada(String idFormula);
	
	
	/**Devuelve el numero de batchs de la formula*/
	public int getBatchsFormula(String idFormula);
	
	/**Devuelve el total de ingredientes de la formula*/
	public int getTotalIngredientesFormula(String idFormula);	
	
	/**Devuelve el identificador de la formula*/
	public String getIdFormula();
	
	/**Actualiza el batch de produccion de la formula*/
	public void updateBatch(int batchActual, String idFormula);
	
	
	/**----------------------INGREDIENTES---------------------*/
	
	/**Devuelve el nombre del ingrediente*/
	public String getNombreIngrediente(int ingrediente, String idFormula);
	
	/**Devuelve la bascula de pesaje del ingrediente*/
	public int getBasculaIngrediente(int ingrediente, String idFormula);
	
	/**Devuelve la especificacion del ingrediente*/
	public double getEspecificacionIngrediente(int ingrediente, String idFormula);
	
	/**Retorna el numero de ingredientes de una bascula en especifica**/
	public int getTotalIngredientesPorBascula(String idFormula, String bascula);

	public double getToleranciaMinima(int ingrediente, String idFormula);
	
	public double getToleranciaMaxima(int ingrediente, String idFormula);
	
	public String getMpIngrediente(int ingrediente, String idFormula);
	
	/**Devuelve un valor booleano si el ingrediente esta asignado a una bascula de prepesado*/
	public boolean isIngredienteAsignadoAPrepesado(int ingrediente, String idFormula);
	
	/**---------------------REGISTRO PRODUCCION-------------------*/
	
	/**xEsta funcion almacena un registro por cada ingrediente pesado correctamente, devuelve el indentificado en bd
	 * del registro de produccion*/
	public int guardarRegistroProduccion(String nombreFormula, String basculaAsignada, String mp, String descripcion, 
			double especificacionPeso, double toleranciaMinima, double toleranciaMaxima, int sumatoriaBatchsPesosObtenidos, 
			String turno, double totalBatchProcesados, String idFormula);
	
	/**Persiste en la bd un registro de un batch, relacionado con un registro de produccion*/
	public boolean saveBatchsRegistroProduccion(
			int idRegistroProduccion, double pesoObtenido, String addressMemory);
	
	/**Retorna direccion de memoria de plc, sobre un pesaje ya grabado en la BD**/
	public int getSavedBatchsRegistroProduccion(
			int idRegistroProduccion, double pesoObtenido, String addressMemory);
	
	/**Retorna el id de registroProduccion de un ingrediente específico*/
	public int getIdRegistroProduccion(int ingrediente, String idFormula);
	
	/**Retorna el batch en que se encuentra procesando la bascula*/
	public double getTotalBachtProcesados(String idFormula, String bascula, String descripcion);

	/**Actualiza los batch procesados por el sistema del registro produccion*/
	public void updateTotalBatchProcesados(String idFormula, String bascula, String descripcion, double nuevoBatch);

	/**Actualiza los batch procesados por el sistema del registro produccion*/
	public void updateSumatoriaBatchPesoObtenido(String idFormula, String bascula, String descripcion, double nuevoBatch);
	
	/**Retorna el batch en que se encuentra procesando la bascula*/
	public double getSumatoriaBatchPesoObtenido(String idFormula, String bascula, String descripcion);
	
	public boolean isFormulaPrepesada(String idFormula);
	
	public Integer getUltimoRegistroBacthProduccion();

}