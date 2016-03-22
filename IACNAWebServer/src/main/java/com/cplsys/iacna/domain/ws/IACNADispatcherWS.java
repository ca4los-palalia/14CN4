package com.cplsys.iacna.domain.ws;

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
public class IACNADispatcherWS implements Serializable {

	private static final long serialVersionUID = 4416294258351232746L;
	
	private Integer idAsignacion;
	private FormulaWS formula;
	private Calendar fechaRegistro;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getIdAsignacion() {
		return idAsignacion;
	}

	public void setIdAsignacion(Integer idAsignacion) {
		this.idAsignacion = idAsignacion;
	}	
	

	@Temporal(TemporalType.TIMESTAMP)
	@Version
	@Column(name = "fechaRegistro")
	public Calendar getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Calendar fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idFormula")
	public FormulaWS getFormula() {
		return formula;
	}

	public void setFormula(FormulaWS formula) {
		this.formula = formula;
	}

}