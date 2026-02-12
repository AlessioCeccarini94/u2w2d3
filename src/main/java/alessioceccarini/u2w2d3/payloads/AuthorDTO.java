package alessioceccarini.u2w2d3.payloads;


import java.time.LocalDate;

public record AuthorDTO(

		String firstName,
		String lastName,
		String email,
		LocalDate birthDate) {
}
