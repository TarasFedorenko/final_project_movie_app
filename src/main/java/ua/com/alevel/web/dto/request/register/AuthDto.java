package ua.com.alevel.web.dto.request.register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDto {

    private String email;
    private String password;
    private String passwordConfirm;
}
