package com.cplsys.iacna.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table
public class Producto implements Serializable {

	private static final long serialVersionUID = 4416294258351232746L;
	private String nombre;
	private Integer idProducto;
	private List<Formula> formulas = new ArrayList<Formula>();
	private Calendar fechaModificacion;

	public Producto() {

	}

	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idProducto")
	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
/*
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "producto")
	public Set<Formula> getFormulas() {
		return formulas;
	}

	public void setFormulas(Set<Formula> formulas) {
		this.formulas = formulas;
	}*/

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
