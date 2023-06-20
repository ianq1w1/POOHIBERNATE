package org.exemplo.persistencia.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.exemplo.persistencia.database.db.IConnection;
import org.exemplo.persistencia.database.model.Exame;
import org.exemplo.persistencia.database.model.Paciente;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PacienteDAO implements IEntityDAO<Paciente>{

	private IConnection conn;
	private IEntityDAO<Exame> exameDAO;
	
	public PacienteDAO(IConnection conn) {
		this.conn = conn;
		exameDAO = new ExameDAO(conn);
	}
	
	public void save(Paciente p) {
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.persist(p);
		session.getTransaction().commit();
		session.close();
	}
	
	public void delete(Paciente p) {
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		session.close();
	}
	
	public void update(Paciente p) {
		Session session = conn.getSessionFactory().openSession();
		session.beginTransaction();
		session.merge(p);
		session.getTransaction().commit();
		session.close();
		
	}
	
	public Paciente findById(Integer id) {
		Session session = conn.getSessionFactory().openSession();
		Paciente temp = session.find(Paciente.class, id);
		return temp;
	}

	@Override
	public List<Paciente> findAll() {
		Session session = conn.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Paciente> query = builder.createQuery(Paciente.class);
        Root<Paciente> root = query.from(Paciente.class);
        query.select(root);
        return session.createQuery(query).getResultList();
	}
}
