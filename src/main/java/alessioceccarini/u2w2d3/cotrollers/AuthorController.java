package alessioceccarini.u2w2d3.cotrollers;


import alessioceccarini.u2w2d3.entities.Author;
import alessioceccarini.u2w2d3.payloads.AuthorPayload;
import alessioceccarini.u2w2d3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("authors")
public class AuthorController {

	private final AuthorService authorService;

	@Autowired
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	//------------------------------------ G E T ----------------------------------------------

	@GetMapping
	public Page<Author> getAuthors(@RequestParam(defaultValue = "0") int page,
								   @RequestParam(defaultValue = "10") int size,
								   @RequestParam(defaultValue = "lastName") String orderBy) {
		return this.authorService.findAll(page, size, orderBy);
	}

	//------------------------------------ P O S T  ----------------------------------------------
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Author saveAuthor(@RequestBody AuthorPayload authorPayload) {
		return this.authorService.save(authorPayload);
	}
}
