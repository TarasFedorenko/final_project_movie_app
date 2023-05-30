package ua.com.alevel.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.review.ReviewFacade;
import ua.com.alevel.facade.review.ReviewResponseFacade;
import ua.com.alevel.facade.subscriber.SubscriberFacade;
import ua.com.alevel.service.ActorService;
import ua.com.alevel.service.DirectorService;
import ua.com.alevel.service.MovieService;
import ua.com.alevel.service.SubscriberCrudService;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final SubscriberCrudService subscriberService;
    private final MovieService movieService;
    private final ActorService actorService;
    private final SubscriberFacade subscriberFacade;
    private final DirectorService directorService;
    private final ReviewResponseFacade reviewResponseFacade;

    private final ReviewFacade reviewFacade;

    public AdminController(SubscriberCrudService subscriberService, MovieService movieService, ActorService actorService, SubscriberFacade subscriberFacade, DirectorService directorService, ReviewResponseFacade reviewResponseFacade, ReviewFacade reviewFacade) {
        this.subscriberService = subscriberService;
        this.movieService = movieService;
        this.actorService = actorService;
        this.subscriberFacade = subscriberFacade;
        this.directorService = directorService;
        this.reviewResponseFacade = reviewResponseFacade;
        this.reviewFacade = reviewFacade;
    }

    @GetMapping("/home")
    public String dashboard(Model model) {
        Integer subCount = subscriberService.count();
        Integer movieCount = movieService.count();
        Integer actorCount = actorService.count();
        Integer directorCount = directorService.count();
        model.addAttribute("subCount", subCount);
        model.addAttribute("movieCount", movieCount);
        model.addAttribute("actorCount", actorCount);
        model.addAttribute("directorCount", directorCount);
        return "pages/admin/home";
    }

    @GetMapping("/users")
    public String usersList(Model model, WebRequest webRequest) {
        model.addAttribute("usersList", subscriberFacade.findAll(webRequest));
        return "pages/admin/users_list";
    }

    @PostMapping("/users/{id}/ban")
    public String banUser(@PathVariable Long id) {
        subscriberService.banUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/unban")
    public String unbanUser(@PathVariable Long id) {
        subscriberService.unbanUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/review")
    public String reviewList(Model model, WebRequest webRequest) {
        model.addAttribute("reviewList", reviewResponseFacade.findAll(webRequest));
        return "pages/admin/review_list";
    }

    @GetMapping("/review/delete/{id}")
    public String delete(@PathVariable Long id) {
        reviewFacade.delete(id);
        return "redirect:/admin/review";
    }

}