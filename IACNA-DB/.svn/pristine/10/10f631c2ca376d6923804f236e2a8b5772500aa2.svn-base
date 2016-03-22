package com.cplsys.iacna.domain;

import java.io.Serializable;
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

@Entity
@Table
public class Ingrediente implements Serializable {

	private static final long serialVersionUID = 5931413280136808999L;

	private Integer idIngrediente;
	private String nombre;
	private String descripcion;
	private Formula formula;
	private String formulaIdForWS;
	private String producto;
	private String bascula;
	private String unidadMedida;
	private String mp;
	private double especificacion;
	private double toleranciaMinima;
	private double toleranciaMaxima;
	private boolean prepesadoBascula;
	private Calendar fechaRegistro;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idIngrediente")
	public Integer getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(Integer idIngrediente) {
		this.idIngrediente = idIngrediente;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idFormula")
	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	@Column(name = "producto")
	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	@Column(name = "bascula")
	public String getBascula() {
		return bascula;
	}

	public void setBascula(String bascula) {
		this.bascula = bascula;
	}

	@Column(name = "unidadMedida")
	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	@Column(name = "mp")
	public String getMp() {
		return mp;
	}

	public void setMp(String mp) {
		this.mp = mp;
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
	

	@Column(name = "prepesadoBascula")
	public boolean getPrepesadoBascula() {
		return prepesadoBascula;
	}

	
	public void setPrepesadoBascula(boolean prepesadoBascula) {
		this.prepesadoBascula = prepesadoBascula;
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

	@Column(name = "formulaIdForWS")
	public String getFormulaIdForWS() {
		return formulaIdForWS;
	}

	public void setFormulaIdForWS(String formulaIdForWS) {
		this.formulaIdForWS = formulaIdForWS;
	}

}
