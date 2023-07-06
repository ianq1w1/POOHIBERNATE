package org.exemplo.persistencia.database.dao;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.exemplo.persistencia.database.db.IConnection;
import org.exemplo.persistencia.database.model.Cliente;
//import org.exemplo.persistencia.database.model.Cliente;
import org.exemplo.persistencia.database.model.Conta;
//import org.exemplo.persistencia.database.model.Transacao;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ContaDAO implements IEntityDAO<Conta> {

	private IConnection conn;

	public ContaDAO(IConnection conn) {
		this.conn = conn;
	}

	
	public void save(Conta t) {
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.persist(t);
		session.getTransaction().commit();
		session.close();
	}

	
	public Conta findById(Integer id) {
		Session session = conn.getSessionFactory().openSession();
		Conta c = session.find(Conta.class, id);
		session.close();
		return c;
	}

	
	public void update(Conta t) {
		// TODO Auto-generated method stub
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.merge(t);
		session.getTransaction().commit();
		session.close();
	}

	
	public void delete(Conta t) {
	    Session session = conn.getSessionFactory().openSession(); 
		session.beginTransaction();
		session.delete(t);
		session.getTransaction().commit();
		session.close();
		
	}

	
	public List<Conta> findAll() {
		
		Session session = conn.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Conta> query = builder.createQuery(Conta.class);
        Root<Conta> root = query.from(Conta.class);
        query.select(root);
        return session.createQuery(query).getResultList();
	}

	



	@Override
	public void closeSession() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Cliente findByCpf2(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Cliente findByCpf(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}



}

