package ua.com.alevel.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.user.Subscriber;
import ua.com.alevel.persistence.type.Gender;

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

    public SubscriberDto(Subscriber subscriber) {
        this.id = subscriber.getId();
        this.username = subscriber.getUsername();
        this.email = subscriber.getEmail();
        this.age = subscriber.getAge();
        this.gender = subscriber.getGender();
        this.phoneNumber = subscriber.getPhoneNumber();
        this.country = subscriber.getCountry();
    }
}
