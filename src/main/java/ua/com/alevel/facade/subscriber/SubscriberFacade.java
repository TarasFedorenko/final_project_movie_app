package ua.com.alevel.facade.subscriber;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.web.dto.SubscriberDto;
import ua.com.alevel.web.dto.request.SubscriberRequestDto;
import ua.com.alevel.web.dto.response.SubscriberResponseDto;

import java.util.Collection;

public interface SubscriberFacade {
    SubscriberResponseDto findById(Long id);

    Collection<SubscriberRequestDto> findAll(WebRequest webRequest);

    void update(SubscriberDto subscriberDto);
}

