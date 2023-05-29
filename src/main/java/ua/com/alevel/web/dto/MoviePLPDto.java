package ua.com.alevel.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.movie.Genre;
import ua.com.alevel.persistence.entity.movie.Movie;

import java.sql.Time;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class MoviePLPDto {

    private Long id;
    private String title;
    private String imageMovie;
    private Time duration;
    private Integer releaseYear;
    private Double averageRating;
    private Set<String> actors;
    private Set<String> genres;
    private Director director;

    public MoviePLPDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.imageMovie = movie.getImageMovie();
        this.duration = movie.getDuration();
        this.releaseYear = movie.getReleaseYear();
        this.averageRating = movie.getAverageRating();
        if (CollectionUtils.isNotEmpty(movie.getActors())) {
            this.actors = movie.getActors()
                    .stream()
                    .map(author -> author.getFirstName() + " " + author.getLastName())
                    .collect(Collectors.toSet());
        }
        if (CollectionUtils.isNotEmpty(movie.getGenres())) {
            this.genres = movie.getGenres()
                    .stream()
                    .map(Genre::getGenreName)
                    .collect(Collectors.toSet());
        }
        if (movie.getDirector() != null) {
            this.director = new Director(
                    movie.getDirector().getId(),
                    movie.getDirector().getFirstName(),
                    movie.getDirector().getLastName());
        }
    }

    private record Director(Long id, String lastName, String firstName) {
        public Long getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }
}
