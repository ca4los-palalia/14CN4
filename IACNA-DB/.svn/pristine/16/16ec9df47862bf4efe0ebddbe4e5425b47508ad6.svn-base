package com.cplsys.iacna.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Formula implements Serializable {

	private static final long serialVersionUID = 7720421353614179069L;
	private Integer idFormula;
	private String descripcion;
	private String nombre;
	
	private List<Ingrediente> ingrediente = new ArrayList<Ingrediente>();
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
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "formula")
	public List<Ingrediente> getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(List<Ingrediente> ingrediente) {
		this.ingrediente = ingrediente;
	}
	
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
	
	@Basic
	@Column(name = "cancelada")
	public boolean isCancelada() {
		return cancelada;
	}

	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}
	
	@Basic
	@Column(name = "despachada")
	public boolean isDespachada() {
		return despachada;
	}

	public void setDespachada(boolean despachada) {
		this.despachada = despachada;
	}

	@Basic
	@Column(name = "prepesado")
	public boolean isPrepesado() {
		return prepesado;
	}

	public void setPrepesado(boolean prepesado) {
		this.despachada = prepesado;
	}	
	
}
