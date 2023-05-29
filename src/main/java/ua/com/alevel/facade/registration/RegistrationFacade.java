package ua.com.alevel.facade.registration;

import ua.com.alevel.web.dto.request.register.AuthDto;

public interface RegistrationFacade {

    void registration(AuthDto dto);
}