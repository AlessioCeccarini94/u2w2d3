package alessioceccarini.u2w2d3.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Setter(AccessLevel.NONE)
	@Column(nullable = false)
	private UUID authorId;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthDate;
	private String avatar;

	public Author(String firstName, String lastName, String email, LocalDate birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
		this.avatar = "https://ui-avatars.com/api/?name=" + firstName + lastName;
	}
}
