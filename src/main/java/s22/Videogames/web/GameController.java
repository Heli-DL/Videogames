package s22.Videogames.web;

import java.io.IOException;
import java.util.Base64;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import s22.Videogames.domain.DeveloperRepository;
import s22.Videogames.domain.Game;
import s22.Videogames.domain.GameRepository;
import s22.Videogames.domain.GenreRepository;

@Controller
public class GameController {
	private static final Logger log = LoggerFactory.getLogger(GameController.class);
	
	@Value("${upload.path}")
	private String uploadFolder;
	
	@Autowired
	private GameRepository gamerepository;
	
	@Autowired
	private DeveloperRepository devrepository;
	
	@Autowired
	private GenreRepository genrerepository;
	
	@GetMapping(value = { "/main", "/"})
	public String showMainPage() {
		log.info("lets go to the main page");
		return "mainPage";
	}

	@GetMapping("/gamelist")
	public String showGames(Model model) {
		model.addAttribute("games", gamerepository.findAll());
		return "games";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/addGame")
	public String addGame(Model model) {
		model.addAttribute("newGame", new Game());
		model.addAttribute("developers", devrepository.findAll());
		model.addAttribute("genres", genrerepository.findAll());
		return "addGame";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/editGame/{id}")
	public String editGame(@PathVariable("id") Long id, Model model) {
		model.addAttribute("editGame", gamerepository.findById(id));
		model.addAttribute("developers", devrepository.findAll());
		model.addAttribute("genres", genrerepository.findAll());
		return "editGame";
	}
	
	@PostMapping("saveGame")
	public String saveGame(@Valid @ModelAttribute("newGame") Game game, BindingResult bindingResult, MultipartFile file, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("some error happened");
			model.addAttribute("developers", devrepository.findAll());
			model.addAttribute("genres", genrerepository.findAll());
			return "addGame";
		} else if (file.isEmpty()) {
        	model.addAttribute("msg", "Upload failed");
            return "addGame";
        } else {
			try {
				byte[] imageData = file.getBytes();
				String imgStr = Base64.getEncoder().encodeToString(imageData);
				Game game1 = new Game();
				game1.setTitle(game.getTitle());
				game1.setReleaseYear(game.getReleaseYear());
				game1.setDescription(game.getDescription());
				game1.setPrice(game.getPrice());
				game1.setDeveloper(game.getDeveloper());
				game1.setGenre(game.getGenre());
				game1.setImage(imgStr);
				gamerepository.save(game1);
				return "redirect:gamelist";
				} catch (IOException e) {
				e.printStackTrace();
				}
		}
		return "addGame";
	}
	
	@PostMapping("saveEdit")
	public String saveEdit(Game game) {
		gamerepository.save(game);
		return "redirect:gamelist";
	}
		
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("deleteGame/{id}")
	public String deleteGame(@PathVariable("id") Long id, Model model) {
		gamerepository.deleteById(id);
		return "redirect:/gamelist";
	}
}
