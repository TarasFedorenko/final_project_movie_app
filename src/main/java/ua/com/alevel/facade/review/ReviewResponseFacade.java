package ua.com.alevel.facade.review;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.web.dto.response.ReviewResponseDto;

import java.util.Collection;

public interface ReviewResponseFacade {

    Collection<ReviewResponseDto> findAll(WebRequest webRequest);


}
