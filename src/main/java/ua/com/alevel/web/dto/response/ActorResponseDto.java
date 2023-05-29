package ua.com.alevel.web.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.person.Actor;
import ua.com.alevel.persistence.type.Gender;
import ua.com.alevel.web.dto.MovieDto;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ActorResponseDto extends ResponseDto{
    private Long id;
    private String name;
    private Integer age;
    private Gender gender;
    private String imageUrl;
    private String biography;
    private Set<MovieDto> movieDto;

    public ActorResponseDto(Actor actor){
        this.id = actor.getId();
        this.name= actor.getFirstName() + " " + actor.getLastName();
        this.age = actor.getAge();
        this.gender = actor.getGender();
        this.imageUrl = actor.getImageUrl();
        this.biography =actor.getBiography();
        initMovie(actor);
    }
    private void initMovie(Actor actor){
        Set<Movie> movies = actor.getMovies();
        if (CollectionUtils.isNotEmpty(movies)){
            this.movieDto = movies.stream().map(MovieDto::new).collect(Collectors.toSet());
        }
    }
}
