package alessioceccarini.u2w2d3.cotrollers;


import alessioceccarini.u2w2d3.entities.Blog;
import alessioceccarini.u2w2d3.payloads.BlogPayload;
import alessioceccarini.u2w2d3.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/blogs")
public class BlogController {

	private final BlogService blogService;

	@Autowired
	public BlogController(BlogService blogService) {
		this.blogService = blogService;


	}

	//------------------------------------ G E T ----------------------------------------------

	@GetMapping
	public Page<Blog> getBlogs(@RequestParam(defaultValue = "0") int page,
							   @RequestParam(defaultValue = "10") int size,
							   @RequestParam(defaultValue = "title") String orderBy) {
		return this.blogService.findAll(page, size, orderBy);
	}

	@GetMapping("/{userId}")
	public Blog getBlogs(@RequestParam(required = false) UUID authorId) {
		if (authorId != null) return blogService.findById(authorId);
		else throw new RuntimeException();
	}

	//------------------------------------ P O S T  ----------------------------------------------
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Blog saveBlog(@RequestBody BlogPayload blogPayload) {
		return this.blogService.saveBlog(blogPayload);
	}

	//------------------------------------- P U T  ----------------------------------------------

	@PutMapping("/{blogId}")
	public Blog findAndUpdate(@PathVariable UUID blogId, @RequestBody BlogPayload body) {
		return blogService.updateBlog(blogId, body);
	}

	//----------------------------------- D E L E T E  ----------------------------------------------


	@DeleteMapping("/{blogId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void findAndDelete(@PathVariable UUID blogId) {
		blogService.deleteBlog(blogId);
	}
}
