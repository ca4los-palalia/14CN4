package com.cplsys.iacna.domain.ws;

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
public class OrigenDeDatosWS implements Serializable {

	private static final long serialVersionUID = 5931413280136808999L;

	private Integer idOrigenDeDatos;
	private String nombre;
	private String descripcion;
	private String locationProducts;
	private String locationProductosPrepesados;
	private Calendar fechaModificacion;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idOrigenDeDatos", nullable = false)
	public Integer getIdOrigenDeDatos() {
		return idOrigenDeDatos;
	}

	public void setIdOrigenDeDatos(Integer idOrigenDeDatos) {
		this.idOrigenDeDatos = idOrigenDeDatos;
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

	@Column(name = "locationProducts")
	public String getLocationProductos() {
		return locationProducts;
	}

	public void setLocationProductos(String locationProducts) {
		this.locationProducts = locationProducts;
	}

	@Column(name = "locationProductosPrepesados")
	public String getLocationProductosPrepesados() {
		return locationProductosPrepesados;
	}

	public void setLocationProductosPrepesados(String locationProductosPrepesados) {
		this.locationProductosPrepesados = locationProductosPrepesados;
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
	
}