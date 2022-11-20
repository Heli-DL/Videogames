package s22.Videogames.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Developer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "dev_name", nullable = false)
	@NotEmpty
	@Size(min = 2, max = 50)
	private String devName;
	
	@NotEmpty
	@Size(min = 3, max = 50)
	private String country;
	
	@Column(name = "year_founded")
	@Min(1900)
	private int yearFounded;
	
	@JsonIgnore // this prevents the infinite loop in our cars REST service
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "developer")
	private List<Game> games;

	public Developer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Developer(Long id, @NotEmpty @Size(min = 2, max = 50) String devName,
			@NotEmpty @Size(min = 3, max = 50) String country, @Min(1900) int yearFounded, List<Game> games) {
		super();
		this.id = id;
		this.devName = devName;
		this.country = country;
		this.yearFounded = yearFounded;
		this.games = games;
	}

	public Developer(@NotEmpty @Size(min = 2, max = 50) String devName,
			@NotEmpty @Size(min = 3, max = 50) String country, @Min(1900) int yearFounded) {
		super();
		this.devName = devName;
		this.country = country;
		this.yearFounded = yearFounded;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(int yearFounded) {
		this.yearFounded = yearFounded;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	@Override
	public String toString() {
		return "Developer [id=" + id + ", devName=" + devName + ", country=" + country + ", yearFounded=" + yearFounded
				+ "]";
	}
	
}
