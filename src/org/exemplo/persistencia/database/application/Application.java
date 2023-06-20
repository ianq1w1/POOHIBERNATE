package org.exemplo.persistencia.database.application;

import org.exemplo.persistencia.database.dao.ExameDAO;
import org.exemplo.persistencia.database.dao.IEntityDAO;
import org.exemplo.persistencia.database.dao.PacienteDAO;
import org.exemplo.persistencia.database.db.ConexaoBancoHibernate;
import org.exemplo.persistencia.database.model.Exame;
import org.exemplo.persistencia.database.model.Paciente;

public class Application {

	public static void main(String[] args) {
		
		IEntityDAO<Paciente> daoPac = new PacienteDAO(new ConexaoBancoHibernate());
		IEntityDAO<Exame> daoExa = new ExameDAO(new ConexaoBancoHibernate());
		
		Paciente p = daoPac.findById(3);
		
		Exame e = p.localeById(8);
		
		e.setDescricao("Bla bla bla");
		
		daoExa.update(e);
	
		System.out.println(daoPac.findById(3));
		
//		
//		for(Paciente p : daoPac.findAll()) {
//			System.out.println(p);
//		}
		
//		SwingUtilities.invokeLater(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				new TelaPrincipal();
//			}
//		});
//		
	}
}
