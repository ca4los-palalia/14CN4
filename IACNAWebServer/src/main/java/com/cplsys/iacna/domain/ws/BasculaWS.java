package com.cplsys.iacna.domain.ws;


public class BasculaWS {

	private String nombre;
	private String descripcion;
	private String unidadMedida;

	private int batchs;
	private boolean basculaAsignada;
	private boolean modoManual;
	private boolean prepesado;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public boolean isBasculaAsignada() {
		return basculaAsignada;
	}

	public void setBasculaAsignada(boolean basculaAsignada) {
		this.basculaAsignada = basculaAsignada;
	}

	public boolean isModoManual() {
		return modoManual;
	}

	public void setModoManual(boolean modoManual) {
		this.modoManual = modoManual;
	}

	public int getBatchs() {
		return batchs;
	}

	public void setBatchs(int batchs) {
		this.batchs = batchs;
	}

	public boolean isPrepesado() {
		return prepesado;
	}

	public void setPrepesado(boolean prepesado) {
		this.prepesado = prepesado;
	}
	
}
