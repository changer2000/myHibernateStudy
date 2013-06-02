package org.hibernate.tutorial.dao;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.domain.Person;
import org.hibernate.tutorial.util.HibernateUtil;

public class EventManager {
	private static Log logger = LogFactory.getLog(EventManager.class);
	
	public static void main(String[] args) {
		EventManager mgr = new EventManager();
		
		//mgr.createAndStoreEvent("My Event", new Date());
		
			
		//mgr.createPerson("John", "Smith", 10);
		
		//mgr.addPersonToEvent();
		
		//mgr.searchPersonAndEvent();
		
		mgr.addPersonToEvent();
		
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
	
	private void createPerson(String firstName, String lastName, Integer age) {
		Person p = new Person();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setAge(age);
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
	}
	
	private void addEventToPerson() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Person p = (Person) session.createQuery("select p from Person p left join fetch p.events where p.id=?")
						.setParameter(0, 1).uniqueResult();
		Event e = (Event) session.load(Event.class, 1);
		
		session.getTransaction().commit();
		
		
		p.addEvent(e);
		Session session2 = HibernateUtil.getSessionFactory().getCurrentSession();
		session2.beginTransaction();
		session2.update(p);
		session2.getTransaction().commit();
	}
	
	private void searchPersonAndEvent() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Person p = (Person) session.createQuery("select p from Person p left join fetch p.events where p.id=?")
						.setParameter(0, 1).uniqueResult();
		
		
		session.getTransaction().commit();
	}
	
	private void addPersonToEvent() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Person p = (Person) session.load(Person.class, 3);	//select sql此时不会发出
		Event e = (Event) session.get(Event.class, 2);		//select sql此时会发出
		p.addEvent(e);		//这一段会发出3条sql语句
							// 1. search person by person_id=3
							// 2. search events by "person_event left join events where person_event.person_id=3"
							// 3. 因为在Event.hbm.xml里有对Person的set配置，所以这里会发出: "person_event left join person where person_event.event_id=1"的serach sql，找出所有此event_id里所有的person
		session.update(p);
		session.getTransaction().commit();
	}
	
}
