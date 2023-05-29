package ua.com.alevel.facade.subscriber.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.subscriber.SubscriberFacade;
import ua.com.alevel.persistence.entity.user.Subscriber;
import ua.com.alevel.service.SubscriberCrudService;
import ua.com.alevel.web.dto.SubscriberDto;
import ua.com.alevel.web.dto.request.SubscriberRequestDto;
import ua.com.alevel.web.dto.response.SubscriberResponseDto;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class SubscriberFacadeImpl implements SubscriberFacade {
    private final SubscriberCrudService subscriberService;

    public SubscriberFacadeImpl(SubscriberCrudService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @Override
    public SubscriberResponseDto findById(Long id) {
        Optional<Subscriber> subscriberOptional = subscriberService.findById(id);
        if (subscriberOptional.isEmpty()) {
            throw new RuntimeException("subscriber not exist");
        }
        Subscriber subscriber = subscriberOptional.get();
        return new SubscriberResponseDto(subscriber);
    }

    @Override
    public Collection<SubscriberRequestDto> findAll(WebRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        if (MapUtils.isNotEmpty(map)) {
            String[] movies = map.get("movies");
            if (movies != null) {
                String movieId = movies[0];
                return subscriberService.findAllByMovie(Long.parseLong(movieId)).stream().map(SubscriberRequestDto::new).toList();
            }
        }
        Collection<Subscriber> subscribers = subscriberService.findAll();
        return subscribers.stream().map(SubscriberRequestDto::new).toList();
    }

    public void update(SubscriberDto subscriberDto) {
        Optional<Subscriber> subscriberOptional = subscriberService.findById(subscriberDto.getId());
        if (subscriberOptional.isEmpty()) {
            throw new RuntimeException("subscriber not exist");
        }
        Subscriber subscriber = subscriberOptional.get();
        subscriber.setId(subscriberDto.getId());
        subscriber.setUsername(subscriberDto.getUsername());
        subscriber.setAge(subscriberDto.getAge());
        subscriber.setEmail(subscriberDto.getEmail());
        subscriber.setPhoneNumber(subscriberDto.getPhoneNumber());
        subscriber.setGender(subscriberDto.getGender());
        subscriber.setCountry(subscriberDto.getCountry());
        subscriberService.update(subscriber);
    }
}

