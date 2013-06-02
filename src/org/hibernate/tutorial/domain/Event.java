package org.hibernate.tutorial.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Event {

	public Event() {
		super();
	}
	
	public void addPerson(Person p) {
		this.getParticipants().add(p);
		p.getEvents().add(this);
	}
	
	public void removePerson(Person p) {
		this.getParticipants().remove(p);
		p.getEvents().remove(this);
	}
	
	private Integer id;
	private String title;
	private Date date;
	private Set<Person> participants = new HashSet<Person>();
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	protected Set<Person> getParticipants() {
		return participants;
	}
	protected void setParticipants(Set<Person> participants) {
		this.participants = participants;
	}
	
}
