package ua.com.alevel.web.controller.subscriber;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ua.com.alevel.facade.movie.MovieFacade;
import ua.com.alevel.facade.review.ReviewFacade;
import ua.com.alevel.facade.subscriber.SubscriberFacade;
import ua.com.alevel.service.SubscriberCrudService;
import ua.com.alevel.web.dto.SubscriberDto;

@Controller
@RequestMapping("/subscriber")
public class SubscriberController {
    private final SubscriberCrudService subscriberCrudService;
    private final SubscriberFacade subscriberFacade;
    private final ReviewFacade reviewFacade;

    private final MovieFacade movieFacade;

    public SubscriberController(SubscriberCrudService subscriberCrudService, SubscriberFacade subscriberFacade, ReviewFacade reviewFacade, MovieFacade movieFacade) {
        this.subscriberCrudService = subscriberCrudService;
        this.subscriberFacade = subscriberFacade;
        this.reviewFacade = reviewFacade;
        this.movieFacade = movieFacade;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("subscriber", getSubscriber());
        return "pages/subscriber/home";
    }

    @PostMapping("/update/{id}")
    public String updateUserInfo(@ModelAttribute("subscriber") SubscriberDto updatedSub, @PathVariable Long id) {
        updatedSub.setId(id);
        subscriberFacade.update(updatedSub);
        return "redirect:/subscriber/home";
    }

    @GetMapping("/review/{id}")
    public String subReview(Model model, @PathVariable Long id) {
        model.addAttribute("subscriber", getSubscriber());
        model.addAttribute("review_list", reviewFacade.findAllBySubscriber(id));
        return "pages/subscriber/sub_review";
    }

    @GetMapping("/review/delete/{id}")
    public String delete(@PathVariable Long id) {
        SubscriberDto subscriberDto = getSubscriber();
        Long subId = subscriberDto.getId();
        reviewFacade.delete(id);
        return "redirect:/subscriber/review/" + subId;
    }

    @GetMapping("/movies/{id}")
    public String subMovies(Model model, @PathVariable Long id) {
        model.addAttribute("subscriber", getSubscriber());
        model.addAttribute("movie_list", movieFacade.findAllBySubscriber(id));
        return "pages/subscriber/sub_movie";
    }

    @GetMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        SubscriberDto subscriberDto = getSubscriber();
        Long subId = subscriberDto.getId();
        subscriberCrudService.removeMovieFromSubscriber(subId, id);
        return "redirect:/subscriber/movies/" + subId;
    }


    private SubscriberDto getSubscriber() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return subscriberCrudService.findByEmail(email);
    }

}


