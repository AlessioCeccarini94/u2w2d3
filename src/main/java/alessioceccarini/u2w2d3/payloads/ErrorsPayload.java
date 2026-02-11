package alessioceccarini.u2w2d3.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
public class ErrorsPayload {
	private String message;
	private LocalDate date;
}
