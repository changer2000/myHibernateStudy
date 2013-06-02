package org.hibernate.tutorial.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.tutorial.domain.Person;
import org.hibernate.tutorial.util.HibernateUtil;

public class PersonManager {
	
	private static final Log logger = LogFactory.getLog(PersonManager.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PersonManager pm = new PersonManager();
		//pm.addEmail();
		pm.addEmail2();
	}
	
	public void addEmail() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Person p = null;
		List<Person> list = (List<Person>) session.createQuery("select p from Person p where p.id=?")
						.setParameter(0, 1).list();
		if (list!=null && list.size()>0) {
			Iterator<Person> iter = list.iterator();
			while (iter.hasNext()) {
				p = iter.next();
				logger.debug(">>>" + ToStringBuilder.reflectionToString(p));
			}
		}
		session.getTransaction().commit();
		
		if (p!=null) {
			p.getEmailAddress().add("test@haha.com");
		}
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		//session.save(p); it will create a new person record.
		session.update(p);	//it will update the person's record.
		session.getTransaction().commit();
	}
	
	public void addEmail2() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Person p = null;
		List<Person> list = (List<Person>) session.createQuery("select p from Person p left join fetch p.emailAddress where p.id=?")
						.setParameter(0, 1).list();
		if (list!=null && list.size()>0) {
			Iterator<Person> iter = list.iterator();
			while (iter.hasNext()) {
				p = iter.next();
				logger.debug(">>>" + ToStringBuilder.reflectionToString(p));
			}
		}
		session.getTransaction().commit();
		
		if (p!=null) {
			p.getEmailAddress().add("test2@haha.com");
		}
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		//session.save(p); it will create a new person record.
		session.update(p);	//it will update the person's record.
		session.getTransaction().commit();
	}

}
