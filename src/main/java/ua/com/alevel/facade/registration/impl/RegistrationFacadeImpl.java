package ua.com.alevel.facade.registration.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.registration.RegistrationFacade;

import ua.com.alevel.persistence.entity.user.Subscriber;
import ua.com.alevel.service.SubscriberCrudService;
import ua.com.alevel.web.dto.request.register.AuthDto;

@Service
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final SubscriberCrudService subscriberCrudService;

    public RegistrationFacadeImpl(SubscriberCrudService subscriberCrudService) {
        this.subscriberCrudService = subscriberCrudService;
    }

    @Override
    public void registration(AuthDto dto) {
        Subscriber subscriber = new Subscriber();
        subscriber.setEmail(dto.getEmail());
        subscriber.setPassword(dto.getPassword());
        subscriberCrudService.create(subscriber);
    }
}