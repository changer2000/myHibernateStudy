package org.hibernate.tutorial.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.util.HibernateUtil;

public class EventManager {
	public static void main(String[] args) {
		EventManager mgr = new EventManager();
		//if (args[0].equals("store")) {
			mgr.createAndStoreEvent("My Event", new Date());
		//}
		HibernateUtil.getSessionFactory().close();
	}

	private void createAndStoreEvent(String title, Date theDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Event theEvent = new Event();
		theEvent.setTitle(title);
		theEvent.setDate(theDate);
		session.save(theEvent);
		System.out.println(theEvent.getId());
		session.getTransaction().commit();
	}
}
