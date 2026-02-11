package alessioceccarini.u2w2d3.services;

import alessioceccarini.u2w2d3.entities.Blog;
import alessioceccarini.u2w2d3.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BlogService {

	private final BlogRepository blogRepository;

	@Autowired
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	//------------------------------------ G E T ----------------------------------------------

	Page<Blog> findAll(int page, int size, String sortBy) {
		if (page < 0) page = 0;
		if (size < 0 || size > 100) size = 10;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
		return this.blogRepository.findAll(pageable);
	}

	//------------------------------------ P O S T ----------------------------------------------

	public Blog save(Blog blog) {
		return this.blogRepository.save(blog);
	}
}
