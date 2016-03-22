package com.cplsys.iacna.utils;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cplsys.iacna.domain.Bascula;
import com.cplsys.iacna.domain.BatchProduccion;
import com.cplsys.iacna.domain.Privilegio;
import com.cplsys.iacna.domain.RegistroProduccion;
import com.cplsys.iacna.domain.Turno;
import com.cplsys.iacna.domain.Usuario;
import com.cplsys.iacna.services.BasculaService;
import com.cplsys.iacna.services.BatchProduccionService;
import com.cplsys.iacna.services.PrivilegiosService;
import com.cplsys.iacna.services.RegistroProduccionService;
import com.cplsys.iacna.services.TurnoService;
import com.cplsys.iacna.services.UsuarioService;

@Component
public class DefaultDBValues {
	@Autowired
	BasculaService basculaService;
	@Autowired
	PrivilegiosService privilegiosService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private RegistroProduccionService produccionService;
	@Autowired
	private BatchProduccionService batchProduccionService;
	@Autowired
	private TurnoService turnoService;

	@PostConstruct
	public void init() {
		createBasculas();
		createUsuarios();
		createTurnos();	
	}

	private void createBasculas() {
		List<Bascula> basculas = basculaService.getAll();
		if (basculas.isEmpty()) {
			Bascula bascula = new Bascula();
			bascula.setDescripcion("Bascula 1");
			bascula.setNombre("1");
			bascula.setUnidadMedida("KG");
			basculaService.saveBascula(bascula);

			bascula = new Bascula();
			bascula.setDescripcion("Bascula 2");
			bascula.setNombre("2");
			bascula.setUnidadMedida("KG");
			basculaService.saveBascula(bascula);

			bascula = new Bascula();
			bascula.setDescripcion("Bascula 3");
			bascula.setNombre("3");
			bascula.setUnidadMedida("KG");
			basculaService.saveBascula(bascula);

			bascula = new Bascula();
			bascula.setDescripcion("Bascula 4");
			bascula.setNombre("4");
			bascula.setUnidadMedida("KG");
			bascula.setPrepesado(false);
			basculaService.saveBascula(bascula);

			bascula = new Bascula();
			bascula.setDescripcion("Bascula 5");
			bascula.setNombre("5");
			bascula.setUnidadMedida("KG");
			bascula.setPrepesado(true);
			basculaService.saveBascula(bascula);

			bascula = new Bascula();
			bascula.setDescripcion("Bascula 6");
			bascula.setNombre("6");
			bascula.setUnidadMedida("KG");
			bascula.setPrepesado(false);
			basculaService.saveBascula(bascula);

			bascula = new Bascula();
			bascula.setDescripcion("Bascula 7");
			bascula.setNombre("7");
			bascula.setUnidadMedida("KG");
			bascula.setPrepesado(false);
			basculaService.saveBascula(bascula);

			bascula = new Bascula();
			bascula.setDescripcion("Bascula 8");
			bascula.setNombre("8");
			bascula.setUnidadMedida("KG");
			bascula.setPrepesado(false);
			basculaService.saveBascula(bascula);

			bascula = new Bascula();
			bascula.setDescripcion("Bascula 9");
			bascula.setNombre("9");
			bascula.setUnidadMedida("KG");
			bascula.setPrepesado(false);
			basculaService.saveBascula(bascula);
		}

	}

