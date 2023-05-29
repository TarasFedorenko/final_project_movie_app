package ua.com.alevel.facade.actor;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.web.dto.request.ActorRequestDto;
import ua.com.alevel.web.dto.response.ActorResponseDto;


import java.util.Collection;

public interface ActorFacade {
    ActorResponseDto findById(Long id);

    Collection<ActorRequestDto> findAll(WebRequest webRequest);
}
