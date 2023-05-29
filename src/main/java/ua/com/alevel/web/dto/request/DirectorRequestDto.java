package ua.com.alevel.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ua.com.alevel.persistence.entity.person.Director;

@Setter
@Getter
@NoArgsConstructor
public class DirectorRequestDto {
    private Long id;
    private String name;
    private String imageUrl;

    public DirectorRequestDto(Director director){
        this.id = director.getId();
        this.name = director.getFirstName() + " " + director.getLastName();
        this.imageUrl = director.getImageUrl();

    }
}
