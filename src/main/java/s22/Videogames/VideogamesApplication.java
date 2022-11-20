package s22.Videogames;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import s22.Videogames.domain.AppUserRepository;
import s22.Videogames.domain.Game;
import s22.Videogames.domain.GameRepository;




@SpringBootApplication
public class VideogamesApplication implements CommandLineRunner {


	private static final Logger log = LoggerFactory.getLogger(VideogamesApplication.class);

	@Autowired
	GameRepository gamerepository;
	
	@Autowired
	AppUserRepository userrepository;


	public static void main(String[] args) {
		SpringApplication.run(VideogamesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
			
			/*gamerepository.save(new Game("Elden Ring", "FromSoftware", 2022, "Elden Ring is a fantasy, action and open world game with RPG elements such as stats, weapons and spells.",
					49.90));*/
		
		
			
			log.info("fetch all games");
			for (Game game : gamerepository.findAll()) {
				log.info(game.toString());
			} 
	}

}