	private void createUsuarios() {
		List<Usuario> usuarios = usuarioService.getAll();
		if (usuarios != null && usuarios.isEmpty()) {
			Usuario usuario = new Usuario();
			Privilegio privilegio = new Privilegio();

			usuario.setUserName("supervisor");
			usuario.setPassword("supervisor");
			usuario.setApellidoPaterno("Staufenberg");
			usuario.setNombre("Sebastian");
			usuarioService.saveUser(usuario);

			privilegio.setUsuario(usuario);
			privilegio.setProductionSupervisor(true);
			privilegiosService.save(privilegio);

			usuario = new Usuario();
			usuario.setUserName("admin");
			usuario.setPassword("admin");
			usuario.setApellidoPaterno("Rees");
			usuario.setNombre("John");
			usuarioService.saveUser(usuario);

			privilegio = new Privilegio();
			privilegio.setUsuario(usuario);
			privilegio.setSuperUser(true);
			privilegiosService.save(privilegio);
			
			usuario = new Usuario();
			usuario.setUserName("calidad");
			usuario.setPassword("calidad");
			usuario.setApellidoPaterno("Hall");
			usuario.setNombre("Carter");
			usuarioService.saveUser(usuario);
			
			privilegio = new Privilegio();
			privilegio.setUsuario(usuario);
			privilegio.setProductionCalidad(true);
			privilegiosService.save(privilegio);
			
			usuario = new Usuario();
			usuario.setUserName("head");
			usuario.setPassword("head");
			usuario.setApellidoPaterno("Jhon");
			usuario.setNombre("Test");
			usuarioService.saveUser(usuario);
			
			privilegio = new Privilegio();
			privilegio.setUsuario(usuario);
			privilegio.setProductionHeads(true);
			privilegiosService.save(privilegio);
			
		}

	}
	
	private void createRegistrosProduccion() {
		List<RegistroProduccion> list = produccionService.getAll(3);
		if (list == null || list.isEmpty()) {
			RegistroProduccion produccion = new RegistroProduccion();
			BatchProduccion batchProduccion = null;
			
			produccion.setBascula("2");
			produccion.setMp("MP 200 128");
			produccion.setDescripcion("Granul. GMT 90");
			produccion.setEspecificacion(155.0);
			produccion.setToleranciaMinima(153);
			produccion.setToleranciaMaxima(157);
			produccion.setTurno("Primero");
			produccionService.saveRegistroProduccion(produccion);
			 
			
			for (int i = 0; i < 5; i++) {
				batchProduccion = new BatchProduccion();
				batchProduccion.setPesoObtenido((int) Math.round((Math.random()*10)));
				batchProduccion.setRegistroProduccion(produccion);
				batchProduccionService.saveBascula(batchProduccion);
				
				
			}
			
			produccion = new RegistroProduccion();
			produccion.setBascula("3");
			produccion.setMp("MP 200 232");
			produccion.setDescripcion("PVC IACNA");
			produccion.setEspecificacion(60.0);
			produccion.setToleranciaMinima(59);
			produccion.setToleranciaMaxima(61);
			produccion.setTurno("Segundo");
			produccionService.saveRegistroProduccion(produccion);
			
			for (int i = 0; i < 10; i++) {
				batchProduccion = new BatchProduccion();
				batchProduccion.setPesoObtenido((int) Math.round((Math.random()*10)));
				batchProduccion.setRegistroProduccion(produccion);
				batchProduccionService.saveBascula(batchProduccion);
			}		
			
			produccion = new RegistroProduccion();
			produccion.setBascula("3");
			produccion.setMp("MP 100 314");
			produccion.setDescripcion("Cryogenics");
			produccion.setEspecificacion(20.0);
			produccion.setToleranciaMinima(19);
			produccion.setToleranciaMaxima(21);
			produccion.setTurno("Tercero");
			produccionService.saveRegistroProduccion(produccion);
			
			for (int i = 0; i < 2; i++) {
				batchProduccion = new BatchProduccion();
				batchProduccion.setPesoObtenido((int) Math.round((Math.random()*10)));
				batchProduccion.setRegistroProduccion(produccion);
				batchProduccionService.saveBascula(batchProduccion);
			}
		}
	}

	private void createTurnos() {
		List<Turno> turnos = turnoService.getAll();
		if (turnos.isEmpty()) {
			Turno turno = new Turno();
			
			turno.setDescripcion(Turno.PRIMER_TURNO);
			turno.setHorario("07:00:00 - 16:30:00");
			turno.setNombre(Turno.PRIMER_TURNO);
			turnoService.saveTurno(turno);
			
			turno = new Turno();
			turno.setDescripcion(Turno.SEGUNDO_TURNO);
			turno.setHorario("16:30:00 - 22:30:00");
			turno.setNombre(Turno.SEGUNDO_TURNO);
			turnoService.saveTurno(turno);
			
			turno = new Turno();
			turno.setDescripcion(Turno.TERCER_TURNO);
			turno.setHorario("22:30:00 - 07:00:00");
			turno.setNombre(Turno.TERCER_TURNO);
			turnoService.saveTurno(turno);			
		}
	}
}
