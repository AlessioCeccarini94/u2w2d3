package alessioceccarini.u2w2d3.cotrollers;


import alessioceccarini.u2w2d3.entities.Author;
import alessioceccarini.u2w2d3.payloads.AuthorDTO;
import alessioceccarini.u2w2d3.payloads.AuthorPayload;
import alessioceccarini.u2w2d3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


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
	public Author saveAuthor(@RequestBody AuthorDTO authorPayload) {
		return this.authorService.save(authorPayload);
	}

	//------------------------------------- P U T  ----------------------------------------------

	@PutMapping("/{authorsId}")
	public Author updateAuthor(@PathVariable UUID authorId, @RequestBody AuthorPayload authorPayload) {
		return authorService.updateAuthor(authorId, authorPayload);
	}

	//----------------------------------- D E L E T E  ----------------------------------------------

	@DeleteMapping("/{authorsid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAuthor(@PathVariable UUID authorId) {
		authorService.findAuthorById(authorId);
	}
}
