package com.cplsys.iacna.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Table
@Entity
public class BatchProduccion {

	private Integer idBatchProduccion;
	private double pesoObtenido;
	private Calendar fechaRegistro;
	private RegistroProduccion registroProduccion;
	private String addressMemory;
	
	@Id	
	@Column(name = "idBatchProduccion")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getIdBatchProduccion() {
		return idBatchProduccion;
	}
	
	public void setIdBatchProduccion(Integer idBatchProduccion) {
		this.idBatchProduccion = idBatchProduccion;
	}
	
	@Column
	public double getPesoObtenido() {
		return pesoObtenido;
	}
	
	public void setPesoObtenido(double pesoObtenido) {
		this.pesoObtenido = pesoObtenido;
	}
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@Version
	public Calendar getFechaRegistro() {
		return fechaRegistro;
	}
	
	public void setFechaRegistro(Calendar fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idRegistroProduccion")
	public RegistroProduccion getRegistroProduccion() {
		return registroProduccion;
	}
	
	public void setRegistroProduccion(RegistroProduccion registroProduccion) {
		this.registroProduccion = registroProduccion;
	}
	
	@Column
	public String getAddressMemory() {
		return addressMemory;
	}
	
	public void setAddressMemory(String addressMemory) {
		this.addressMemory = addressMemory;
	}
}
