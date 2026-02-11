package alessioceccarini.u2w2d3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Setter(AccessLevel.NONE)
	private UUID blogId;
	private String category;
	private String title;
	private String cover;
	private String content;
	private int readingTime;
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author authorId;

	public Blog(String category, String title, String cover, String content, int readingTime, Author authorId) {
		this.category = category;
		this.title = title;
		this.cover = cover;
		this.content = content;
		this.readingTime = readingTime;
		this.authorId = authorId;
	}
}
