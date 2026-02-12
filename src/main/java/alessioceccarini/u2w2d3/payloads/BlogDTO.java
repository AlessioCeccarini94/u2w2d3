package alessioceccarini.u2w2d3.payloads;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record BlogDTO(
		@NotBlank(message = "insert category")
		String category,
		@NotBlank(message = "title required")
		String title,
		String content,
		int readingTime,
		UUID authorId) {
}
