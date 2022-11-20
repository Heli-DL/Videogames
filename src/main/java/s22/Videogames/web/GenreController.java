package s22.Videogames.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import s22.Videogames.domain.Genre;
import s22.Videogames.domain.GenreRepository;

@Controller
public class GenreController {
	
	@Autowired
	GenreRepository genrerepository;

	@GetMapping("/genrelist")
	public String getGenres(Model model) {
		model.addAttribute("genres", genrerepository.findAll());
		return "genres";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/addGenre")
	public String addGenre(Model model) {
		model.addAttribute("newGenre", new Genre());
		return "addGenre";
	}

	@PostMapping("saveGenre")
	public String saveGenre(@Valid @ModelAttribute("newGenre") Genre genre, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("some error happened");
			return "addGenre";
		}
		genrerepository.save(genre);
		return "redirect:genrelist";

	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("deleteGenre/{id}")
	public String deleteGenre(@PathVariable("id") Long id, Model model) {
		genrerepository.deleteById(id);
		return "redirect:/genrelist";
	}
}
