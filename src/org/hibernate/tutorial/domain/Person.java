package org.hibernate.tutorial.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Person {
	
	private Integer id;
	private Integer age;
	private String firstName;
	private String lastName;
	
	private Set<Event> events = new HashSet<Event>();
	private Set<String> emailAddress = new HashSet<String>();
	
	public Person() {
		
	}
	
	////////////////////  pay attention to this segment code.
	public void addEvent(Event e) {
		this.getEvents().add(e);
		e.getParticipants().add(this);
	}
	
	public void removeEvent(Event e) {
		this.getEvents().remove(e);
		e.getParticipants().remove(this);
	}
	////////////////////
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	protected Set<Event> getEvents() {
		return events;
	}

	protected void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<String> getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(Set<String> emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
}
