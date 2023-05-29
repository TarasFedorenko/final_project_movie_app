package ua.com.alevel.web.dto.response;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.persistence.entity.user.Subscriber;
import ua.com.alevel.web.dto.MovieDto;
import ua.com.alevel.web.dto.SubscriberDto;

@Getter
@Setter
public class ReviewResponseDto extends ResponseDto{

    private String message;
    private Double stars;
    private SubscriberDto subscriberDto;
    private MovieDto movieDto;


    public ReviewResponseDto(Review review){
        setId(review.getId());
        setCreated(review.getCreated());
        setUpdated(review.getUpdated());
        setVisible(review.getVisible());
        this.message = review.getMessage();
        this.stars = review.getStars();
        initSubscriber(review);
        initMovie(review);

    }
    private void initSubscriber(Review review){
        Subscriber subscriber = review.getSubscriber();
        if(subscriber !=null){
            this.subscriberDto = new SubscriberDto(subscriber);
        }
    }
    private void initMovie(Review review){
        Movie movie = review.getMovie();
        if(movie !=null){
            this.movieDto = new MovieDto(movie);
        }
    }


}
