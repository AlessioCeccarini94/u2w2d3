package alessioceccarini.u2w2d3.exceptions;


import alessioceccarini.u2w2d3.payloads.ErrorsPayload;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class ErrorsHandle {
	@ExceptionHandler(AlreadyUsedException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorsPayload handleAlreadyUsedException() {
		return new ErrorsPayload("Email already used", LocalDate.now());
	}
}
