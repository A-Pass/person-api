package apass.personapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import apass.personapi.dto.MessageResponseDTO;
import apass.personapi.dto.request.PersonDTO;
import apass.personapi.entity.Person;
import apass.personapi.mapper.PersonMapper;
import apass.personapi.repository.PersonRepository;
import apass.personapi.service.exception.PersonNotFoundExcpetion;

@Service
public class PersonService {
	private PersonRepository personRepository;

	private PersonMapper personMapper = PersonMapper.INSTANCE;

	/**
	 * 
	 * @param personRepository
	 */
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	/**
	 * 
	 * @param personDTO
	 * @return
	 */
	public MessageResponseDTO createPerson(@RequestBody PersonDTO personDTO) {
		var personToSave = this.personMapper.toModel(personDTO);
		var savedPerson = this.personRepository.save(personToSave);
		return this.createMessageResponse(savedPerson.getId(), "Created person with ID");
	}

	/**
	 * 
	 * @return
	 */
	public List<PersonDTO> listAll() {
		var allPeople = this.personRepository.findAll();
		return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersonNotFoundExcpetion
	 */
	public PersonDTO findById(Long id) throws PersonNotFoundExcpetion {
		var person = this.verifyIfExists(id);
		return this.personMapper.toDTO(person);
	}

	/**
	 * 
	 * @param id
	 * @throws PersonNotFoundExcpetion
	 */
	public void delete(Long id) throws PersonNotFoundExcpetion {
		this.verifyIfExists(id);
		this.personRepository.deleteById(id);
	}

	/**
	 * 
	 * @param id
	 * @param personDTO
	 * @return
	 * @throws PersonNotFoundExcpetion
	 */
	public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundExcpetion {
		this.verifyIfExists(id);
		var personToUpdate = this.personMapper.toModel(personDTO);
		personToUpdate.setId(id);
		var updatedPerson = this.personRepository.save(personToUpdate);
		return this.createMessageResponse(updatedPerson.getId(), "Updated person with ID");
	}

	/**
	 * @param id
	 * @return
	 */
	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO.builder().message(message + " " + id).build();
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersonNotFoundExcpetion
	 */
	private Person verifyIfExists(Long id) throws PersonNotFoundExcpetion {
		return this.personRepository.findById(id).orElseThrow(() -> new PersonNotFoundExcpetion(id));
	}

}
