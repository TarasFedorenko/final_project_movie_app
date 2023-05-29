package ua.com.alevel.web.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.person.Actor;
import ua.com.alevel.persistence.entity.person.Director;
import ua.com.alevel.persistence.type.Gender;
import ua.com.alevel.web.dto.MovieDto;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class DirectorResponseDto extends ResponseDto {
    private Long id;
    private String name;
    private Integer age;
    private Gender gender;
    private String imageUrl;
    private String biography;
    private Set<MovieDto> movieDto;

    public DirectorResponseDto (Director director){
        this.id = director.getId();
        this.name= director.getFirstName() + " " + director.getLastName();
        this.age = director.getAge();
        this.gender = director.getGender();
        this.imageUrl = director.getImageUrl();
        this.biography =director.getBiography();
        initMovie(director);
    }
    private void initMovie(Director director){
        Set<Movie> movies = director.getMovies();
        if (CollectionUtils.isNotEmpty(movies)){
            this.movieDto = movies.stream().map(MovieDto::new).collect(Collectors.toSet());
        }
    }
}

