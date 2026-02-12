package alessioceccarini.u2w2d3.payloads;

import java.time.LocalDate;

public record ErrorsDTO(String message, LocalDate timestamp) {
}
