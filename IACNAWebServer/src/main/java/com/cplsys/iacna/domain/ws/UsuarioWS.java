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

@Entity
@Table
public class UsuarioWS {

	private Integer idUsuario;
	private String userName;
	private String password;

	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Calendar fechaModificacion;
	
	public final static int GERENTE_PRODUCCION = 1;
	public final static int SUPER_ADMINISTRADOR = 2;
	public final static int OPERADOR = 3;

	private List<PrivilegioWS> privilegios = new ArrayList<PrivilegioWS>();

	@Id
	@Column(name = "idUsuario")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Column(name = "userName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "apellidoPaterno")
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	@Column(name = "apellidoMaterno")
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
	public List<PrivilegioWS> getPrivilegios() {
		return privilegios;
	}

	public void setPrivilegios(List<PrivilegioWS> privilegios) {
		this.privilegios = privilegios;
	}

}