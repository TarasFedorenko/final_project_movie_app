package ua.com.alevel.web.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.genre.GenreFacade;

@Controller
@RequestMapping("/genres")
public class GenreController {
    private final GenreFacade genreFacade;

    public GenreController(GenreFacade genreFacade) {
        this.genreFacade = genreFacade;
    }
    @GetMapping
    public String plp(Model model, WebRequest webRequest) {
        model.addAttribute("genreList", genreFacade.findAll(webRequest));
        return "pages/open/genre_plp";
    }

    @GetMapping("/{id}")
    public String pdp(Model model, @PathVariable Long id) {
        model.addAttribute("genre", genreFacade.findById(id));
        return "pages/open/genre_pdp";
    }
}
