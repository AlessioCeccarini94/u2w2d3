package alessioceccarini.u2w2d3.payloads;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AuthorDTO(
		@NotNull(message = "first name required")
		String firstName,
		@NotNull(message = "last name required")
		String lastName,
		@NotNull(message = "insert a valid email")
		String email,
		@Min(1916)
		LocalDate birthDate) {
}
