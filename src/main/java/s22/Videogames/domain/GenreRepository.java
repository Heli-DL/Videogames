package s22.Videogames.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GenreRepository extends CrudRepository<Genre, Long>{
	List<Genre> findByGenreName(String genreName);
}
