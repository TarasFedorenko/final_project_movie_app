package ua.com.alevel.web.controller.open;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.facade.movie.MovieFacade;
import ua.com.alevel.facade.plp.PLPFacade;
import ua.com.alevel.service.SubscriberCrudService;
import ua.com.alevel.util.WebUtil;
import ua.com.alevel.web.dto.ReviewDto;
import ua.com.alevel.web.dto.SubscriberDto;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final PLPFacade plpFacade;
    private final MovieFacade movieFacade;
    private final SubscriberCrudService subscriberCrudService;

    public MovieController(PLPFacade plpFacade, MovieFacade movieFacade, SubscriberCrudService subscriberCrudService) {
        this.plpFacade = plpFacade;
        this.movieFacade = movieFacade;

        this.subscriberCrudService = subscriberCrudService;
    }

    @GetMapping
    public String allMovies(Model model, WebRequest webRequest) {
        model.addAttribute("date", new Input());
        model.addAttribute("movieList", plpFacade.search(webRequest));
        return "pages/open/movie_plp";
    }

    @PostMapping("date")
    public String date(@ModelAttribute("date") Input date) {
        System.out.println("date = " + date);
        return "redirect:/movies";
    }

    @GetMapping("/suggestions")
    private @ResponseBody List<String> allSearchMovies(@RequestParam String query) {
        return plpFacade.searchTitle(query);
    }


    @PostMapping("/search")
    private String searchMovies(@RequestParam String query, RedirectAttributes ra) {
        ra.addAttribute(WebUtil.SEARCH_MOVIE_PARAM, query);
        return "redirect:/movies";
    }

    @GetMapping("/{id}")
    public String pdp(Model model, @PathVariable Long id) {
        model.addAttribute("movieData", movieFacade.findById(id));
        model.addAttribute("subscriber", getSubscriber());
        model.addAttribute("reviews", new ReviewDto());
        return "pages/open/movie_pdp";
    }

    @PostMapping("/{id}/add")
    public String addMovieToList(@PathVariable Long id, @RequestParam("subId") Long subId) {
        subscriberCrudService.addMovieToSubscriber(subId,id);
        return "redirect:/movies/{id}";
    }

    private SubscriberDto getSubscriber() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return subscriberCrudService.findByEmail(email);
    }

    public class Input {
        private String date;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "Input{" +
                    "date=" + date +
                    '}';
        }
    }
}