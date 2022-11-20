package s22.Videogames;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import s22.Videogames.domain.AppUser;
import s22.Videogames.domain.AppUserRepository;
import s22.Videogames.domain.Developer;
import s22.Videogames.domain.DeveloperRepository;
import s22.Videogames.domain.Game;
import s22.Videogames.domain.GameRepository;
import s22.Videogames.domain.Genre;
import s22.Videogames.domain.GenreRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RespositoryTests {
	
	@Autowired
	GameRepository gameRepository;
	@Autowired
	DeveloperRepository devRepository;
	@Autowired
	GenreRepository genreRepository;
	@Autowired
	AppUserRepository userRepository;
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	//GAMES
	@Test
	public void findByTitleShouldReturnGame() {
		List<Game> games = gameRepository.findByTitle("Elden Ring");
		assertThat(games).hasSize(1);
		assertThat(games.get(0).getReleaseYear()).isEqualTo(2022);
	}
	
	@Test
	public void createNewGame() {
		Game game = new Game("Black Desert Online", "Black Desert is an open-world MMORPG", 19.90, 2015, devRepository.findByDevName("Pearl Abyss").get(0),
					genreRepository.findByGenreName("MMO").get(0));
		gameRepository.save(game);
		assertThat(game.getId()).isNotNull();
	}
	
	@Test
	public void deleteGame() {
		Game game = new Game("BDO", "Black Desert is an open-world MMORPG", 19.90, 2015, devRepository.findByDevName("Pearl Abyss").get(0),
				genreRepository.findByGenreName("MMO").get(0));
		gameRepository.save(game);
		gameRepository.deleteById(game.getId());
		List<Game> games = gameRepository.findByTitle("BDO");
		assertEquals(games.size(), 0);
		
	}
	
	//DEVELOPERS
	@Test
	public void findByDevNameShouldReturnDeveloper() {
		List<Developer> devs = devRepository.findByDevName("Epic Games");
		assertThat(devs).hasSize(1);
		assertThat(devs.get(0).getCountry()).isEqualTo("United States");
	}
	
	@Test
	public void createNewDev() {
		Developer dev = new Developer("Rockstar games", "United States", 1998);
		devRepository.save(dev);
		assertThat(dev.getId()).isNotNull();
	}
	
	public void deleteDev() {
		Developer dev = new Developer("Ubisoft", "France", 1986);
		devRepository.save(dev);
		devRepository.deleteById(dev.getId());
		List<Developer> devs = devRepository.findByDevName("Ubisoft");
		assertEquals(devs.size(), 0);
	}	
	
	//GENRES
	@Test
	public void findByGenreNameShouldReturnGenre() {
		List<Genre> genres = genreRepository.findByGenreName("MMO");
		assertThat(genres).hasSize(1);
	}
	
	@Test
	public void createNewGenre() {
		Genre genre = new Genre("Co-op");
		genreRepository.save(genre);
		assertThat(genre.getId()).isNotNull();
	}
	
	@Test
	public void deleteGenre() {
		Genre genre = new Genre("Horror");
		genreRepository.save(genre);
		genreRepository.deleteById(genre.getId());
		List<Genre> genres = genreRepository.findByGenreName("Horror");
		assertEquals(genres.size(), 0);
	}	
	
	//USERS
	@Test
	public void findByUsernameShouldReturnUser() {
		AppUser user = userRepository.findByUsername("admin");
		assertEquals(user.getFirstName(), "Heli");
	}
	
	@Test
	public void createNewUser() {
		String pwd = "user123";
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		String hashPwd = bc.encode(pwd);
		AppUser user = new AppUser("Testi", "Testinen", "USER", "user123", hashPwd );
		userRepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteUser() {
		String pwd = "user222";
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		String hashPwd = bc.encode(pwd);
		AppUser user = new AppUser("Testi", "Testinen", "USER", "user222", hashPwd );
		userRepository.save(user);
		userRepository.deleteById(user.getId());
		AppUser users = userRepository.findByUsername("user222");
		assertThat(users).isNull();
	}	
}