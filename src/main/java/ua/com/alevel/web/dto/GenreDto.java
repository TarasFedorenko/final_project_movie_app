package ua.com.alevel.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.movie.Genre;


@Getter
@Setter
@NoArgsConstructor
public class GenreDto {

    private Long id;
    private String name;

    public GenreDto(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getGenreName();
    }
}
