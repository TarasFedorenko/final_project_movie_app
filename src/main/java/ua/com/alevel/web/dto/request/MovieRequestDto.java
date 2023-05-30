package ua.com.alevel.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.movie.Movie;

import java.sql.Time;

@Getter
@Setter
@ToString

public class MovieRequestDto extends RequestDto {

    private String title;
    private Time duration;
    private Integer releaseYear;
    private String description;
    private String imageMovie;


    public MovieRequestDto(Movie movie){
        this.title = movie.getTitle();
        this.duration =movie.getDuration();
        this.releaseYear = movie.getReleaseYear();
        this.imageMovie =getImageMovie();
    }
}