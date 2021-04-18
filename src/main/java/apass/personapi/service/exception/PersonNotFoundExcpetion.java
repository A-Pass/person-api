package apass.personapi.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PersonNotFoundExcpetion extends Exception {

	public PersonNotFoundExcpetion(Long id) {
		super("Not found person with ID " + id);
	}
}
