package opendoors.app.desktop.pomodoro.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import opendoors.app.desktop.pomodoro.controller.DataController;
import opendoors.app.desktop.pomodoro.model.Assunto;
import opendoors.app.desktop.pomodoro.model.Materia;

public class Fixtures {


	public static void main(String args[]){
		
		Materia materia = new Materia("JavaFX");
		Materia materia2 = new Materia("Hibernate");
		Materia materia3 = new Materia("Angular");
		Materia materia4 = new Materia("Sap Bods");
		Materia materia5 = new Materia("Inglês");

//		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		session.save(materia);
//		session.save(materia2);
//		session.save(materia3);
//		session.save(materia4);
//		session.save(materia5);
//		session.save(assunto1);
//		session.save(assunto2);
//		session.save(assunto3);
//		session.save(assunto4);
//		session.save(assunto5);
//		session.getTransaction().commit();
//		session.close();
		
		DataController controller = new DataController();
		
		controller.saveMateria(materia);
		controller.saveMateria(materia2);
		controller.saveMateria(materia3);
		controller.saveMateria(materia4);
		controller.saveMateria(materia5);
		
		ArrayList<Materia> materias = (ArrayList<Materia>) controller.getTodasMaterias();
//		
//		for (Materia materia6 : materias) {
//			System.out.println(materia6.getNome());
//		}
//		
		Assunto assunto1 = new Assunto(materias.get(0), "JavaFX");
		Assunto assunto2 = new Assunto(materias.get(1), "Hibernate");
		Assunto assunto3 = new Assunto(materias.get(2), "Angular");
		Assunto assunto4 = new Assunto(materias.get(3), "Sap Bods");
		Assunto assunto5 = new Assunto(materias.get(4), "Phrasal Verbs");
		controller.saveAssunto(assunto1);
		controller.saveAssunto(assunto2);
		controller.saveAssunto(assunto3);
		controller.saveAssunto(assunto4);
		controller.saveAssunto(assunto5);
	}
}
