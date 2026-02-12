package alessioceccarini.u2w2d3.exceptions;


import alessioceccarini.u2w2d3.payloads.ErrorsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorsHandle {
	@ExceptionHandler(AlreadyUsedException.class)
	public ErrorsDTO handleAlreadyUsedException() {
		return new ErrorsDTO("Email already used", LocalDate.now());
	}

	@ExceptionHandler(NotAdultException.class)
	public ErrorsDTO handleNotAdultException() {
		return new ErrorsDTO("The Author is younger than 18 years old", LocalDate.now());
	}

	@ExceptionHandler(NotFoundEception.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorsDTO handleNotFoundEception() {
		return new ErrorsDTO("Not Found ", LocalDate.now());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach((error) -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});
		return errors;
	}
}
