package ua.com.alevel.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.persistence.entity.user.Subscriber;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class SubscriberRequestDto extends RequestDto {
    private Long id;
    private Date created;
    private String email;
    private Boolean enabled;

    public SubscriberRequestDto(Subscriber subscriber) {
        this.id = subscriber.getId();
        this.created = subscriber.getCreated();
        this.email = subscriber.getEmail();
        this.enabled = subscriber.getEnabled();
    }
}
