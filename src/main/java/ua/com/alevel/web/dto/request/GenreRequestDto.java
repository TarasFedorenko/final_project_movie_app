package ua.com.alevel.web.dto.request;

import lombok.*;
import ua.com.alevel.persistence.entity.movie.Genre;

@Setter
@Getter
@NoArgsConstructor
public class GenreRequestDto extends RequestDto {
    private Long id;
    private String genreName;
    private String imageUrl;
    private Boolean visible;

    public GenreRequestDto(Genre genre) {
        this.id = genre.getId();
        this.genreName = genre.getGenreName();
        this.imageUrl = genre.getImageUrl();
    }
}
