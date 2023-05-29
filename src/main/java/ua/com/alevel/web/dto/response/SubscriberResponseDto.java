package ua.com.alevel.web.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.person.Actor;
import ua.com.alevel.persistence.entity.user.Subscriber;
import ua.com.alevel.persistence.type.Gender;
import ua.com.alevel.web.dto.MovieDto;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class SubscriberResponseDto  extends ResponseDto{
    private Long id;
    private Date created;
    private String email;
    private Boolean enabled;
    private String phoneNumber;
    private String userName;
    private Integer age;
    private Gender gender;
    private Set<MovieDto> movieDto;


    public SubscriberResponseDto(Subscriber subscriber){
        this.id= subscriber.getId();
        this.created = subscriber.getCreated();
        this.email = subscriber.getEmail();
        this.enabled = subscriber.getEnabled();
        this.phoneNumber = subscriber.getPhoneNumber();
        this.userName = subscriber.getUsername();
        this.age = subscriber.getAge();
        this.gender =subscriber.getGender();
        initMovie(subscriber);
    }
    private void initMovie(Subscriber subscriber){
        Set<Movie> movies = subscriber.getMovies();
        if (CollectionUtils.isNotEmpty(movies)){
            this.movieDto = movies.stream().map(MovieDto::new).collect(Collectors.toSet());
        }
    }
}
