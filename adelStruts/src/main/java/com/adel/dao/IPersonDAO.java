package com.adel.dao;

import java.util.List;

import com.adel.model.Person;

public interface IPersonDAO extends GenericDAO<Person, Integer> {
	
	public List<Person> getPersonByFistName(String firstName, String lastName);
	public Person getPersonEmail(String email);
	
	
}
