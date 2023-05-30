package ua.com.alevel.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.person.Actor;
import ua.com.alevel.persistence.entity.user.Subscriber;
import ua.com.alevel.persistence.type.Gender;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SubscriberDto {
    private Long id;
    private String username;
    private String email;
    private Integer age;
    private Gender gender;
    private String phoneNumber;
    private String country;
    Set<MovieDto> movieDto;

    public SubscriberDto(Subscriber subscriber) {
        this.id = subscriber.getId();
        this.username = subscriber.getUsername();
        this.email = subscriber.getEmail();
        this.age = subscriber.getAge();
        this.gender = subscriber.getGender();
        this.phoneNumber = subscriber.getPhoneNumber();
        this.country = subscriber.getCountry();
        initMovie(subscriber);
    }
    private void initMovie(Subscriber subscriber) {
        Set<Movie> movies = subscriber.getMovies();
        if (CollectionUtils.isNotEmpty(movies)) {
            this.movieDto = movies.stream().map(MovieDto::new).collect(Collectors.toSet());
        }
    }
}
