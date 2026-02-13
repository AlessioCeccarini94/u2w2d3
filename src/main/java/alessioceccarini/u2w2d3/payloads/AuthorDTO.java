package alessioceccarini.u2w2d3.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

public record AuthorDTO(
		@NotBlank(message = "first name required")
		@Size(min = 3, max = 50)
		String firstName,
		@NotBlank(message = "last name required")
		@Size(min = 3, max = 50)
		String lastName,
		@NotBlank(message = "insert a valid email")
		@Email
		String email,
		@NotNull
		LocalDate birthDate,
		@NotBlank
		@URL
		String avatar) {
}
