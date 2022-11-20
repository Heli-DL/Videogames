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

import s22.Videogames.domain.Developer;
import s22.Videogames.domain.DeveloperRepository;

@Controller
public class DeveloperController {
	
	@Autowired
	DeveloperRepository devrepository;

	@GetMapping("/developerlist")
	public String getDevelopers(Model model) {
		model.addAttribute("developers", devrepository.findAll());
		return "developers";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/addDeveloper")
	public String addDeveloper(Model model) {
		model.addAttribute("newDeveloper", new Developer());
		return "addDeveloper";
	}

	@PostMapping("saveDeveloper")
	public String saveDeveloper(@Valid @ModelAttribute("newDeveloper") Developer developer, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("some error happened");
			return "addDeveloper";
		}
		devrepository.save(developer);
		return "redirect:developerlist";

	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("deleteDeveloper/{id}")
	public String deleteDeveloper(@PathVariable("id") Long id, Model model) {
		devrepository.deleteById(id);
		return "redirect:/developerlist";
	}
}
