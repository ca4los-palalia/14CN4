package com.cplsys.iacna.domain.ws;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

@Table
@Entity
public class RegistroProduccionWS {
	
	private Integer idRegistroProduccion;
	private String formula;
	private String bascula;
	private String mp;
	private String descripcion;
	private Double especificacion;
	private Integer toleranciaMinima;
	private Integer toleranciaMaxima;
	private int totalBatchProcesados;
	private double sumatoriaBatchPesosObtenidos;
	private String turno;
	private Calendar fechaRegistro;
	//private List<BatchProduccion> batchProduccion = new ArrayList<BatchProduccion>();
	
	@Id
	@Column(name = "idRegistroProduccion")
	@GeneratedValue (strategy = GenerationType.AUTO)
	public Integer getIdRegistroProduccion() {
		return idRegistroProduccion;
	}
	
	public void setIdRegistroProduccion(Integer idRegistroProduccion) {
		this.idRegistroProduccion = idRegistroProduccion;
	}
	
	@Column(name = "bascula")
	public String getBascula() {
		return bascula;
	}
	
	public void setBascula(String bascula) {
		this.bascula = bascula;
	}
	
	@Column(name = "mp")
	public String getMp() {
		return mp;
	}
	
	public void setMp(String mp) {
		this.mp = mp;
	}
	
	@Column(name = "descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(name = "especificacion")
	public Double getEspecificacion() {
		return especificacion;
	}
	
	public void setEspecificacion(Double especificacion) {
		this.especificacion = especificacion;
	}
	
	@Column(name = "toleranciaMinima")
	public Integer getToleranciaMinima() {
		return toleranciaMinima;
	}
	
	public void setToleranciaMinima(Integer toleranciaMinima) {
		this.toleranciaMinima = toleranciaMinima;
	}
	
	@Column(name = "toleranciaMaxima")
	public Integer getToleranciaMaxima() {
		return toleranciaMaxima;
	}
	
	public void setToleranciaMaxima(Integer toleranciaMaxima) {
		this.toleranciaMaxima = toleranciaMaxima;
	}
	
	@Column(name = "totalBatchProcesados")
	public int getTotalBatchProcesados() {
		return totalBatchProcesados;
	}
	
	public void setTotalBatchProcesados(int totalBatchProcesados) {
		this.totalBatchProcesados = totalBatchProcesados;
	}
	
	@Column(name = "sumatoriaBatchPesosObtenidos")
	public double getSumatoriaBatchPesosObtenidos() {
		return sumatoriaBatchPesosObtenidos;
	}
	
	public void setSumatoriaBatchPesosObtenidos(double sumatoriaBatchPesosObtenidos) {
		this.sumatoriaBatchPesosObtenidos = sumatoriaBatchPesosObtenidos;
	}
	
	@Column(name = "fechaRegistro")
	@Temporal(TemporalType.TIMESTAMP)
	@Version
	public Calendar getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Calendar fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	/*
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "registroProduccion")
	public List<BatchProduccion> getBatchProduccion() {
		return batchProduccion;
	}

	public void setBatchProduccion(List<BatchProduccion> batchProduccion) {
		this.batchProduccion = batchProduccion;
	}*/

	@Column(name = "turno")
	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	@Column(name = "formula")
	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	
}