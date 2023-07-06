package org.exemplo.persistencia.database.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.exemplo.persistencia.database.db.IConnection;
import org.exemplo.persistencia.database.model.Cliente;
import org.exemplo.persistencia.database.model.Conta;
//import org.exemplo.persistencia.database.model.Conta;
import org.exemplo.persistencia.database.model.Transacao;
import org.hibernate.Session;

public class TransacaoDAO implements IEntityDAO<Transacao>{

	private IConnection conn;

	public TransacaoDAO(IConnection conn) {
		this.conn = conn;
	}
	
	
	
	public void save(Transacao t) {
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.persist(t);
		session.getTransaction().commit();
		session.close();
	}

	
	public void update(Transacao t) {
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.merge(t);
		session.getTransaction().commit();
		session.close();
		
	}

	
	public void delete(Transacao t) {
		// TODO Auto-generated method stub
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(t);
		session.getTransaction().commit();
		session.close();
	}

	
	public Transacao findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Cliente findByCpf(String cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Transacao> findAll() {
		Session session = conn.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Transacao> query = builder.createQuery(Transacao.class);
        Root<Transacao> root = query.from(Transacao.class);
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





}