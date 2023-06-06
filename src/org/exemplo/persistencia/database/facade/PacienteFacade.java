package org.exemplo.persistencia.database.facade;

import java.util.List;

import org.exemplo.persistencia.database.dao.IEntityDAO;
import org.exemplo.persistencia.database.dao.PacienteDAO;
import org.exemplo.persistencia.database.db.ConexaoBancoMySQL;
import org.exemplo.persistencia.database.model.Exame;
import org.exemplo.persistencia.database.model.Paciente;

public class PacienteFacade {

	private IEntityDAO<Paciente> pacienteDao;
	private static PacienteFacade instance;

	private PacienteFacade() {
		pacienteDao = new PacienteDAO(new ConexaoBancoMySQL());
	}

	public static PacienteFacade getInstance() {

		if (instance != null)
			return instance;
		else {
			instance = new PacienteFacade();
			return instance;
		}
	}

	public void save(Integer id, String nome, Float altura, Float peso) {
		pacienteDao.save(new Paciente(id, nome, altura, peso));
	}

	public void delete(Integer id) {
		pacienteDao.delete(new Paciente(id));
	}

	public void update(Integer id, String nome, Float altura, Float peso, List<Exame> exames) {
		pacienteDao.update(new Paciente(id, nome, altura, peso));

	}

	public Paciente findById(Integer id) {
		return pacienteDao.findById(id);
	}

	public List<Paciente> findAll() {
		return pacienteDao.findAll();
	}
}
