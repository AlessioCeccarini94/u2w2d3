package alessioceccarini.u2w2d3.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BlogDTO(
		@NotNull(message = "insert category")
		String category,
		@NotNull(message = "title required")
		String title,
		String content,
		int readingTime,
		UUID authorId) {
}
