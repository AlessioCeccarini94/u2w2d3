package alessioceccarini.u2w2d3.services;

import alessioceccarini.u2w2d3.entities.Author;
import alessioceccarini.u2w2d3.exceptions.AlreadyUsedException;
import alessioceccarini.u2w2d3.exceptions.NotAdultException;
import alessioceccarini.u2w2d3.exceptions.NotFoundEception;
import alessioceccarini.u2w2d3.payloads.AuthorDTO;
import alessioceccarini.u2w2d3.payloads.AuthorPayload;
import alessioceccarini.u2w2d3.repositories.AuthorRepository;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class AuthorService {

	private final AuthorRepository authorRepository;
	private final Cloudinary cloudinary;

	@Autowired
	public AuthorService(AuthorRepository authorRepository, Cloudinary cloudinary) {
		this.authorRepository = authorRepository;
		this.cloudinary = cloudinary;
	}

	//------------------------------------ G E T ----------------------------------------------

	// GET ALL AUTHORS AND ADD PAGINATION
	public Page<Author> findAll(int page, int size, String sortBy) {
		if (page < 0) page = 0;
		if (size < 0 || size > 150) size = 10;
		if (sortBy == null) sortBy = "title";
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
		return this.authorRepository.findAll(pageable);
	}

	public Author findAuthorById(UUID authorId) {
		return this.authorRepository.findById(authorId).orElseThrow(() -> new NotFoundEception("author not found"));
	}

	//------------------------------------ P O S T ----------------------------------------------

	// ADD NEW AUTHOR AND CHECK EMAIL IS NOT USED YET
	public Author save(AuthorDTO authorPayload) {
		this.authorRepository.findByEmail(authorPayload.email()).ifPresent(author -> {
			throw new AlreadyUsedException("Email " + author.getEmail() + " has been already used");

		});
		Author newAuthor = new Author(authorPayload.firstName(), authorPayload.lastName(), authorPayload.email(), authorPayload.birthDate());
		if (authorPayload.birthDate().isAfter(LocalDate.now().minusYears(18))) {
			throw new NotAdultException("The author's birth date is younger than 18 years old");
		} else
			return this.authorRepository.save(newAuthor);
	}

	//------------------------------------- P U T  ----------------------------------------------
	public Author updateAuthor(UUID authorId, @RequestBody AuthorPayload authorPayload) {
		Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new NotFoundEception("author not found"));
		authorPayload.setFirstName(authorPayload.getFirstName());
		authorPayload.setLastName(authorPayload.getLastName());
		authorPayload.setEmail(authorPayload.getEmail());
		authorPayload.setBirthDate(authorPayload.getBirthDate());
		return authorRepository.save(author);

	}
//
//
//	public Author updateAvatar(MultipartFile file, UUID authorId) {
//		Author author = this.findAuthorById(authorId);
//		try {
//			Map result = cloudinary.uploader();
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}
}
