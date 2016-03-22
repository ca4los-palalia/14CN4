package com.cplsys.iacna.domain;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

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
public class RegistroProduccion {
	
	private Integer idRegistroProduccion;
	private String formula;
	private String idFormula;
	private String bascula;
	private String mp;
	private String descripcion;
	private double especificacion;
	private double toleranciaMinima;
	private double toleranciaMaxima;
	private double totalBatchProcesados;
	private double sumatoriaBatchPesosObtenidos;
	private String turno;
	private Calendar fechaRegistro;
	private String fechaRegistroDia;
	private Set<BatchProduccion> batchProduccion = new HashSet<BatchProduccion>(0);
	
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
	public double getEspecificacion() {
		return especificacion;
	}
	
	public void setEspecificacion(double especificacion) {
		this.especificacion = especificacion;
	}
	
	@Column(name = "toleranciaMinima")
	public double getToleranciaMinima() {
		return toleranciaMinima;
	}
	
	public void setToleranciaMinima(double toleranciaMinima) {
		this.toleranciaMinima = toleranciaMinima;
	}
	
	@Column(name = "toleranciaMaxima")
	public double getToleranciaMaxima() {
		return toleranciaMaxima;
	}
	
	public void setToleranciaMaxima(double toleranciaMaxima) {
		this.toleranciaMaxima = toleranciaMaxima;
	}
	
	@Column(name = "totalBatchProcesados")
	public double getTotalBatchProcesados() {
		return totalBatchProcesados;
	}
	
	public void setTotalBatchProcesados(double totalBatchProcesados) {
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "registroProduccion")
	public Set<BatchProduccion> getBatchProduccion() {
		return batchProduccion;
	}

	public void setBatchProduccion(Set<BatchProduccion> batchProduccion) {
		this.batchProduccion = batchProduccion;
	}

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

	public String getIdFormula() {
		return idFormula;
	}

	public void setIdFormula(String idFormula) {
		this.idFormula = idFormula;
	}

	@Column (name = "fechaRegistroDia")
	public String getFechaRegistroDia() {
		return fechaRegistroDia;
	}

	public void setFechaRegistroDia(String fechaRegistroDia) {
		this.fechaRegistroDia = fechaRegistroDia;
	}
	
	
	
}