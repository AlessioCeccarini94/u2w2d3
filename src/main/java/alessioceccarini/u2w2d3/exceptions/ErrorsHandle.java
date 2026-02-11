package alessioceccarini.u2w2d3.exceptions;


import alessioceccarini.u2w2d3.payloads.ErrorsPayload;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class ErrorsHandle {
	@ExceptionHandler(AlreadyUsedException.class)
	public ErrorsPayload handleAlreadyUsedException() {
		return new ErrorsPayload("Email already used", LocalDate.now());
	}

	@ExceptionHandler(NotAdultException.class)
	public ErrorsPayload handleNotAdultException() {
		return new ErrorsPayload("The Author is younger than 18 years old", LocalDate.now());
	}

}
