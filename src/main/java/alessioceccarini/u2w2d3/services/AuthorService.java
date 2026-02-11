package alessioceccarini.u2w2d3.services;

import alessioceccarini.u2w2d3.entities.Author;
import alessioceccarini.u2w2d3.exceptions.AlreadyUsedException;
import alessioceccarini.u2w2d3.exceptions.NotAdultException;
import alessioceccarini.u2w2d3.payloads.AuthorPayload;
import alessioceccarini.u2w2d3.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthorService {

	private final AuthorRepository authorRepository;

	@Autowired
	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	//------------------------------------ G E T ----------------------------------------------

	// GET ALL AUTHORS AND ADD PAGINATION
	public Page<Author> findAll(int page, int size, String sortBy) {
		if (page < 0) page = 0;
		if (size < 0 || size > 150) size = 10;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
		return this.authorRepository.findAll(pageable);
	}

	//------------------------------------ P O S T ----------------------------------------------

	// ADD NEW AUTHOR AND CHECK EMAIL IS NOT USED YET
	public Author save(AuthorPayload authorPayload) {
		this.authorRepository.findByEmail(authorPayload.getEmail()).ifPresent(author -> {
			throw new AlreadyUsedException("Email " + author.getEmail() + " has been already used");

		});
		Author newAuthor = new Author(authorPayload.getFirstName(), authorPayload.getLastName(), authorPayload.getEmail(), authorPayload.getBirthDate());
		if (authorPayload.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
			throw new NotAdultException("The author's birth date is younger than 18 years old");
		} else
			return this.authorRepository.save(newAuthor);
	}
}
