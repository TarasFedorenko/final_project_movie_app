package ua.com.alevel.web.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.persistence.entity.user.Subscriber;

@Getter
@Setter
@ToString
public class ReviewRequestDto extends RequestDto{
    private Long id;
    private String message;
    private Double stars;



}
