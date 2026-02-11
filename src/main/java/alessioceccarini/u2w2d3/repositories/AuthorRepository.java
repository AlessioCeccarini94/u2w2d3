package alessioceccarini.u2w2d3.repositories;

import alessioceccarini.u2w2d3.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
	Optional<Author> findByEmail(String email);

}
