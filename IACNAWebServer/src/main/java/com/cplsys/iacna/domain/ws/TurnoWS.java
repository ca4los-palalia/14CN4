package com.cplsys.iacna.domain.ws;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
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
public class TurnoWS implements Serializable {

	private static final long serialVersionUID = 5931413280136808999L;

	private Integer idTurno;
	private String nombre;
	private String descripcion;
	private String horario;
	private Calendar fechaModificacion;
	private boolean turnoHabilitado;

	public TurnoWS() {

	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idTurno", nullable = false)
	public Integer getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(Integer idTurno) {
		this.idTurno = idTurno;
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

	@Column(name = "horario")
	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
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

	@Basic
	@Column(name = "turnoHabilitado")
	public boolean isTurnoHabilitado() {
		return turnoHabilitado;
	}

	public void setTurnoHabilitado(boolean turnoHabilitado) {
		this.turnoHabilitado = turnoHabilitado;
	}

}
