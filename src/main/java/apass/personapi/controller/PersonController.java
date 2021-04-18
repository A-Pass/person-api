package apass.personapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import apass.personapi.dto.MessageResponseDTO;
import apass.personapi.dto.request.PersonDTO;
import apass.personapi.service.PersonService;
import apass.personapi.service.exception.PersonNotFoundExcpetion;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

	private PersonService personService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
		return this.personService.createPerson(personDTO);
	}

	@GetMapping
	public List<PersonDTO> listAll() {
		return personService.listAll();
	}

	@GetMapping("/{id}")
	public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundExcpetion {
		return this.personService.findById(id);
	}

	@PutMapping("/{id}")
	public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundExcpetion {
		return this.personService.updateById(id, personDTO);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) throws PersonNotFoundExcpetion {
		this.personService.delete(id);
	}
}
