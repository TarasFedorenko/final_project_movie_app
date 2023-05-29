package ua.com.alevel.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.person.Actor;

@Getter
@Setter
@NoArgsConstructor
public class ActorDto {

    private Long id;
    private String name;

    public ActorDto(Actor actor) {
        this.id = actor.getId();
        this.name = actor.getFirstName() + " " + actor.getLastName();
    }
}