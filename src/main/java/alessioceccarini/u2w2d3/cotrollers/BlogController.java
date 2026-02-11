package alessioceccarini.u2w2d3.cotrollers;


import alessioceccarini.u2w2d3.entities.Blog;
import alessioceccarini.u2w2d3.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/blogs")
public class BlogController {

	private final BlogRepository blogRepository;

	@Autowired
	public BlogController(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

	//------------------------------------ G E T ----------------------------------------------

	@GetMapping
	public Page<Blog> getBlogs(@RequestParam(defaultValue = "0") int page,
							   @RequestParam(defaultValue = "10") int size,
							   @RequestParam(defaultValue = "title") String title) {
		return this.blogRepository.findAll(PageRequest.of(page, size, Sort.by(title).ascending()));
	}

	//------------------------------------ P O S T  ----------------------------------------------
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Blog save(@RequestBody Blog blog) {
		return this.blogRepository.save(blog);
	}
}
