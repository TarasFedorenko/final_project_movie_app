package ua.com.alevel.web.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.actor.ActorFacade;

@Controller
@RequestMapping("/actors")
public class ActorController {
   private final ActorFacade actorFacade;

    public ActorController(ActorFacade actorFacade) {
        this.actorFacade = actorFacade;
    }
    @GetMapping
    public String plp(Model model, WebRequest webRequest) {
        model.addAttribute("actorList", actorFacade.findAll(webRequest));
        return "pages/open/actor_plp";
    }

    @GetMapping("/{id}")
    public String pdp(Model model, @PathVariable Long id) {
        model.addAttribute("actor", actorFacade.findById(id));
        return "pages/open/actor_pdp";
    }
}
