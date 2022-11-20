package s22.Videogames.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DeveloperRepository extends CrudRepository<Developer, Long>{
	List<Developer> findByDevName(String devName);
}
