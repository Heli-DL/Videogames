package s22.Videogames.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import s22.Videogames.domain.AppUser;
import s22.Videogames.domain.AppUserRepository;
import s22.Videogames.domain.Developer;
import s22.Videogames.domain.DeveloperRepository;
import s22.Videogames.domain.Game;
import s22.Videogames.domain.GameRepository;
import s22.Videogames.domain.Genre;
import s22.Videogames.domain.GenreRepository;


@RestController
public class GameRestController {
	
	@Autowired
	GameRepository gameRepository;
	@Autowired
	DeveloperRepository devRepository;
	@Autowired
	GenreRepository genreRepository;
	@Autowired
	AppUserRepository userRepository;

	//GAMES
	// return list of games
	@GetMapping("/games")
	public Iterable<Game> getGames() {
		// fetch and return games
		return gameRepository.findAll();
	}

	// add new game
	@PostMapping("games")
	Game newGame(@RequestBody Game newGame) {
		return gameRepository.save(newGame);
	}

	// edit existing game information
	@PutMapping("/games/{id}")
	Game editGame(@RequestBody Game editedGame, @PathVariable Long id) {
		editedGame.setId(id);
		return gameRepository.save(editedGame);
	}

	// delete game
	@DeleteMapping("/games/{id}")
	void deleteGame(@PathVariable Long id) {
		gameRepository.deleteById(id);
	}
	
	
	// DEVELOPERS
	// return list of developers
	@GetMapping("/developers")
	public Iterable<Developer> getDevelopers() {
		// fetch and return developers
		return devRepository.findAll();
	}

	// add new developer
	@PostMapping("developers")
	Developer newDeveloper(@RequestBody Developer newDev) {
		return devRepository.save(newDev);
	}

	// delete developer
	@DeleteMapping("/developers/{id}")
	void deleteDeveloper(@PathVariable Long id) {
		devRepository.deleteById(id);
	}
	
	// GENRES
	// return list of genres
	@GetMapping("/genres")
	public Iterable<Genre> getGenres() {
		// fetch and return genres
		return genreRepository.findAll();
	}

	// add new genre
	@PostMapping("genres")
	Genre newGenre(@RequestBody Genre newGenre) {
		return genreRepository.save(newGenre);
	}

	// delete genre
	@DeleteMapping("/genres/{id}")
	void deleteGenre(@PathVariable Long id) {
		genreRepository.deleteById(id);
	}
	
	// USERS
	// return list of users
	@GetMapping("/users")
	public Iterable<AppUser> getUsers() {
		// fetch and return users
		return userRepository.findAll();
	}

	// add new user
	@PostMapping("users")
	AppUser newUser(@RequestBody AppUser newUser) {
		return userRepository.save(newUser);
	}

	// delete user
	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
	}
}
