package com.cooksys.Friendlr.person;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonController {

	private PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping
	public List<PersonDto> getAll(@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName) {
		return personService.find(firstName, lastName);
	}

	@GetMapping("{id}")
	public PersonDto getSpecificPerson(@PathVariable Long id) {
		if (id == null) {
			return (HttpStatus.NOT_FOUND);
		} else {
			try {
				return personService.get(id);
			} catch (IndexOutOfBoundsException e) {

			}
		}
	}

	@PostMapping
	public PersonDto addPerson(@RequestBody PersonDto person) {

		return personService.create(person);
	}

	@PutMapping("{id}")
	public PersonDto updatePerson(@PathVariable Long id, @RequestBody PersonDto person) {

		return personService.updatePerson(id, person);
	}

	@DeleteMapping("{id}")
	public PersonDto deletePerson(@PathVariable Long id) {

		return personService.deletePerson(id);
	}
}