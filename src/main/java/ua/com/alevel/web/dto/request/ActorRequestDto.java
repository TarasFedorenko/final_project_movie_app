package ua.com.alevel.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.person.Actor;

@Setter
@Getter
@NoArgsConstructor
public class ActorRequestDto {
    private Long id;
    private String name;
    private String imageUrl;

    public ActorRequestDto(Actor actor) {
        this.id = actor.getId();
        this.name = actor.getFirstName() + " " + actor.getLastName();
        this.imageUrl = actor.getImageUrl();

    }
}
