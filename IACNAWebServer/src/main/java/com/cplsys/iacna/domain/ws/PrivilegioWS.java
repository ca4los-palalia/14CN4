package com.cplsys.iacna.domain.ws;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
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
public class PrivilegioWS implements Serializable {

	private static final long serialVersionUID = 9129578328242917126L;
	private Integer idPrivilegios;
	private boolean productionSupervisor;
	private boolean productionOperator;
	private boolean superUser;
	private UsuarioWS usuario;
	private Calendar fechaModificacion;

	@Id
	@Column(name = "idPrivilegio")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getIdPrivilegios() {
		return idPrivilegios;
	}

	public void setIdPrivilegios(Integer idPrivilegios) {
		this.idPrivilegios = idPrivilegios;
	}

	@Basic
	@Column
	public boolean isProductionSupervisor() {
		return productionSupervisor;
	}

	public void setProductionSupervisor(boolean productionSupervisor) {
		this.productionSupervisor = productionSupervisor;
	}

	@Basic
	@Column
	public boolean isProductionOperator() {
		return productionOperator;
	}

	public void setProductionOperator(boolean productionOperator) {
		this.productionOperator = productionOperator;
	}

	@Basic
	@Column
	public boolean isSuperUser() {
		return superUser;
	}

	public void setSuperUser(boolean superUser) {
		this.superUser = superUser;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idUsuario")
	public UsuarioWS getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioWS usuario) {
		this.usuario = usuario;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Version
	@Column
	public Calendar getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Calendar fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

}