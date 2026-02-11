package alessioceccarini.u2w2d3.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AuthorPayload {
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthDate;
}
