package alessioceccarini.u2w2d3.payloads;

import java.util.UUID;

public record BlogDTO(String category, String title, String content, int readingTime, UUID authorId) {
}
