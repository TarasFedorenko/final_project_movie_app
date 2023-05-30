package ua.com.alevel.web.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.facade.review.ReviewFacade;
import ua.com.alevel.web.dto.request.ReviewRequestDto;

@Controller
@RequestMapping("/movies")
public class ReviewController {
    private final ReviewFacade reviewFacade;


    public ReviewController(ReviewFacade reviewFacade) {
        this.reviewFacade = reviewFacade;

    }

    @PostMapping("/{id}/review")
    public String addReview(@ModelAttribute("reviews") ReviewRequestDto reviewDto, @PathVariable("id") Long id) {
        reviewFacade.create(reviewDto, id);
        return "redirect:/movies/" + id;
    }
}
