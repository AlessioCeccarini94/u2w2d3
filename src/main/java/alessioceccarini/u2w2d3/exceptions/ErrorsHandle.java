package alessioceccarini.u2w2d3.exceptions;


import alessioceccarini.u2w2d3.payloads.ErrorsDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

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
	public ErrorsDTO handleNotFoundEception() {
		return new ErrorsDTO("Not Found ", LocalDate.now());
	}

}
