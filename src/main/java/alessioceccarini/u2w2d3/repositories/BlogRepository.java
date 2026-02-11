package alessioceccarini.u2w2d3.repositories;

import alessioceccarini.u2w2d3.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogRepository extends JpaRepository<Blog, UUID> {
}
