package com.enoteshare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	@Autowired
	PersonRepository personRepository;

	public Iterable<PersonExample> findPeople(PersonExample probe) {
		return personRepository.findAll(Example.of(probe));
	}
}
