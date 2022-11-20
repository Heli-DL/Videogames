package s22.Videogames.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Genre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="genre")
	@NotEmpty
	@Size(min = 2, max = 50)
	private String genreName;
	
	@JsonIgnore // this prevents the infinite loop in our cars REST service
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
	private List<Game> games;

	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Genre(Long id, @NotEmpty @Size(min = 2, max = 50) String genreName, List<Game> games) {
		super();
		this.id = id;
		this.genreName = genreName;
		this.games = games;
	}

	public Genre(@NotEmpty @Size(min = 2, max = 50) String genreName) {
		super();
		this.genreName = genreName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", genreName=" + genreName + "]";
	}

	
	
	
}
