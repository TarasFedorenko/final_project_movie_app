package ua.com.alevel.facade.plp;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.web.dto.MoviePLPDto;

import java.util.List;

public interface PLPFacade {

    List<MoviePLPDto> search(WebRequest webRequest);

    List<String> searchTitle(String query);
}