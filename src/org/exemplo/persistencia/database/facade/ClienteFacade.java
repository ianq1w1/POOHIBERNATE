package org.exemplo.persistencia.database.facade;

import java.util.List;

import org.exemplo.persistencia.database.dao.IEntityDAO;
import org.exemplo.persistencia.database.dao.ClienteDAO;
import org.exemplo.persistencia.database.db.ConexaoBancoHibernate;
import org.exemplo.persistencia.database.db.ConexaoBancoMySQL;
//import org.exemplo.persistencia.database.model.Exame;
import org.exemplo.persistencia.database.model.Cliente;
import org.exemplo.persistencia.database.model.Conta;

public class ClienteFacade {

	private IEntityDAO<Cliente> clienteDao;
	private static ClienteFacade instance;

	private ClienteFacade() {
		clienteDao = new ClienteDAO(new ConexaoBancoHibernate());
	}

	public static ClienteFacade getInstance() {

		if (instance != null)
			return instance;
		else {
			instance = new ClienteFacade();
			return instance;
		}
	}

	public void save(Integer id, String nome, String cpf) {
		clienteDao.save(new Cliente(nome, cpf));
	}

	public void delete(Integer id) {
	    Cliente cliente = clienteDao.findById(id);
	    if (cliente != null) {
	        clienteDao.delete(cliente);
	    } else {
	        System.out.println("Cliente n�o encontrado");
	    }
	}

	public void update(Integer id, String nome, String cpf, List<Conta> Contas) {
		clienteDao.update(new Cliente(nome, cpf));

	}

	public Cliente findById(Integer id) {
		return clienteDao.findById(id);
	}

	public List<Cliente> findAll() {
		return clienteDao.findAll();
	}
}
