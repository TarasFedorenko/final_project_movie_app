package ua.com.alevel.persistence.entity.person;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.type.Gender;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class BasePerson extends BaseEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;
    @Column(name = "image_url", nullable = false, unique = true)
    private String imageUrl;
    @Column(name = "biography", nullable = false, unique = true, columnDefinition = "TEXT")
    private String biography;


}
