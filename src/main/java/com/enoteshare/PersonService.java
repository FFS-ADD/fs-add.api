package com.enoteshare;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	@Autowired
	PersonRepository personRepository;

	public Iterable<PersonExample> findPeople(PersonExample probe) {
		return personRepository.findAll(Example.of(probe));
	}
	
	public PersonExample findOnePeople(String firstname ) {
		return personRepository.findOneByFirstname(firstname, new Sort(Direction.DESC,"lastname"));
	}
	
	public List<PersonExample> findAllPeople(){
		return personRepository.findWeelyByFirstname("2", "4", new Sort(Direction.DESC,"lastname"));
	}
}
