package ua.com.alevel.facade.director;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.web.dto.request.DirectorRequestDto;
import ua.com.alevel.web.dto.response.DirectorResponseDto;


import java.util.Collection;

public interface DirectorFacade {
    DirectorResponseDto findById(Long id);

    Collection<DirectorRequestDto> findAll(WebRequest webRequest);
}
