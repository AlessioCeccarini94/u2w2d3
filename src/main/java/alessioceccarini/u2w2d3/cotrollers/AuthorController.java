package alessioceccarini.u2w2d3.cotrollers;


import alessioceccarini.u2w2d3.entities.Author;
import alessioceccarini.u2w2d3.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("authors")
public class AuthorController {

	private final AuthorRepository authorRepository;

	@Autowired
	public AuthorController(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	//------------------------------------ G E T ----------------------------------------------

	@GetMapping
	public Page<Author> getAuthors(@RequestParam(defaultValue = "1") int page,
								   @RequestParam(defaultValue = "10") int size,
								   @RequestParam(defaultValue = "lastName") String orderBy) {
		return this.authorRepository.findAll(PageRequest.of(page, size, Sort.by(orderBy)));
	}

	//------------------------------------ P O S T  ----------------------------------------------
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Author saveAuthor(@RequestBody Author author) {
		return this.authorRepository.save(author);
	}
}
