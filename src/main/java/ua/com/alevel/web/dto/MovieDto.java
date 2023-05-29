package ua.com.alevel.web.dto;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.movie.Movie;

import java.sql.Time;

@Getter
@Setter
public class MovieDto {
    private Long id;
    private String title;
    private String imageMovie;
    private Time duration;
    private Integer releaseYear;
    private String description;
    private String imdbId;
    private String trailerUrl;
    private Double averageRating;

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.imageMovie = movie.getImageMovie();
        this.duration = movie.getDuration();
        this.releaseYear = movie.getReleaseYear();
        this.description = movie.getDescription();
        this.imdbId = movie.getImdbId();
        this.trailerUrl = movie.getTrailerUrl();
        this.averageRating = movie.getAverageRating();
    }
}
