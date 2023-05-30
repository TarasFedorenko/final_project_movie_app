package ua.com.alevel.web.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.director.DirectorFacade;

@Controller
@RequestMapping("/directors")
public class DirectorController {
    private final DirectorFacade directorFacade;

    public DirectorController(DirectorFacade directorFacade) {
        this.directorFacade = directorFacade;
    }

    @GetMapping
    public String plp(Model model, WebRequest webRequest) {
        model.addAttribute("directorList", directorFacade.findAll(webRequest));
        return "pages/open/director_plp";
    }

    @GetMapping("/{id}")
    public String pdp(Model model, @PathVariable Long id) {
        model.addAttribute("director", directorFacade.findById(id));
        return "pages/open/director_pdp";
    }

}
