package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.persistence.type.Gender;
import ua.com.alevel.persistence.type.RoleType;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@DiscriminatorValue("SUBSCRIBER")
public class Subscriber extends User {
    @Column(name = "age")
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    @Column
    private String country;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "subscribers")
    private Set<Movie> movies;
    @OneToMany(mappedBy = "subscriber")
    private Set<Review> reviews;

    public Subscriber() {
        super();
        setRoleType(RoleType.ROLE_SUBSCRIBER);
    }
}
