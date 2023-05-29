package ua.com.alevel.web.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.movie.Genre;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.person.Actor;
import ua.com.alevel.persistence.entity.person.Director;
import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.web.dto.ReviewDto;
import ua.com.alevel.web.dto.ActorDto;
import ua.com.alevel.web.dto.DirectorDto;
import ua.com.alevel.web.dto.GenreDto;

import java.sql.Time;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MovieResponseDto extends ResponseDto {

    private String title;
    private String imageMovie;
    private Time duration;
    private Integer releaseYear;
    private String description;
    private String imdbId;
    private String trailerUrl;
    private Double averageRating;
    private Set<ActorDto> actors;
    private Set<GenreDto> genres;
    private Set<ReviewDto> reviews;
    private DirectorDto directorDto;


    public MovieResponseDto(Movie movie) {
        setId(movie.getId());
        setCreated(movie.getCreated());
        setUpdated(movie.getUpdated());
        setVisible(movie.getVisible());
        this.title = movie.getTitle();
        this.imageMovie = movie.getImageMovie();
        this.duration = movie.getDuration();
        this.releaseYear = movie.getReleaseYear();
        this.description = movie.getDescription();
        this.imdbId = movie.getImdbId();
        this.trailerUrl = movie.getTrailerUrl();
        this.averageRating = movie.getAverageRating();
        initActors(movie);
        initDirector(movie);
        initGenres(movie);
        initReview(movie);
    }

    private void initActors(Movie movie) {
        Set<Actor> actors = movie.getActors();
        if (CollectionUtils.isNotEmpty(actors)) {
            this.actors = actors.stream().map(ActorDto::new).collect(Collectors.toSet());
        }
    }
    private void initReview(Movie movie) {
        Set<Review> reviews = movie.getReviews();
        if (CollectionUtils.isNotEmpty(reviews)) {
            this.reviews = reviews.stream().map(ReviewDto::new).collect(Collectors.toSet());
        }
    }
    private void initDirector(Movie movie) {
        Director director = movie.getDirector();
        if (director != null) {
            this.directorDto = new DirectorDto(director);
        }
    }
    private void initGenres(Movie movie) {
        Set<Genre> genres = movie.getGenres();
        if (CollectionUtils.isNotEmpty(genres)) {
            this.genres = genres.stream().map(GenreDto::new).collect(Collectors.toSet());
        }
    }

}
