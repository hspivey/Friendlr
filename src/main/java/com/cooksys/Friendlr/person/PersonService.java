package com.cooksys.Friendlr.person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class PersonService {

	private List<Person> persons = new ArrayList<Person>();
	private PersonMapper mapper;

	public PersonService(PersonMapper mapper) {
		this.mapper = mapper;
	}

	public List<PersonDto> find(String firstName, String lastName) {

		List<Person> personz = persons;

		if (firstName != null) {
			personz = personz.stream().filter(person -> firstName.equals(person.getFirstName()))
					.collect(Collectors.toList());
		}

		if (lastName != null) {
			personz = personz.stream().filter(person -> lastName.equals(person.getLastName()))
					.collect(Collectors.toList());
		}

		return personz.stream().map(mapper::toDto).collect(Collectors.toList());

	}

	public PersonDto get(Long id) {
		return mapper.toDto(persons.get(Math.toIntExact(id)));
	}

	public PersonDto create(PersonDto dto) {

		Person person = mapper.toPerson(dto);

		person.setId((long) persons.size());

		persons.add(person);

		return mapper.toDto(person);
	}

	public PersonDto updatePerson(Long id, PersonDto dto) {

		Person person = mapper.toPerson(dto);

		person.setId(id);

		persons.set(Math.toIntExact(id - 1), person);

		return mapper.toDto(person);
	}

	public PersonDto deletePerson(Long id) {

		persons.remove(id - 1);

		return mapper.toDto(persons.get(Math.toIntExact(id)));
	}
}
