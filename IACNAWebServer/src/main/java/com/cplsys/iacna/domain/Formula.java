package com.cplsys.iacna.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table
public class Formula implements Serializable {

	private static final long serialVersionUID = 4416294258351232746L;
	
	private Integer idFormula;
	
	//private String numeroParte;
	private String descripcion;
	private String nombre;
	//private Bascula bascula;
	//private Producto producto;
	
	//private Calendar fechaModificacion; ESPERAR
	
	//private Vector<Ingrediente> ingrediente = new Vector<Ingrediente>();
	private boolean cancelada;
	private boolean despachada;
	private boolean prepesado;
	private int totalBatchAProcesar;
	private int batchProcesado;
	private String turno;
	
	
	
	@Id
	@Column(name = "idFormula")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(Integer idFormula) {
		this.idFormula = idFormula;
	}
	
	/*
	@Column(name = "numeroParte")
	public String getNumeroParte() {
		return numeroParte;
	}

	public void setNumeroParte(String numeroParte) {
		this.numeroParte = numeroParte;
	}*/

	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idBascula")
	public Bascula getBascula() {
		return bascula;
	}

	public void setBascula(Bascula bascula) {
		this.bascula = bascula;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProducto")
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}*/

	/*ESPERAR
	@Column(name = "fechaModificacion")
	@Temporal(TemporalType.TIMESTAMP)
	@Version
	public Calendar getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Calendar fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	*/
	
/*
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "formula")
	public Vector<Ingrediente> getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Vector<Ingrediente> ingrediente) {
		this.ingrediente = ingrediente;
	}*/
	
	@Column(name = "totalBatchAProcesar")
	public int getTotalBatchAProcesar() {
		return totalBatchAProcesar;
	}

	public void setTotalBatchAProcesar(int totalBatchAProcesar) {
		this.totalBatchAProcesar = totalBatchAProcesar;
	}

	@Column(name = "batchProcesado")
	public int getBatchProcesado() {
		return batchProcesado;
	}

	public void setBatchProcesado(int batchProcesado) {
		this.batchProcesado = batchProcesado;
	}
	
	@Column(name = "turno")
	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	@Column(name = "cancelada")
	public boolean isCancelada() {
		return cancelada;
	}

	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}

	@Column(name = "despachada")
	public boolean isDespachada() {
		return despachada;
	}

	public void setDespachada(boolean despachada) {
		this.despachada = despachada;
	}
	
	@Column(name = "prepesado")
	public boolean isPrepesado() {
		return prepesado;
	}

	public void setPrepesado(boolean prepesado) {
		this.despachada = prepesado;
	}

}
