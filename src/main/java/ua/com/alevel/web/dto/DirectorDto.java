package ua.com.alevel.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.person.Director;

@Getter
@Setter
@NoArgsConstructor
public class DirectorDto {

    private Long id;
    private String name;

    public DirectorDto(Director director) {
        this.id = director.getId();
        this.name = director.getFirstName() + " " + director.getLastName();
    }
}
