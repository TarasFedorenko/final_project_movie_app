package ua.com.alevel.web.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.movie.Genre;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.web.dto.MovieDto;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class GenreResponseDto extends ResponseDto {

    private String genreName;
    private String imageUrl;
    private Set<MovieDto> movieDto;

    public GenreResponseDto(Genre genre) {
        setId(genre.getId());
        setCreated(genre.getCreated());
        setUpdated(genre.getUpdated());
        setVisible(genre.getVisible());
        this.genreName = genre.getGenreName();
        this.imageUrl = genre.getImageUrl();
        initMovie(genre);
    }

    private void initMovie(Genre genre) {
        Set<Movie> movies = genre.getMovies();
        if (CollectionUtils.isNotEmpty(movies)) {
            this.movieDto = movies.stream().map(MovieDto::new).collect(Collectors.toSet());
        }
    }
}
