package org.hibernate.tutorial.dao;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.util.HibernateUtil;

public class EventManager {
	private static Log logger = LogFactory.getLog(EventManager.class);
	
	public static void main(String[] args) {
		EventManager mgr = new EventManager();
		//if (args[0].equals("store")) {
			mgr.createAndStoreEvent("My Event", new Date());
		//}
		HibernateUtil.getSessionFactory().close();
	}

	private void createAndStoreEvent(String title, Date theDate) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		logger.debug(">>>>>>>>>>>>>");
		session.beginTransaction();
		Event theEvent = new Event();
		theEvent.setTitle(title);
		theEvent.setDate(theDate);
		session.save(theEvent);
		System.out.println(theEvent.getId());
		session.getTransaction().commit();
	}
}
