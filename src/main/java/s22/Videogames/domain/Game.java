package s22.Videogames.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column (name = "title", nullable = false)
	@NotEmpty
	@Size(min = 1, max = 50)
	private String title;
	
	@Size(min = 10, max = 300)
	private String description;
	
	private double price;

	@Column(name = "release_year")
	@Min(1900)
	private int releaseYear;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "developerid")
	private Developer developer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "genreid")
	private Genre genre;
	
	@Column(name = "gameimg", nullable = true)
	private String image;

	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Game(@NotEmpty @Size(min = 1, max = 50) String title, @Size(min = 10, max = 300) String description,
			double price, @Min(1900) int releaseYear, Developer developer, Genre genre, String image) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.releaseYear = releaseYear;
		this.developer = developer;
		this.genre = genre;
		this.image = image;
	}

	public Game(@NotEmpty @Size(min = 1, max = 50) String title, @Size(min = 10, max = 300) String description,
			double price, @Min(1900) int releaseYear, Developer developer, Genre genre) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.releaseYear = releaseYear;
		this.developer = developer;
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", releaseYear=" + releaseYear + ", developer=" + developer + ", genre=" + genre + ", image=" + image
				+ "]";
	}


	
}
