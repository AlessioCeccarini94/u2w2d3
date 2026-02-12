package alessioceccarini.u2w2d3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Random;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Blog {

	Random random = new Random();

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
	private Author author;

	public Blog(String category, String title, String content, int readingTime, Author authorId) {
		this.category = category;
		this.title = title;
		this.cover = "https://www.placebaer.com/" + random.nextInt(100, 400) + "/" + random.nextInt(100, 400);
		this.content = content;
		this.readingTime = readingTime;
		this.author = author;
	}
}
