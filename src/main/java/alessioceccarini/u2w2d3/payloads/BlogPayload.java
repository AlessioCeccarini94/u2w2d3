package alessioceccarini.u2w2d3.payloads;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class BlogPayload {
	private String category;
	private String title;
	private String content;
	private int readingTime;
	private UUID authorId;
}
