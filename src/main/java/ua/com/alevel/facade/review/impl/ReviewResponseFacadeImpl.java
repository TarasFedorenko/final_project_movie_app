package ua.com.alevel.facade.review.impl;


import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.review.ReviewResponseFacade;
import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.service.ReviewService;
import ua.com.alevel.web.dto.response.ReviewResponseDto;

import java.util.Collection;


@Service
public class ReviewResponseFacadeImpl implements ReviewResponseFacade {
    private final ReviewService reviewService;

    public ReviewResponseFacadeImpl(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public Collection<ReviewResponseDto> findAll(WebRequest webRequest) {
        String[] movies = webRequest.getParameterValues("movies");
        if (movies != null) {
            String movieId = movies[0];
            Collection<ReviewResponseDto> responseDtoCollection = null;
            responseDtoCollection.add(new ReviewResponseDto(reviewService.findByMovie(Long.parseLong(movieId))));
            return responseDtoCollection;
        }
        Collection<Review> reviews = reviewService.findAll();
        return reviews.stream().map(ReviewResponseDto::new).toList();
    }
}





