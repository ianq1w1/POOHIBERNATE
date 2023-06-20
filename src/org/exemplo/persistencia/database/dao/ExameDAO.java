package org.exemplo.persistencia.database.dao;


import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.exemplo.persistencia.database.db.IConnection;
import org.exemplo.persistencia.database.model.Exame;
import org.hibernate.Session;

public class ExameDAO implements IEntityDAO<Exame> {

	private IConnection conn;

	public ExameDAO(IConnection conn) {
		this.conn = conn;
	}

	@Override
	public void save(Exame t) {
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.persist(t);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Exame findById(Integer id) {
		Session session = conn.getSessionFactory().openSession();
		return session.find(Exame.class, id);
	}

	@Override
	public void update(Exame t) {
		// TODO Auto-generated method stub
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.merge(t);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(Exame t) {
		// TODO Auto-generated method stub
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(t);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Exame> findAll() {
		
		Session session = conn.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Exame> query = builder.createQuery(Exame.class);
        Root<Exame> root = query.from(Exame.class);
        query.select(root);
        return session.createQuery(query).getResultList();
	}

}
