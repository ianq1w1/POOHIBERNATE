package org.exemplo.persistencia.database.dao;

//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.exemplo.persistencia.database.db.IConnection;
//import org.exemplo.persistencia.database.model.Exame;
import org.exemplo.persistencia.database.model.Cliente;
import org.exemplo.persistencia.database.model.Conta;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



public class ClienteDAO implements IEntityDAO<Cliente>{

	private IConnection conn;

	
	public ClienteDAO(IConnection conn) {
		this.conn = conn;
	}
	
	public void save(Cliente p) {
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.persist(p);
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(Cliente p) {
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		session.close();
	}
	
	public void update(Cliente p) {
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.merge(p);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public Cliente findById(Integer id) {
		Session session = conn.getSessionFactory().openSession();
		Cliente temp = session.find(Cliente.class, id);
		return temp;
	}

	@Override
	public List<Cliente> findAll() {
		Session session = conn.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Cliente> query = builder.createQuery(Cliente.class);
        Root<Cliente> root = query.from(Cliente.class);
        query.select(root);
        return session.createQuery(query).getResultList();
	}
	
	
	public Cliente findByCpf(String cpf) {
		Session session = conn.getSessionFactory().openSession();
		String hql = "SELECT c FROM Cliente c LEFT JOIN FETCH c.contas WHERE c.cpf = :cpf";
		Query<Cliente> query = session.createQuery(hql, Cliente.class);
		query.setParameter("cpf", cpf);
		Cliente c = query.uniqueResult();
		session.close();
		return c;
	}
	
	public Cliente findByCpf2(String cpf) {
		Session session = conn.getSessionFactory().openSession();
		String hql = "SELECT c FROM Cliente c LEFT JOIN FETCH c.contas WHERE c.cpf = :cpf";
		Query<Cliente> query = session.createQuery(hql, Cliente.class);
		query.setParameter("cpf", cpf);
		return query.uniqueResult();
	}

	@Override
	public void closeSession() {
		// TODO Auto-generated method stub
		
	}


}
