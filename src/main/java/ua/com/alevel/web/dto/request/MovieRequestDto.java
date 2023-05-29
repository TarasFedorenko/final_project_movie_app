package ua.com.alevel.web.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;

@Getter
@Setter
@ToString
public class MovieRequestDto extends RequestDto {

    private String title;
    private Time duration;
    private Integer releaseYear;
    private String description;
    private String imageMovie;

}