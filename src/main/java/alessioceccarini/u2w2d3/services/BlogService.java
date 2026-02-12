package alessioceccarini.u2w2d3.services;

import alessioceccarini.u2w2d3.entities.Author;
import alessioceccarini.u2w2d3.entities.Blog;
import alessioceccarini.u2w2d3.exceptions.AlreadyUsedException;
import alessioceccarini.u2w2d3.payloads.BlogPayload;
import alessioceccarini.u2w2d3.repositories.AuthorRepository;
import alessioceccarini.u2w2d3.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class BlogService {

	private final BlogRepository blogRepository;
	private final AuthorRepository authorRepository;

	@Autowired
	public BlogService(BlogRepository blogRepository, AuthorRepository authorRepository) {
		this.blogRepository = blogRepository;
		this.authorRepository = authorRepository;

	}


	//------------------------------------ G E T ----------------------------------------------

	public Page<Blog> findAll(int page, int size, String sortBy) {
		if (page < 0) page = 0;
		if (size < 0 || size > 100) size = 10;
		if (sortBy == null) sortBy = "title";
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
		return this.blogRepository.findAll(pageable);
	}

	public Blog findById(UUID id) {
		return blogRepository.findById(id).orElseThrow(() -> new AlreadyUsedException("nknk"));
	}
	//------------------------------------ P O S T ----------------------------------------------

	public Blog saveBlog(BlogPayload blogPayload) {
		Author author = this.authorRepository.findById(blogPayload.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found"));
		Blog newBlog = new Blog(
				blogPayload.getCategory(),
				blogPayload.getTitle(),
				blogPayload.getContent(),
				blogPayload.getReadingTime(),
				author
		);
		return this.blogRepository.save(newBlog);
	}
}
