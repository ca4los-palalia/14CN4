package com.cplsys.iacna.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table
public class Bascula implements Serializable {

	private static final long serialVersionUID = 5931413280136808999L;

	private Integer idBascula;
	private String nombre;
	private String descripcion;
	private String unidadMedida;
	private Calendar fechaModificacion;
	private int batchs;
	private boolean basculaAsignada;
	private boolean modoManual;
	private boolean prepesado;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idBascula", nullable = false)
	public Integer getIdBascula() {
		return idBascula;
	}

	public void setIdBascula(Integer idBascula) {
		this.idBascula = idBascula;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "unidadMedida")
	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	@Column(name = "fechaModificacion")
	@Temporal(TemporalType.TIMESTAMP)
	@Version
	public Calendar getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Calendar fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	@Column(name = "basculaAsignada")
	public boolean isBasculaAsignada() {
		return basculaAsignada;
	}

	public void setBasculaAsignada(boolean basculaAsignada) {
		this.basculaAsignada = basculaAsignada;
	}

	@Column(name = "modoManual")
	public boolean isModoManual() {
		return modoManual;
	}

	public void setModoManual(boolean modoManual) {
		this.modoManual = modoManual;
	}

	@Column(name = "batchs")
	public int getBatchs() {
		return batchs;
	}

	public void setBatchs(int batchs) {
		this.batchs = batchs;
	}

	@Column(name = "prepesado")
	public boolean isPrepesado() {
		return prepesado;
	}

	public void setPrepesado(boolean prepesado) {
		this.prepesado = prepesado;
	}
}
