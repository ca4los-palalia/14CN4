package com.cplsys.iacna.ws.skeleton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cplsys.iacna.domain.Bascula;
import com.cplsys.iacna.domain.BatchProduccion;
import com.cplsys.iacna.domain.Formula;
import com.cplsys.iacna.domain.IACNADispatcher;
import com.cplsys.iacna.domain.Ingrediente;
import com.cplsys.iacna.domain.RegistroProduccion;
import com.cplsys.iacna.util.HibernateUtil;
import com.cplsys.iacna.ws.skeleton.iface.IACNAIface;

public class WSiacna implements IACNAIface {

	private Session session;
	private Transaction transaction;
	private List<Ingrediente> despachadorIngredientes;
	private final static String FORMATO_FECHA_BUSQUEDA_DIA = "yyyy-MM-dd";

	@PostConstruct
	public void init() {
		initObjects();
	}

	private void initObjects() {
		despachadorIngredientes = new ArrayList<Ingrediente>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void habilitarBascula(int bascula) {
		try {
			int basculas = Integer.parseInt(String.valueOf(bascula));
			if (basculas > 0 && basculas < 10) {
				session = HibernateUtil.getSession();
				List<Bascula> basculaDB = session
						.createQuery("from Bascula as b where b.nombre = ?")
						.setString(0, String.valueOf(basculas)).list();
				if (basculaDB != null && !basculaDB.isEmpty()) {
					transaction = session.beginTransaction();
					Bascula basculaToPerformChange = basculaDB.get(0);
					basculaToPerformChange.setBasculaAsignada(true);
					session.update(basculaToPerformChange);
					transaction.commit();
					HibernateUtil.shutDown();
				}
			}
		} catch (Exception e) {

		}
	}

	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public void deshabilitarBascula(int bascula) {
		try {
			int basculas = Integer.parseInt(String.valueOf(bascula));
			if (bascula > 0 && bascula < 10) {
				session = HibernateUtil.getSession();
				List<Bascula> basculaDB = session
						.createQuery("from Bascula as b where b.nombre = ?")
						.setString(0, String.valueOf(bascula)).list();
				if (basculaDB != null && !basculaDB.isEmpty()) {
					transaction = session.beginTransaction();
					Bascula basculaToPerformChange = basculaDB.get(0);
					basculaToPerformChange.setBasculaAsignada(false);
					session.update(basculaToPerformChange);
					transaction.commit();
					HibernateUtil.shutDown();
				}
			}
		} catch (Exception e) {

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getTotalIngredientesFormula(String formula) {

		try {
			session = HibernateUtil.getSession();
			List<Formula> formulas = session
					.createQuery("from Formula as f where f.idFormula = ?")
					.setInteger(0, Integer.parseInt(formula)).list();
			if (formulas != null && !formulas.isEmpty()) {
				Formula formulaFromDB = formulas.get(0);
				if (!formulaFromDB.isCancelada()
						&& !formulaFromDB.isDespachada()) {
					List<Ingrediente> ingredientes = session
							.createQuery(
									"from Ingrediente as i where i.formulaIdForWS = ?")
							.setString(0, formula).list();
					HibernateUtil.shutDown();
					if (despachadorIngredientes == null) {
						despachadorIngredientes = new ArrayList<Ingrediente>();
					} else {
						despachadorIngredientes.clear();
					}
					for (Ingrediente ingrediente : ingredientes) {
						despachadorIngredientes.add(ingrediente);
					}
					return ingredientes != null && !ingredientes.isEmpty() ? ingredientes
							.size() : 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean saveBatchsRegistroProduccion(int idRegistroProduccion,
			double pesoObtenido, String addressMemory) {
		
		boolean centinela = false;
		List<RegistroProduccion> rProduccion = session
				.createQuery(
						"from RegistroProduccion as r "
								+ "where r.idRegistroProduccion = ?")
				.setInteger(0, idRegistroProduccion).list();
		
			RegistroProduccion registroProduccion = rProduccion.get(0);
			
			
			BatchProduccion batchProduccion = new BatchProduccion();
			batchProduccion.setPesoObtenido(pesoObtenido);
			batchProduccion.setRegistroProduccion(registroProduccion);
			batchProduccion.setAddressMemory(addressMemory);
			transaction = session.beginTransaction();
			session.saveOrUpdate(batchProduccion);
			transaction.commit();
			HibernateUtil.shutDown();
			centinela = true;
		
		
		/*
		if (!String.valueOf(pesoObtenido).equals("0.0")) {

			session = HibernateUtil.getSession();
			List<BatchProduccion> bPro = session
					.createQuery(
							"from BatchProduccion as b where b.registroProduccion = ? and b.pesoObtenido = ? and b.addressMemory = ?")
					.setInteger(0, idRegistroProduccion)
					.setDouble(1, pesoObtenido).setString(2, addressMemory)
					.list();

			if (!bPro.isEmpty()) {
				session = HibernateUtil.getSession();

			} else {
				List<RegistroProduccion> rProduccion = session
						.createQuery(
								"from RegistroProduccion as r "
										+ "where r.idRegistroProduccion = ?")
						.setInteger(0, idRegistroProduccion).list();
				if (rProduccion != null && !rProduccion.isEmpty()) {
					RegistroProduccion registroProduccion = rProduccion.get(0);
					BatchProduccion batchProduccion = new BatchProduccion();
					batchProduccion.setPesoObtenido(pesoObtenido);
					batchProduccion.setRegistroProduccion(registroProduccion);
					batchProduccion.setAddressMemory(addressMemory);
					transaction = session.beginTransaction();
					session.saveOrUpdate(batchProduccion);
					transaction.commit();
					HibernateUtil.shutDown();
					centinela = true;
				} else {
					HibernateUtil.shutDown();
				}
			}
		}*/
		return centinela;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getBasculaNombre(int bascula) {
		session = HibernateUtil.getSession();
		List<Bascula> basculaDB = session
				.createQuery("from Bascula as b where b.nombre = ?")
				.setString(0, String.valueOf(bascula)).list();
		if (basculaDB != null && !basculaDB.isEmpty()) {
			Bascula bas = basculaDB.get(0);
			HibernateUtil.shutDown();
			return bas.getNombre();
		}
		HibernateUtil.shutDown();
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isBasculaHabilitada(int bascula) {
		session = HibernateUtil.getSession();
		List<Bascula> basculaDB = session
				.createQuery("from Bascula as b where b.nombre = ?")
				.setString(0, String.valueOf(bascula)).list();
		if (basculaDB != null && !basculaDB.isEmpty()) {
			Bascula bas = basculaDB.get(0);
			HibernateUtil.shutDown();
			return bas.isBasculaAsignada();
		}
		HibernateUtil.shutDown();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isBasculaModoManual(int bascula) {
		session = HibernateUtil.getSession();
		List<Bascula> basculaDB = session
				.createQuery("from Bascula as b where b.nombre = ?")
				.setString(0, String.valueOf(bascula)).list();
		if (basculaDB != null && !basculaDB.isEmpty()) {
			Bascula bas = basculaDB.get(0);
			HibernateUtil.shutDown();
			return bas.isModoManual();
		}
		HibernateUtil.shutDown();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isBasculaPrepesado(int bascula) {
		session = HibernateUtil.getSession();
		List<Bascula> basculaDB = session
				.createQuery("from Bascula as b where b.nombre = ?")
				.setString(0, String.valueOf(bascula)).list();
		if (basculaDB != null && !basculaDB.isEmpty()) {
			Bascula bas = basculaDB.get(0);
			HibernateUtil.shutDown();
			return bas.isPrepesado();
		}
		HibernateUtil.shutDown();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getDescripcionBascula(int bascula) {
		session = HibernateUtil.getSession();
		List<Bascula> basculaDB = session
				.createQuery("from Bascula as b where b.nombre = ?")
				.setString(0, String.valueOf(bascula)).list();
		if (basculaDB != null && !basculaDB.isEmpty()) {
			Bascula bas = basculaDB.get(0);
			HibernateUtil.shutDown();
			return bas.getDescripcion();
		}
		HibernateUtil.shutDown();
		return "";
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setDescripcionBascula(int bascula, String descripcion) {
		try {
			int basculas = Integer.parseInt(String.valueOf(bascula));
			if (basculas > 0 && basculas < 10) {
				session = HibernateUtil.getSession();
				List<Bascula> basculaDB = session
						.createQuery("from Bascula as b where b.nombre = ?")
						.setString(0, String.valueOf(basculas)).list();
				if (basculaDB != null && !basculaDB.isEmpty()) {
					transaction = session.beginTransaction();
					Bascula basculaToPerformChange = basculaDB.get(0);
					basculaToPerformChange.setDescripcion(descripcion);
					session.update(basculaToPerformChange);
					transaction.commit();
					HibernateUtil.shutDown();
				}
			}
		} catch (Exception e) {

		}
	}

	/*
	 * METODO OBSOLETO POR PERFORMANCE
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public String getTurnoFormula() { session =
	 * HibernateUtil.getSession(); List<IACNADispatcher> dispatcher =
	 * session.createQuery( "from IACNADispatcher").list(); if (dispatcher !=
	 * null) { for (IACNADispatcher iacnaDispatcher : dispatcher) { if
	 * (!iacnaDispatcher.getFormula().isCancelada() &&
	 * !iacnaDispatcher.getFormula().isDespachada()) { HibernateUtil.shutDown();
	 * return iacnaDispatcher.getFormula().getTurno(); } } }
	 * HibernateUtil.shutDown(); return null; }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public String getTurnoFormula() {
		session = HibernateUtil.getSession();
		List<Formula> formulas = session
				.createQuery(
						"from Formula as f where f.cancelada = ? and f.despachada = ? ")
				.setBoolean(0, false).setBoolean(1, false).list();

		if (formulas != null && !formulas.isEmpty()) {
			Formula formula = formulas.get(0);
			HibernateUtil.shutDown();
			return formula.getTurno();
		}
		HibernateUtil.shutDown();
		return null;
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public boolean isFormulaCancelada(String idFormula) {
		Thread thread = new Thread();
		try {
			thread.sleep(200);
			session = HibernateUtil.getSession();
			List<Formula> formula = session
					.createQuery("from Formula as f where f.idFormula = ?")
					.setInteger(0, Integer.parseInt(idFormula)).list();
			if (formula.size() > 0) {
				thread.sleep(200);
				Formula form = formula.get(0);
				return form.isCancelada();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/*
	 * OBSOLETO POR PERFORMANCE
	 * 
	 * @SuppressWarnings({ "static-access", "unchecked" })
	 * 
	 * @Override public int getBatchsFormula(String idFormula) { try { Thread
	 * thread = new Thread(); thread.sleep(200); session =
	 * HibernateUtil.getSession(); List<IACNADispatcher> dispatcher =
	 * session.createQuery( "from IACNADispatcher").list(); if (dispatcher !=
	 * null) { for (IACNADispatcher iacnaDispatcher : dispatcher) {
	 * 
	 * // --------------- thread.sleep(200); List<Formula> formula = session
	 * .createQuery(
	 * "from Formula as f where f.idFormula = ? and f.cancelada = ? and despachada = ?"
	 * ) .setLong(0, iacnaDispatcher.getFormula().getIdFormula()) .setBoolean(1,
	 * false).setBoolean(2, false).list();
	 * 
	 * if (formula.size() != 0) { HibernateUtil.shutDown(); thread.sleep(200);
	 * return formula.get(0).getTotalBatchAProcesar(); } } }
	 * HibernateUtil.shutDown(); } catch (Exception e) { return 0; } return 0; }
	 */

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public int getBatchsFormula(String idFormula) {
		try {
			Thread thread = new Thread();
			thread.sleep(200);
			session = HibernateUtil.getSession();

			List<Formula> formulas = session
					.createQuery(
							"from Formula as f where f.cancelada = ? and f.despachada = ? ")
					.setBoolean(0, false).setBoolean(1, false).list();

			if (formulas != null && formulas.size() > 0) {
				Formula formula = formulas.get(0);
				HibernateUtil.shutDown();
				thread.sleep(200);
				return formula.getTotalBatchAProcesar();
			}

			HibernateUtil.shutDown();
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}

	/*
	 * // VIEJO FUNCIONAMIENTO 17/11/2013
	 * 
	 * @SuppressWarnings({ "unchecked", "static-access" })
	 * 
	 * @Override public String getIdFormula() { Thread thread = new Thread();
	 * String id = ""; try { thread.sleep(200); session =
	 * HibernateUtil.getSession();
	 * 
	 * //nuevo funcionamiento List<Formula> formulas = session.createQuery(
	 * "from Formula as f where f.cancelada = ? and despachada = ? "
	 * ).setBoolean(0, false).setBoolean(1, false).list(); formulas.size();
	 * ///--- FUNCIONAMIENTO ANTERIOR List<IACNADispatcher> dispatcher =
	 * session.createQuery( "from IACNADispatcher").list(); if (dispatcher !=
	 * null) { for (IACNADispatcher iacnaDispatcher : dispatcher) {
	 * 
	 * // --------------- thread.sleep(200); List<Formula> formula = session
	 * .createQuery(
	 * "from Formula as f where f.idFormula = ? and f.cancelada = ? and despachada = ?"
	 * ) .setLong(0, iacnaDispatcher.getFormula().getIdFormula()) .setBoolean(1,
	 * false).setBoolean(2, false).list();
	 * 
	 * if (formula.size() != 0) { HibernateUtil.shutDown(); thread.sleep(200);
	 * id = String.valueOf(formula.get(0).getIdFormula()); break; } } }
	 * HibernateUtil.shutDown(); } catch (Exception e) { e.printStackTrace(); }
	 * return id; }
	 */

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String getIdFormula() {
		Thread thread = new Thread();
		String id = "";
		try {
			thread.sleep(200);
			session = HibernateUtil.getSession();

			List<Formula> formulas = session
					.createQuery(
							"from Formula as f where f.cancelada = ? and f.despachada = ? ")
					.setBoolean(0, false).setBoolean(1, false).list();

			if (formulas != null && formulas.size() > 0) {
				for (Formula formula : formulas) {
					HibernateUtil.shutDown();
					thread.sleep(200);
					id = String.valueOf(formula.getIdFormula());
					break;
				}
			}
			HibernateUtil.shutDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateBatch(int batchActual, String idFormula) {
		try {
			session = HibernateUtil.getSession();
			List<Formula> formula = session
					.createQuery("from Formula as f where f.idFormula = ?")
					.setInteger(0, Integer.parseInt(idFormula)).list();

			if (formula != null) {
				transaction = session.beginTransaction();
				formula.get(0).setBatchProcesado(batchActual);
				transaction.commit();
				HibernateUtil.shutDown();
			} else {
				HibernateUtil.shutDown();
			}
			if (idFormula.equals("-1")) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public String getNombreIngrediente(int ingrediente, String idFormula) {
		session = HibernateUtil.getSession();
		List<Formula> formulas = session
				.createQuery("from Formula as f where f.idFormula = ?")
				.setInteger(0, Integer.parseInt(idFormula)).list();

		if (formulas != null && formulas.size() > 0) {
			Formula formulaFromDB = formulas.get(0);
			if (!formulaFromDB.isCancelada() && !formulaFromDB.isDespachada()) {
				List<Ingrediente> ingredientes = session
						.createQuery(
								"from Ingrediente as i where i.formulaIdForWS = ?")
						.setString(0,
								String.valueOf(formulaFromDB.getIdFormula()))
						.list();
				HibernateUtil.shutDown();
				if (ingrediente < ingredientes.size()) {
					return ingredientes.get(ingrediente).getNombre();
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getBasculaIngrediente(int ingrediente, String idFormula) {
		try {
			session = HibernateUtil.getSession();
			List<Formula> formulas = session
					.createQuery("from Formula as f where f.idFormula = ?")
					.setInteger(0, Integer.parseInt(idFormula)).list();

			if (formulas != null && formulas.size() > 0) {
				Formula formulaFromDB = formulas.get(0);
				if (!formulaFromDB.isCancelada()
						&& !formulaFromDB.isDespachada()) {
					List<Ingrediente> ingredientes = session
							.createQuery(
									"from Ingrediente as i where i.formulaIdForWS = ?")
							.setString(
									0,
									String.valueOf(formulaFromDB.getIdFormula()))
							.list();
					HibernateUtil.shutDown();

					if (ingrediente < ingredientes.size()) {
						return Integer.parseInt(ingredientes.get(ingrediente)
								.getBascula());
					}
				}
			}
		} catch (Exception e) {
			return 0;
		}

		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public double getEspecificacionIngrediente(int ingrediente, String idFormula) {
		double especificacion = 0;
		session = HibernateUtil.getSession();
		List<Formula> formulas = session
				.createQuery("from Formula as f where f.idFormula = ?")
				.setInteger(0, Integer.parseInt(idFormula)).list();

		if (formulas != null && formulas.size() > 0) {
			Formula formulaFromDB = formulas.get(0);
			if (!formulaFromDB.isCancelada() && !formulaFromDB.isDespachada()) {
				List<Ingrediente> ingredientes = session
						.createQuery(
								"from Ingrediente as i where i.formulaIdForWS = ?")
						.setString(0,
								String.valueOf(formulaFromDB.getIdFormula()))
						.list();
				HibernateUtil.shutDown();
				if (ingrediente < ingredientes.size()) {
					especificacion = ingredientes.get(ingrediente)
							.getEspecificacion();
				}
			}
		}
		return especificacion;
	}

	@SuppressWarnings("unchecked")
	@Override
	public double getToleranciaMinima(int ingrediente, String idFormula) {
		double minimo = 0;
		session = HibernateUtil.getSession();
		List<Formula> formulas = session
				.createQuery("from Formula as f where f.idFormula = ?")
				.setInteger(0, Integer.parseInt(idFormula)).list();

		if (formulas != null && formulas.size() > 0) {
			Formula formulaFromDB = formulas.get(0);
			if (!formulaFromDB.isCancelada() && !formulaFromDB.isDespachada()) {
				List<Ingrediente> ingredientes = session
						.createQuery(
								"from Ingrediente as i where i.formulaIdForWS = ?")
						.setString(0,
								String.valueOf(formulaFromDB.getIdFormula()))
						.list();
				HibernateUtil.shutDown();
				if (ingrediente < ingredientes.size()) {
					minimo = ingredientes.get(ingrediente)
							.getToleranciaMinima();
				}
			}
		}
		return minimo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public double getToleranciaMaxima(int ingrediente, String idFormula) {
		double maximo = 0;
		session = HibernateUtil.getSession();
		List<Formula> formulas = session
				.createQuery("from Formula as f where f.idFormula = ?")
				.setInteger(0, Integer.parseInt(idFormula)).list();

		if (formulas != null && formulas.size() > 0) {
			Formula formulaFromDB = formulas.get(0);
			if (!formulaFromDB.isCancelada() && !formulaFromDB.isDespachada()) {
				List<Ingrediente> ingredientes = session
						.createQuery(
								"from Ingrediente as i where i.formulaIdForWS = ?")
						.setString(0,
								String.valueOf(formulaFromDB.getIdFormula()))
						.list();
				HibernateUtil.shutDown();
				if (ingrediente < ingredientes.size()) {
					maximo = ingredientes.get(ingrediente)
							.getToleranciaMaxima();
				}
			}
		}
		return maximo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isIngredienteAsignadoAPrepesado(int ingrediente,
			String idFormula) {
		session = HibernateUtil.getSession();
		List<Formula> formulas = session
				.createQuery("from Formula as f where f.idFormula = ?")
				.setInteger(0, Integer.parseInt(idFormula)).list();

		if (formulas != null && formulas.size() > 0) {
			Formula formulaFromDB = formulas.get(0);
			if (!formulaFromDB.isCancelada() && !formulaFromDB.isDespachada()) {
				List<Ingrediente> ingredientes = session
						.createQuery(
								"from Ingrediente as i where i.formulaIdForWS = ?")
						.setString(0,
								String.valueOf(formulaFromDB.getIdFormula()))
						.list();
				HibernateUtil.shutDown();
				if (ingrediente < ingredientes.size()) {
					return ingredientes.get(ingrediente).getPrepesadoBascula();
				}
			}
		}
		return false;
	}

	@Override
	public int guardarRegistroProduccion(String nombreFormula,
			String basculaAsignada, String mp, String descripcion,
			double especificacionPeso, double toleranciaMinima,
			double toleranciaMaxima, int sumatoriaBatchsPesosObtenidos,
			String turno, double totalBatchProcesados, String idFormula) {

		session = HibernateUtil.getSession();

		// ---------------------

		// ---------------------

		SimpleDateFormat format = new SimpleDateFormat(
				FORMATO_FECHA_BUSQUEDA_DIA);

		RegistroProduccion registroProduccion = new RegistroProduccion();

		registroProduccion.setFormula(nombreFormula);
		registroProduccion.setBascula(basculaAsignada);
		registroProduccion.setDescripcion(descripcion);
		registroProduccion.setEspecificacion((double) especificacionPeso);

		registroProduccion.setMp(mp);
		registroProduccion
				.setSumatoriaBatchPesosObtenidos(sumatoriaBatchsPesosObtenidos);
		registroProduccion.setToleranciaMaxima(toleranciaMaxima);
		registroProduccion.setToleranciaMinima(toleranciaMinima);
		registroProduccion.setTurno(turno);
		registroProduccion.setTotalBatchProcesados(totalBatchProcesados);
		registroProduccion.setFechaRegistroDia(format.format(Calendar
				.getInstance().getTime()));
		registroProduccion.setIdFormula(idFormula);

		transaction = session.beginTransaction();
		session.save(registroProduccion);
		transaction.commit();

		HibernateUtil.shutDown();

		return registroProduccion.getIdRegistroProduccion();
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String getNombreFormula(String idFormula) {

		Thread thread = new Thread();
		try {
			thread.sleep(200);
			session = HibernateUtil.getSession();
			List<Formula> formula = session
					.createQuery("from Formula as f where f.idFormula = ?")
					.setInteger(0, Integer.parseInt(idFormula)).list();
			if (formula != null && formula.size() > 0) {
				thread.sleep(200);
				Formula form = formula.get(0);
				return form.getNombre();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public boolean isFormulaDespachada(String idFormula) {
		Thread thread = new Thread();
		try {
			thread.sleep(200);
			session = HibernateUtil.getSession();
			List<Formula> formula = session
					.createQuery("from Formula as f where f.idFormula = ?")
					.setInteger(0, Integer.parseInt(idFormula)).list();
			if (formula != null && formula.size() > 0) {
				thread.sleep(200);
				Formula form = formula.get(0);
				return form.isDespachada();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getMpIngrediente(int ingrediente, String idFormula) {

		session = HibernateUtil.getSession();
		List<Formula> formulas = session
				.createQuery("from Formula as f where f.idFormula = ?")
				.setInteger(0, Integer.parseInt(idFormula)).list();

		if (formulas != null && formulas.size() > 0) {
			Formula formulaFromDB = formulas.get(0);
			if (!formulaFromDB.isCancelada() && !formulaFromDB.isDespachada()) {
				List<Ingrediente> ingredientes = session
						.createQuery(
								"from Ingrediente as i where i.formulaIdForWS = ?")
						.setString(0,
								String.valueOf(formulaFromDB.getIdFormula()))
						.list();
				HibernateUtil.shutDown();
				if (ingrediente < ingredientes.size()) {
					return ingredientes.get(ingrediente).getMp();
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getTotalIngredientesPorBascula(String idFormula, String bascula) {
		try {
			session = HibernateUtil.getSession();
			List<Formula> formulas = session
					.createQuery("from Formula as f where f.idFormula = ?")
					.setInteger(0, Integer.parseInt(idFormula)).list();

			if (formulas != null && formulas.size() > 0) {
				Formula formulaFromDB = formulas.get(0);
				if (!formulaFromDB.isCancelada()
						&& !formulaFromDB.isDespachada()) {
					List<Ingrediente> ingredientes = session
							.createQuery(
									"From Ingrediente as i where i.formulaIdForWS = ? and i.bascula = ?")
							.setString(
									0,
									String.valueOf(formulaFromDB.getIdFormula()))
							.setString(1, bascula).list();
					HibernateUtil.shutDown();

					if (ingredientes != null && !ingredientes.isEmpty()) {
						return ingredientes.size();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setModoManualBascula(int bascula) {
		try {
			int basculas = Integer.parseInt(String.valueOf(bascula));
			if (basculas > 0 && basculas < 10) {
				session = HibernateUtil.getSession();
				List<Bascula> basculaDB = session
						.createQuery("from Bascula as b where b.nombre = ?")
						.setString(0, String.valueOf(basculas)).list();
				if (basculaDB != null && basculaDB.size() > 0) {
					transaction = session.beginTransaction();
					Bascula basculaToPerformChange = basculaDB.get(0);
					basculaToPerformChange.setModoManual(true);
					session.update(basculaToPerformChange);
					transaction.commit();
					HibernateUtil.shutDown();
				}
			}
		} catch (Exception e) {

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setFormulaDespachada(String idFormula) {

		try {
			session = HibernateUtil.getSession();
			List<Formula> formula = session
					.createQuery("from Formula as f where f.idFormula = ?")
					.setInteger(0, Integer.parseInt(idFormula)).list();
			if (formula != null && formula.size() > 0) {
				transaction = session.beginTransaction();
				Formula form = formula.get(0);
				form.setDespachada(true);
				session.update(form);
				transaction.commit();
				HibernateUtil.shutDown();
			}
		} catch (NumberFormatException e) {

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getIdRegistroProduccion(int ingrediente, String idFormula) {

		session = HibernateUtil.getSession();
		List<Formula> formulas = session
				.createQuery("from Formula as f where f.idFormula = ?")
				.setInteger(0, Integer.parseInt(idFormula)).list();

		if (formulas != null && formulas.size() > 0) {
			Formula formulaFromDB = formulas.get(0);
			if (!formulaFromDB.isCancelada() && !formulaFromDB.isDespachada()) {
				List<RegistroProduccion> registroProduccion = session
						.createQuery(
								"from RegistroProduccion as i where i.idFormula = ? order by bascula, idRegistroProduccion")
						.setString(0,
								String.valueOf(formulaFromDB.getIdFormula()))
						.list();
				HibernateUtil.shutDown();
				if (ingrediente < registroProduccion.size()) {
					return registroProduccion.get(ingrediente)
							.getIdRegistroProduccion();
				}
			}
		}
		return 0;

	}

	@SuppressWarnings("unchecked")
	@Override
	public double getTotalBachtProcesados(String idFormula, String bascula,
			String descripcion) {

		double valor = 0;
		session = HibernateUtil.getSession();
		List<Formula> formulas = session
				.createQuery("from Formula as f where f.idFormula = ?")
				.setInteger(0, Integer.parseInt(idFormula)).list();

		if (formulas != null && formulas.size() > 0) {
			Formula formulaFromDB = formulas.get(0);
			if (!formulaFromDB.isCancelada() && !formulaFromDB.isDespachada()) {
				List<RegistroProduccion> registroProduccion = session
						.createQuery(
								"From RegistroProduccion as i where i.idFormula = ? and i.bascula = ? and i.descripcion = ?")
						.setString(0,
								String.valueOf(formulaFromDB.getIdFormula()))
						.setString(1, bascula).setString(2, descripcion).list();
				HibernateUtil.shutDown();
				if (registroProduccion.size() == 0) {
					valor = 0;
				} else {
					valor = registroProduccion.get(0).getTotalBatchProcesados();
				}
			}
		}
		return valor;
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public void updateTotalBatchProcesados(String idFormula, String bascula,
			String descripcion, double nuevoBatch) {
		Thread thread = new Thread();
		try {
			thread.sleep(200);
			session = HibernateUtil.getSession();
			List<RegistroProduccion> registroproduccion = session
					.createQuery(
							"from RegistroProduccion as i where i.idFormula = ? and bascula = ? and descripcion = ?")
					.setString(0, idFormula).setString(1, bascula)
					.setString(2, descripcion).list();

			if (registroproduccion != null && registroproduccion.size() > 0) {
				thread.sleep(200);
				transaction = session.beginTransaction();
				registroproduccion.get(0).setTotalBatchProcesados(nuevoBatch);
				transaction.commit();
				HibernateUtil.shutDown();
			} else {
				HibernateUtil.shutDown();
			}
			if (idFormula.equals("-1")) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	public void updateSumatoriaBatchPesoObtenido(String idFormula,
			String bascula, String descripcion, double nuevoBatch) {
		Thread thread = new Thread();
		try {
			thread.sleep(200);
			session = HibernateUtil.getSession();
			List<RegistroProduccion> registroproduccion = session
					.createQuery(
							"from RegistroProduccion as i where i.idFormula = ? and bascula = ? and descripcion = ?")
					.setString(0, idFormula).setString(1, bascula)
					.setString(2, descripcion).list();

			if (registroproduccion != null && registroproduccion.size() > 0) {
				thread.sleep(200);
				transaction = session.beginTransaction();
				registroproduccion.get(0).setSumatoriaBatchPesosObtenidos(
						nuevoBatch);
				transaction.commit();
				HibernateUtil.shutDown();
			} else {
				HibernateUtil.shutDown();
			}
			if (idFormula.equals("-1")) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public double getSumatoriaBatchPesoObtenido(String idFormula,
			String bascula, String descripcion) {
		session = HibernateUtil.getSession();
		List<Formula> formulas = session
				.createQuery("from Formula as f where f.idFormula = ?")
				.setInteger(0, Integer.parseInt(idFormula)).list();

		if (formulas != null && formulas.size() > 0) {
			Formula formulaFromDB = formulas.get(0);
			if (!formulaFromDB.isCancelada() && !formulaFromDB.isDespachada()) {
				List<RegistroProduccion> registroProduccion = session
						.createQuery(
								"from RegistroProduccion as i where i.idFormula = ? and i.bascula = ? and i.descripcion = ?")
						.setString(0,
								String.valueOf(formulaFromDB.getIdFormula()))
						.setString(1, bascula).setString(2, descripcion).list();
				HibernateUtil.shutDown();

				return registroProduccion.get(0)
						.getSumatoriaBatchPesosObtenidos();
			}
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isFormulaPrepesada(String idFormula) {
		session = HibernateUtil.getSession();
		List<Formula> formulas = session
				.createQuery("from Formula as f where f.idFormula = ?")
				.setInteger(0, Integer.parseInt(idFormula)).list();

		if (formulas != null && formulas.size() > 0) {
			Formula formulaFromDB = formulas.get(0);
			if (!formulaFromDB.isCancelada() && !formulaFromDB.isDespachada()) {
				return formulaFromDB.isPrepesado();
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getBatchsCompleteBascula(int idBascula) {
		int value = 0;
		try {
			session = HibernateUtil.getSession();

			List<Bascula> basculas = session
					.createQuery("from Bascula as i where i.idBascula = ?")
					.setInteger(0, idBascula).list();
			value = basculas.get(0).getBatchs();
			HibernateUtil.shutDown();

		} catch (Exception e) {
			e.printStackTrace();
			value = -1;
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	public void setBatchsCompleteBascula(int idBascula, int batchs) {
		try {
			session = HibernateUtil.getSession();

			List<Bascula> basculas = session
					.createQuery("from Bascula as i where i.idBascula = ?")
					.setInteger(0, idBascula).list();

			transaction = session.beginTransaction();
			Bascula basculaToPerformChange = basculas.get(0);
			basculaToPerformChange.setBatchs(batchs);
			session.update(basculaToPerformChange);
			transaction.commit();
			HibernateUtil.shutDown();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getSavedBatchsRegistroProduccion(int idRegistroProduccion,
			double pesoObtenido, String addressMemory) {

		int valor = 0;

		session = HibernateUtil.getSession();
		List<BatchProduccion> bPro = session
				.createQuery(
						"from BatchProduccion as b where b.registroProduccion = ? and b.addressMemory = ?")
				.setInteger(0, idRegistroProduccion)
				.setString(1, addressMemory).list();

		if (bPro != null && bPro.size() > 0) {
			session = HibernateUtil.getSession();
			valor = bPro.get(0).getRegistroProduccion()
					.getIdRegistroProduccion();
			HibernateUtil.shutDown();
		}

		return valor;
	}

	@SuppressWarnings("unchecked")
	public boolean verificarFormulaCargadaEnPLC(int formula) {
		session = HibernateUtil.getSession();
		List<IACNADispatcher> dispatcher = session
				.createQuery(
						"FROM IACNADispatcher as i "
								+ "WHERE i.formula.idFormula = ?")
				.setInteger(0, formula).list();

		boolean cargada = false;
		if (dispatcher.size() > 0) {
			if (dispatcher.get(0).getCargadaEnPLC() != null) {
				cargada = dispatcher.get(0).getCargadaEnPLC().booleanValue();	
			} else {
				cargada = false;
			}
			
		}
		return cargada;
	}
	
	@SuppressWarnings("unchecked")
	public void formulaRegistradaEnPLC(int formula) {
		session = HibernateUtil.getSession();
		List<IACNADispatcher> dispatcher = session
				.createQuery(
						"FROM IACNADispatcher as i "
								+ "WHERE i.formula.idFormula = ?")
				.setInteger(0, formula).list();

		transaction = session.beginTransaction();
		IACNADispatcher dispatcherDB = dispatcher.get(0);
		dispatcherDB.setCargadaEnPLC(true);
		session.update(dispatcherDB);
		transaction.commit();
		HibernateUtil.shutDown();
		
	}

	@Override
	public Integer getUltimoRegistroBacthProduccion() {
		
		Integer size = 1;
		session = HibernateUtil.getSession();
		List<BatchProduccion> filas = session
				.createQuery(
						"FROM BatchProduccion").list();
		
		transaction = session.beginTransaction();
		if(filas != null){
			size += filas.size();
		}
		transaction.commit();
		HibernateUtil.shutDown();
		return size;
	}


}
