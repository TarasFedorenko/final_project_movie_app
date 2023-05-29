package ua.com.alevel.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.persistence.entity.user.Subscriber;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {

    private Long id;
    private Date created;
    private Date updated;
    private String message;
    private Double stars;
    private SubscriberDto subscriberDto;
    private MovieDto movieDto;

    public ReviewDto(Review review) {
        this.id = review.getId();
        this.message = review.getMessage();
        this.stars = review.getStars();
        initSubscriber(review);
        initMovie(review);
    }

    private void initSubscriber(Review review) {
        Subscriber subscriber = review.getSubscriber();
        if (subscriber != null) {
            this.subscriberDto = new SubscriberDto(subscriber);
        }
    }

    private void initMovie(Review review) {
        Movie movie = review.getMovie();
        if (movie != null) {
            this.movieDto = new MovieDto(movie);
        }
    }
}
