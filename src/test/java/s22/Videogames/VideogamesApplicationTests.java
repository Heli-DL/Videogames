package s22.Videogames;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import s22.Videogames.web.DeveloperController;
import s22.Videogames.web.GameController;
import s22.Videogames.web.GameRestController;
import s22.Videogames.web.GenreController;
import s22.Videogames.web.UserController;

@SpringBootTest
class VideogamesApplicationTests {
	
	@Autowired
	GameController gameController;
	@Autowired
	DeveloperController devController;
	@Autowired
	GenreController genreController;
	@Autowired
	UserController userController;
	@Autowired
	GameRestController restController;
	
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(gameController).isNotNull();
		assertThat(devController).isNotNull();
		assertThat(genreController).isNotNull();
		assertThat(userController).isNotNull();
		assertThat(restController).isNotNull();
	}

}
